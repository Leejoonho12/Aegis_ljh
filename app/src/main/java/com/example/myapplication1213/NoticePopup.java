package com.example.myapplication1213;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class NoticePopup extends Activity {

    TextView tv_npop_title,tv_npop_content,tv_npop_writer,tv_npop_day;
    Button btn_bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new PaintDrawable(Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        tv_npop_title = findViewById(R.id.tv_npop_title);
        tv_npop_content = findViewById(R.id.tv_npop_content);
        tv_npop_writer = findViewById(R.id.tv_npop_writer);
        tv_npop_day = findViewById(R.id.tv_npop_day);
        btn_bm = findViewById(R.id.btn_bm);
        tv_npop_title.setText(intent.getStringExtra("title"));
        tv_npop_content.setText(intent.getStringExtra("content"));
        tv_npop_writer.setText(intent.getStringExtra("writer"));
        tv_npop_day.setText(intent.getStringExtra("day"));
        btn_bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}