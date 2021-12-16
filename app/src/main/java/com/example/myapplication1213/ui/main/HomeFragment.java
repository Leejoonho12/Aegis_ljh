package com.example.myapplication1213.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication1213.DangerPopup;
import com.example.myapplication1213.TellPopup;
import com.example.myapplication1213.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

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
                // 1번버튼 그림자 주고 2번버튼 그림자 없애기 #EAC625
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
                // 2번버튼 그림자 주고 1번버튼 그림자 없애기
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
}