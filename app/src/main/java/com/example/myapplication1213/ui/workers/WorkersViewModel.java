package com.example.myapplication1213.ui.workers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WorkersViewModel extends ViewModel {

    private MutableLiveData<String> wTitle, wSelect;

    public WorkersViewModel() {
        wTitle = new MutableLiveData<>();
        wTitle.setValue("작업장 인원관리");
        wSelect = new MutableLiveData<>();
        wSelect.setValue("구역별 세부 인원 정보");
    }

    public LiveData<String> getTitle() {
        return wTitle;
    }
    public LiveData<String> getSelect() {
        return wSelect;
    }
}