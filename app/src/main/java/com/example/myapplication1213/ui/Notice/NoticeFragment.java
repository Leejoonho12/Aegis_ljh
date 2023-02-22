package com.example.myapplication1213.ui.Notice;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication1213.NoticeAdapter;
import com.example.myapplication1213.NoticePopup;
import com.example.myapplication1213.NoticeVO;
import com.example.myapplication1213.NoticeWrite;
import com.example.myapplication1213.databinding.NoticeFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class NoticeFragment extends Fragment {

    private NoticeViewModel mViewModel;
    private NoticeFragmentBinding binding;
    NoticeAdapter adapter;
    ListView lv_notice;
    String title;
    String content;
    String writer;
    String day;

    //서버로 요청하는 객체
    private RequestQueue queue;
    //서버로 요청시 필요한 정보를 담는 객체
    private StringRequest stringRequest;
    //클라이언트를 판별하는 값
    private String TAG = "main";

    public static NoticeFragment newInstance() {
        return new NoticeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(NoticeViewModel.class);
        binding = NoticeFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView tvHomeTitle = binding.tvNoticeTitle;
        final Button btn_notice_writeMove = binding.btnNoticeWriteMove;
        lv_notice = binding.lvNotice;
        sendRequest1();
        mViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tvHomeTitle.setText(s);
            }
        });
        btn_notice_writeMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticeWrite.class);
                startActivityForResult(intent,1);
            }
        });

        lv_notice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NoticeVO vo = (NoticeVO) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), NoticePopup.class);
                intent.putExtra("title",vo.getTitle());
                intent.putExtra("content",vo.getContent());
                intent.putExtra("worker",vo.getWriter());
                intent.putExtra("day",vo.getDay());
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            sendRequest1();
        }

    }

    public void sendRequest1() {
        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(getActivity());
        // 서버에 요청할 주소(cmd : ipconfig, eclips : servers : HTTP/1.1)
        String url = "http://172.17.102.233:8087/AndroidServer/ProjectNoticeList";

        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue", response);
                adapter = new NoticeAdapter();
                lv_notice = binding.lvNotice;
                lv_notice.setAdapter(adapter);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        title = jsonObject.getString("title");
                        content = jsonObject.getString("content");
                        writer = jsonObject.getString("worker");
                        day = jsonObject.getString("day");
                        adapter.addItem(title,content,writer,day);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override //response를 UTF8로 변경해주는 소스코드
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }

            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }

}