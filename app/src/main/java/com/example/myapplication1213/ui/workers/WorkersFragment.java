package com.example.myapplication1213.ui.workers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication1213.ScannerAdapter;
import com.example.myapplication1213.databinding.FragmentWorkersBinding;

public class WorkersFragment extends Fragment {

    private WorkersViewModel galleryViewModel;
    private FragmentWorkersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(WorkersViewModel.class);

        binding = FragmentWorkersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView tvWorkerTitle = binding.tvWorkerTitle;
        galleryViewModel.getTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tvWorkerTitle.setText(s);
            }
        });
        final TextView tvWorerWlist = binding.tvWorkerWlist;
        galleryViewModel.getSelect().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tvWorerWlist.setText(s);
            }
        });

        ListView lv_worker = binding.lvWorker;
        ScannerAdapter adapter = new ScannerAdapter();
        lv_worker.setAdapter(adapter);
        adapter.addItem("A1","오세영","a_a_1");
        adapter.addItem("A1","김경국","a_a_2");
        adapter.addItem("A1","김재중","a_a_3");
        adapter.addItem("B1","김범종","a_b_1");
        adapter.addItem("B1","김종원","a_b_2");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}