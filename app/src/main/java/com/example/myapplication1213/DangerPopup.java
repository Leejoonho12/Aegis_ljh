package com.example.myapplication1213;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DangerPopup extends Activity {

    TextView tv_dan_content;
    Button btn_dan_back, btn_dan_push;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_danger_popup);

        tv_dan_content = findViewById(R.id.tv_dan_content);
        btn_dan_back = findViewById(R.id.btn_dan_back);
        btn_dan_push = findViewById(R.id.btn_dan_push);

        tv_dan_content.setText("모든 작업 인원에게 \n위험알림을 보내시겠습니까?");
        btn_dan_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_dan_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DangerPopup.this, "푸시 보낸걸로 쳐!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}