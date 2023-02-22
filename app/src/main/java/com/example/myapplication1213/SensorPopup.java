package com.example.myapplication1213;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;

public class SensorPopup extends Activity {

    RadioGroup radioGroup;
    String sensorName;
    String sensorLastName;
    Button btn_shutdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sensor_popup);

        radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb1){
                    sensorName = "온도";
                    sensorLastName = "℃";
                } else if(checkedId == R.id.rb2){
                    sensorName = "습도";
                    sensorLastName = "%";
                } else if(checkedId == R.id.rb3){
                    sensorName = "톨루엔 농도";
                    sensorLastName = "㎍/㎥";
                } else if(checkedId == R.id.rb4){
                    sensorName = "아세톤 농도";
                    sensorLastName = "ppm";
                } else if(checkedId == R.id.rb5){
                    sensorName = "암모니아 농도";
                    sensorLastName = "ppm";
                } else if(checkedId == R.id.rb6){
                    sensorName = "일산화탄소 농도";
                    sensorLastName = "ppm";
                } else if(checkedId == R.id.rb7){
                    sensorName = "이산화탄소 농도";
                    sensorLastName = "ppm";
                } else if(checkedId == R.id.rb8){
                    sensorName = "포름알데히드 농도";
                    sensorLastName = "㎍/㎥";
                }
            }
        });

        btn_shutdown = findViewById(R.id.btn_shutdown);
        btn_shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("sn", sensorName);
                intent.putExtra("sln", sensorLastName);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}