package com.example.myapplication1213.ui.joinselect;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication1213.NoticeAdapter;
import com.example.myapplication1213.R;
import com.example.myapplication1213.TellPopup;
import com.example.myapplication1213.WorkerListAdapter;
import com.example.myapplication1213.databinding.JoinSelectFragmentBinding;
import com.example.myapplication1213.databinding.NoticeFragmentBinding;
import com.example.myapplication1213.ui.Notice.NoticeViewModel;

public class JoinSelectFragment extends Fragment {

    private JoinSelectViewModel mViewModel;
    private JoinSelectFragmentBinding binding;
    WorkerListAdapter adapter;

    public static JoinSelectFragment newInstance() {
        return new JoinSelectFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(JoinSelectViewModel.class);
        binding = JoinSelectFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListView lv_worker_list = binding.lvWorkerList;
        adapter = new WorkerListAdapter();
        lv_worker_list.setAdapter(adapter);

        adapter.addItem("오세영","010-1111-1111");
        adapter.addItem("김경국","010-2222-2222");
        adapter.addItem("김재중","010-3333-3333");
        adapter.addItem("김범종","010-4444-4444");
        adapter.addItem("김종원","010-5555-5555");
        Button button = binding.button;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "등록 완료!", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

}