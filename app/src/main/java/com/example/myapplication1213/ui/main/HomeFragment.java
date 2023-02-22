package com.example.myapplication1213.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.example.myapplication1213.DangerPopup;
import com.example.myapplication1213.R;
import com.example.myapplication1213.TellAdapter;
import com.example.myapplication1213.TellPopup;
import com.example.myapplication1213.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    //서버로 요청하는 객체
    private RequestQueue queue;
    //서버로 요청시 필요한 정보를 담는 객체
    private StringRequest stringRequest;
    //클라이언트를 판별하는 값
    private String TAG = "main";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView tvHomeTitle = binding.tvHomeTitle;
        final TextView tvHomeContent = binding.tvHomeContent;
        final ImageButton btnHomeTell = binding.btnHomeTell;
        final ImageButton btnHomeDanger = binding.btnHomeDanger;
        final Button btnHomeWorker = binding.btnHomeWorker;
        final Button btnHomeSensor = binding.btnHomeSensor;
        homeViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tvHomeTitle.setText(s);
            }
        });
        btnHomeWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHomeWorker.setBackgroundColor(Color.parseColor("#EAC625"));
                btnHomeSensor.setBackgroundColor(Color.parseColor("#C1C1C1"));
                homeViewModel.getContentw().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        tvHomeContent.setText(s);
                    }
                });
            }
        });
        btnHomeSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHomeWorker.setBackgroundColor(Color.parseColor("#C1C1C1"));
                btnHomeSensor.setBackgroundColor(Color.parseColor("#EAC625"));
                homeViewModel.getContents().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        tvHomeContent.setText(s);
                    }
                });
            }
        });
        btnHomeTell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TellPopup.class);
                startActivity(intent);
            }
        });
        btnHomeDanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DangerPopup.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void sendRequest() {
        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(getActivity());
        // 서버에 요청할 주소(cmd : ipconfig, eclips : servers : HTTP/1.1)
        String url = "http://172.30.1.34:8087/AndroidServer/ProjectBeaconList";

        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue", response);
//                adapter = new TellAdapter();
//                lv_tell = findViewById(R.id.lv_tell);
//                lv_tell.setAdapter(adapter);
//                imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                try {
//                    JSONArray jsonArray = new JSONArray(response);
//                    for(int i = 0; i < jsonArray.length(); i++){
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        adapter.addItem(jsonObject.getString("name"), jsonObject.getString("tellnum"));
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
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