package com.example.myapplication1213.ui.sensor;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication1213.R;
import com.example.myapplication1213.SensorPopup;
import com.example.myapplication1213.databinding.FragmentSensorBinding;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class SensorFragment extends Fragment {

    private com.example.myapplication1213.ui.sensor.SensorViewModel sensorViewModel;
    private FragmentSensorBinding binding;
    LineChart lineChart_sensor1,lineChart_sensor2,lineChart_sensor3,lineChart_sensor4,
            lineChart_sensor5,lineChart_sensor6,lineChart_sensor7,lineChart_sensor8;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sensorViewModel =
                new ViewModelProvider(this).get(com.example.myapplication1213.ui.sensor.SensorViewModel.class);

        binding = FragmentSensorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView tvSensorTitle = binding.tvSensorTitle;
        sensorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tvSensorTitle.setText(s);
            }
        });

        final Button btn_sensor_choice = binding.btnSensorChoice;
        btn_sensor_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //센서 고를 수 있는 팝업창 띄워 줄 거임
                Intent intent = new Intent(getActivity(), SensorPopup.class);
                //requestCode : 정의된 액티비티의 구분값 (정해진 틀은 없음)
                startActivityForResult(intent, 7);
            }
        });

        lineChart_sensor1 = binding.linechartSensor1;
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(9, (float)1));
        values.add(new Entry(10, (float)1));
        values.add(new Entry(11, (float)1));
        values.add(new Entry(12, (float)1));
        values.add(new Entry(13, (float)0));
        values.add(new Entry(14, (float)0));
        values.add(new Entry(15, (float)0));
        values.add(new Entry(16, (float)0));
        values.add(new Entry(17, (float)-1));
        values.add(new Entry(18, (float)-1));
        LineDataSet set1;
        set1 = new LineDataSet(values, "온도");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data1 = new LineData(dataSets);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.RED);
        lineChart_sensor1.setData(data1);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {
            String msg = data.getStringExtra("sn");
            String msg1 = data.getStringExtra("sln");
            TextView tv_sensor_name = binding.tvSensorName;
            TextView tv_sensor_value2 = binding.tvSensorValue2;
            TextView tv_sensor_value = binding.tvSensorValue;
            lineChart_sensor1 = binding.linechartSensor1;
            lineChart_sensor2 = binding.linechartSensor2;
            lineChart_sensor3 = binding.linechartSensor3;
            lineChart_sensor4 = binding.linechartSensor4;
            lineChart_sensor5 = binding.linechartSensor5;
            lineChart_sensor6 = binding.linechartSensor6;
            lineChart_sensor7 = binding.linechartSensor7;
            lineChart_sensor8 = binding.linechartSensor8;
            tv_sensor_name.setText(msg);
            tv_sensor_value2.setText(msg1);
            if(msg.equals("온도")){
                tv_sensor_value.setText("-1");
                tv_sensor_value.setTextColor(Color.parseColor("#03A9F4"));
                lineChart_sensor1.setVisibility(View.VISIBLE);
                lineChart_sensor2.setVisibility(View.INVISIBLE);
                lineChart_sensor3.setVisibility(View.INVISIBLE);
                lineChart_sensor4.setVisibility(View.INVISIBLE);
                lineChart_sensor5.setVisibility(View.INVISIBLE);
                lineChart_sensor6.setVisibility(View.INVISIBLE);
                lineChart_sensor7.setVisibility(View.INVISIBLE);
                lineChart_sensor8.setVisibility(View.INVISIBLE);
            }else if(msg.equals("습도")){
                tv_sensor_value.setText("54");
                tv_sensor_value.setTextColor(Color.parseColor("#03A9F4"));
                lineChart_sensor1.setVisibility(View.INVISIBLE);
                lineChart_sensor2.setVisibility(View.VISIBLE);
                lineChart_sensor3.setVisibility(View.INVISIBLE);
                lineChart_sensor4.setVisibility(View.INVISIBLE);
                lineChart_sensor5.setVisibility(View.INVISIBLE);
                lineChart_sensor6.setVisibility(View.INVISIBLE);
                lineChart_sensor7.setVisibility(View.INVISIBLE);
                lineChart_sensor8.setVisibility(View.INVISIBLE);
                ArrayList<Entry> values = new ArrayList<>();
                values.add(new Entry(9, (float)54));
                values.add(new Entry(10, (float)52));
                values.add(new Entry(11, (float)54));
                values.add(new Entry(12, (float)53));
                values.add(new Entry(13, (float)53));
                values.add(new Entry(14, (float)53));
                values.add(new Entry(15, (float)54));
                values.add(new Entry(16, (float)54));
                values.add(new Entry(17, (float)54));
                values.add(new Entry(18, (float)54));
                LineDataSet set1;
                set1 = new LineDataSet(values, "습도");
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);
                LineData data1 = new LineData(dataSets);
                set1.setColor(Color.BLACK);
                set1.setCircleColor(Color.RED);
                lineChart_sensor2.setData(data1);
            }else if(msg.equals("톨루엔 농도")){
                tv_sensor_value.setText("900");
                tv_sensor_value.setTextColor(Color.parseColor("#F1D236"));
                lineChart_sensor1.setVisibility(View.INVISIBLE);
                lineChart_sensor2.setVisibility(View.INVISIBLE);
                lineChart_sensor3.setVisibility(View.VISIBLE);
                lineChart_sensor4.setVisibility(View.INVISIBLE);
                lineChart_sensor5.setVisibility(View.INVISIBLE);
                lineChart_sensor6.setVisibility(View.INVISIBLE);
                lineChart_sensor7.setVisibility(View.INVISIBLE);
                lineChart_sensor8.setVisibility(View.INVISIBLE);
                ArrayList<Entry> values = new ArrayList<>();
                values.add(new Entry(9, (float)791));
                values.add(new Entry(10, (float)802));
                values.add(new Entry(11, (float)801));
                values.add(new Entry(12, (float)823));
                values.add(new Entry(13, (float)789));
                values.add(new Entry(14, (float)856));
                values.add(new Entry(15, (float)1021));
                values.add(new Entry(16, (float)929));
                values.add(new Entry(17, (float)857));
                values.add(new Entry(18, (float)900));
                LineDataSet set1;
                set1 = new LineDataSet(values, "톨루엔농도");
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);
                LineData data1 = new LineData(dataSets);
                set1.setColor(Color.BLACK);
                set1.setCircleColor(Color.RED);
                lineChart_sensor3.setData(data1);
            }else if(msg.equals("아세톤 농도")){
                tv_sensor_value.setText("27");
                tv_sensor_value.setTextColor(Color.parseColor("red"));
                lineChart_sensor1.setVisibility(View.INVISIBLE);
                lineChart_sensor2.setVisibility(View.INVISIBLE);
                lineChart_sensor3.setVisibility(View.INVISIBLE);
                lineChart_sensor4.setVisibility(View.VISIBLE);
                lineChart_sensor5.setVisibility(View.INVISIBLE);
                lineChart_sensor6.setVisibility(View.INVISIBLE);
                lineChart_sensor7.setVisibility(View.INVISIBLE);
                lineChart_sensor8.setVisibility(View.INVISIBLE);
                ArrayList<Entry> values = new ArrayList<>();
                values.add(new Entry(9, (float)21));
                values.add(new Entry(10, (float)21));
                values.add(new Entry(11, (float)21));
                values.add(new Entry(12, (float)21));
                values.add(new Entry(13, (float)21));
                values.add(new Entry(14, (float)23));
                values.add(new Entry(15, (float)24));
                values.add(new Entry(16, (float)24));
                values.add(new Entry(17, (float)26));
                values.add(new Entry(18, (float)27));
                LineDataSet set1;
                set1 = new LineDataSet(values, "아세톤농도");
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);
                LineData data1 = new LineData(dataSets);
                set1.setColor(Color.BLACK);
                set1.setCircleColor(Color.RED);
                lineChart_sensor4.setData(data1);
            }else if(msg.equals("일산화탄소 농도")){
                tv_sensor_value.setText("21");
                tv_sensor_value.setTextColor(Color.parseColor("#03A9F4"));
                lineChart_sensor1.setVisibility(View.INVISIBLE);
                lineChart_sensor2.setVisibility(View.INVISIBLE);
                lineChart_sensor3.setVisibility(View.INVISIBLE);
                lineChart_sensor4.setVisibility(View.INVISIBLE);
                lineChart_sensor5.setVisibility(View.INVISIBLE);
                lineChart_sensor6.setVisibility(View.VISIBLE);
                lineChart_sensor7.setVisibility(View.INVISIBLE);
                lineChart_sensor8.setVisibility(View.INVISIBLE);
                ArrayList<Entry> values = new ArrayList<>();
                values.add(new Entry(9, (float)21));
                values.add(new Entry(10, (float)21));
                values.add(new Entry(11, (float)21));
                values.add(new Entry(12, (float)21));
                values.add(new Entry(13, (float)21));
                values.add(new Entry(14, (float)22));
                values.add(new Entry(15, (float)21));
                values.add(new Entry(16, (float)21));
                values.add(new Entry(17, (float)20));
                values.add(new Entry(18, (float)21));
                LineDataSet set1;
                set1 = new LineDataSet(values, "일산화탄소농도");
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);
                LineData data1 = new LineData(dataSets);
                set1.setColor(Color.BLACK);
                set1.setCircleColor(Color.RED);
                lineChart_sensor6.setData(data1);
            }else if(msg.equals("암모니아 농도")){
                tv_sensor_value.setText("19");
                tv_sensor_value.setTextColor(Color.parseColor("#03A9F4"));
                lineChart_sensor1.setVisibility(View.INVISIBLE);
                lineChart_sensor2.setVisibility(View.INVISIBLE);
                lineChart_sensor3.setVisibility(View.INVISIBLE);
                lineChart_sensor4.setVisibility(View.INVISIBLE);
                lineChart_sensor5.setVisibility(View.INVISIBLE);
                lineChart_sensor6.setVisibility(View.INVISIBLE);
                lineChart_sensor7.setVisibility(View.VISIBLE);
                lineChart_sensor8.setVisibility(View.INVISIBLE);
                ArrayList<Entry> values = new ArrayList<>();
                values.add(new Entry(9, (float)19));
                values.add(new Entry(10, (float)18));
                values.add(new Entry(11, (float)18));
                values.add(new Entry(12, (float)18));
                values.add(new Entry(13, (float)18));
                values.add(new Entry(14, (float)18));
                values.add(new Entry(15, (float)19));
                values.add(new Entry(16, (float)19));
                values.add(new Entry(17, (float)19));
                values.add(new Entry(18, (float)19));
                LineDataSet set1;
                set1 = new LineDataSet(values, "암모니아농도");
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);
                LineData data1 = new LineData(dataSets);
                set1.setColor(Color.BLACK);
                set1.setCircleColor(Color.RED);
                lineChart_sensor7.setData(data1);
            }else if(msg.equals("포름알데히드 농도")){
                tv_sensor_value.setText("128");
                tv_sensor_value.setTextColor(Color.parseColor("#03A9F4"));
                lineChart_sensor1.setVisibility(View.INVISIBLE);
                lineChart_sensor2.setVisibility(View.INVISIBLE);
                lineChart_sensor3.setVisibility(View.INVISIBLE);
                lineChart_sensor4.setVisibility(View.INVISIBLE);
                lineChart_sensor5.setVisibility(View.INVISIBLE);
                lineChart_sensor6.setVisibility(View.INVISIBLE);
                lineChart_sensor7.setVisibility(View.INVISIBLE);
                lineChart_sensor8.setVisibility(View.VISIBLE);
                ArrayList<Entry> values = new ArrayList<>();
                values.add(new Entry(9, (float)145));
                values.add(new Entry(10, (float)141));
                values.add(new Entry(11, (float)138));
                values.add(new Entry(12, (float)136));
                values.add(new Entry(13, (float)134));
                values.add(new Entry(14, (float)133));
                values.add(new Entry(15, (float)132));
                values.add(new Entry(16, (float)130));
                values.add(new Entry(17, (float)129));
                values.add(new Entry(18, (float)128));
                LineDataSet set1;
                set1 = new LineDataSet(values, "포름알데히드농도");
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);
                LineData data1 = new LineData(dataSets);
                set1.setColor(Color.BLACK);
                set1.setCircleColor(Color.RED);
                lineChart_sensor8.setData(data1);
            }else if(msg.equals("이산화탄소 농도")){
                tv_sensor_value.setText("411");
                tv_sensor_value.setTextColor(Color.parseColor("#03A9F4"));
                lineChart_sensor1.setVisibility(View.INVISIBLE);
                lineChart_sensor2.setVisibility(View.INVISIBLE);
                lineChart_sensor3.setVisibility(View.INVISIBLE);
                lineChart_sensor4.setVisibility(View.INVISIBLE);
                lineChart_sensor5.setVisibility(View.VISIBLE);
                lineChart_sensor6.setVisibility(View.INVISIBLE);
                lineChart_sensor7.setVisibility(View.INVISIBLE);
                lineChart_sensor8.setVisibility(View.INVISIBLE);
                ArrayList<Entry> values = new ArrayList<>();
                values.add(new Entry(9, (float)400));
                values.add(new Entry(10, (float)401));
                values.add(new Entry(11, (float)480));
                values.add(new Entry(12, (float)600));
                values.add(new Entry(13, (float)709));
                values.add(new Entry(14, (float)652));
                values.add(new Entry(15, (float)555));
                values.add(new Entry(16, (float)431));
                values.add(new Entry(17, (float)420));
                values.add(new Entry(18, (float)411));
                LineDataSet set1;
                set1 = new LineDataSet(values, "이산화탄소농도");
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);
                LineData data1 = new LineData(dataSets);
                set1.setColor(Color.BLACK);
                set1.setCircleColor(Color.RED);
                lineChart_sensor5.setData(data1);
            }
        }
    }


}