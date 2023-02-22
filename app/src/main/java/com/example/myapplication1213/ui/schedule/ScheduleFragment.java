package com.example.myapplication1213.ui.schedule;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

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
import com.example.myapplication1213.R;
import com.example.myapplication1213.ScheduleInput;
import com.example.myapplication1213.databinding.ScheduleFragmentBinding;
import com.example.myapplication1213.ui.Notice.NoticeViewModel;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ScheduleFragment extends Fragment {

    private ScheduleViewModel mViewModel;
    private ScheduleFragmentBinding binding;
    private String day;
    //서버로 요청하는 객체
    private RequestQueue queue;
    //서버로 요청시 필요한 정보를 담는 객체
    private StringRequest stringRequest;
    //클라이언트를 판별하는 값
    private String TAG = "main";

    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(ScheduleViewModel.class);
        binding = ScheduleFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TextView tv_schedule_content = binding.tvScheduleContent;
        Button btn_schedule_remove = binding.btnScheduleRemove;
        Button btn_schedule_commit = binding.btnScheduleCommit;
        EditText edt_schedule_input = binding.edtScheduleInput;
        Button btn_schinMove = binding.btnSchinMove;
        TextView tv_schedule_title = binding.tvScheduleTitle;

        btn_schinMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScheduleInput.class);
                startActivity(intent);
            }
        });

        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tv_schedule_title.setText(s);
            }
        });

        CalendarView calendarView = binding.calendarView;
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                tv_schedule_content.setVisibility(View.VISIBLE);
                btn_schedule_remove.setVisibility(View.VISIBLE);
                day = year+"년"+(month+1)+"월"+dayOfMonth+"일 예상일정";
                tv_schedule_content.setText(day+"\n"+
                        "일정 : 2차 프로젝트\n시작날짜 : 21/12/01\n종료날짜 : 21/12/30\n");
                if(dayOfMonth==30){
                    tv_schedule_content.setText(day+"\n"+
                            "일정 : 2차 프로젝트\n시작날짜 : 21/12/01\n종료날짜 : 21/12/30\n" +
                            "일정 : 발표, 수료\n시작날짜 : 21/12/30\n종료날짜 : 21/12/30\n");
                }
                if(dayOfMonth==31){
                    tv_schedule_content.setText("일정이 없습니다.");
                }
            }
        });

        btn_schedule_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_schedule_content.setVisibility(View.INVISIBLE);
                btn_schedule_remove.setVisibility(View.INVISIBLE);
                edt_schedule_input.setVisibility(View.VISIBLE);
                btn_schedule_commit.setVisibility(View.VISIBLE);
            }
        });

        btn_schedule_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_schedule_content.setVisibility(View.VISIBLE);
                btn_schedule_remove.setVisibility(View.VISIBLE);
                edt_schedule_input.setVisibility(View.INVISIBLE);
                btn_schedule_commit.setVisibility(View.INVISIBLE);
                tv_schedule_content.setText(day+"\n"+
                        "일정 : 2차 프로젝트\n시작날짜 : 21/12/01\n종료날짜 : 21/12/30\n"+(edt_schedule_input.getText().toString()));
                edt_schedule_input.setText("");
            }
        });

        return root;
    }

    public void sendRequestset() {
        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(getActivity());
        // 서버에 요청할 주소(cmd : ipconfig, eclips : servers : HTTP/1.1)
        String url = "http://172.30.1.35:8087/AndroidServer/ProjectBeaconList";

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

    public void sendRequestget() {
        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(getActivity());
        // 서버에 요청할 주소(cmd : ipconfig, eclips : servers : HTTP/1.1)
        String url = "http://172.30.1.35:8087/AndroidServer/ProjectBeaconList";

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