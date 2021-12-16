package com.example.myapplication1213.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> title;
    private MutableLiveData<String> contentw;
    private MutableLiveData<String> contents;

    public HomeViewModel() {
        title = new MutableLiveData<>();
        title.setValue("AEGIS");
        contentw = new MutableLiveData<>();
        contentw.setValue("비콘 스캐너를 통해 파악된\n위치 데이터 노출\n\nA 구역 n명\nB 구역 n명");
        contents = new MutableLiveData<>();
        contents.setValue("오늘의 날씨\n\n센서값 1 : -\n센서값 2 : -\n센서값 3 : -\n센서값 4 : -\n센서값 5 : -");
    }

    public LiveData<String> getTitle() {
        return title;
    }
    public LiveData<String> getContentw() {
        return contentw;
    }
    public LiveData<String> getContents() {
        return contents;
    }
}