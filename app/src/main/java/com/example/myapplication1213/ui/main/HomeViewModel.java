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
        contentw.setValue("비콘 스캐너를 통해 파악된\n위치 데이터 노출\n\nA 구역 3명\nB 구역 2명");
        contents = new MutableLiveData<>();
        contents.setValue("온도 : -1℃\n습도 : 54%\n톨루엔농도 : 821㎍/㎥\n아세톤농도 : 27ppm\n암모니아농도 : 11ppm\n"
        +"이산화탄소농도 : 756ppm\n일산화탄소농도 : 166ppm\n포름알데히드농도 : 124㎍/㎥");
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