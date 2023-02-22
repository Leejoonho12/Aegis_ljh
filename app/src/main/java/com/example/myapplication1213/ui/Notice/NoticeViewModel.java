package com.example.myapplication1213.ui.Notice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NoticeViewModel extends ViewModel {

    private MutableLiveData<String> title;

    public NoticeViewModel(){
        title = new MutableLiveData<>();
        title.setValue("공지사항");
    }
    public LiveData<String> getTitle() {
        return title;
    }

}