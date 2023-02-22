package com.example.myapplication1213.ui.workerSearch;

import static android.app.Activity.RESULT_OK;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication1213.R;
import com.example.myapplication1213.databinding.FragmentWorkersBinding;
import com.example.myapplication1213.databinding.WorkerSearchFragmentBinding;
import com.example.myapplication1213.dayChoicePopup;
import com.example.myapplication1213.ui.workers.WorkersViewModel;

public class WorkerSearchFragment extends Fragment {

    private WorkerSearchViewModel mViewModel;
    private WorkerSearchFragmentBinding binding;

    public static WorkerSearchFragment newInstance() {
        return new WorkerSearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mViewModel =
                new ViewModelProvider(this).get(WorkerSearchViewModel.class);

        binding = WorkerSearchFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button btn_day_choice = binding.btnDayChice;

        btn_day_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), dayChoicePopup.class);
                startActivityForResult(intent,5);
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == RESULT_OK){
            TextView tv_day = binding.tvDay;
            tv_day.setText(data.getStringExtra("day"));
            LinearLayout ll = binding.ll;
            ll.setVisibility(View.VISIBLE);
        }
    }
}