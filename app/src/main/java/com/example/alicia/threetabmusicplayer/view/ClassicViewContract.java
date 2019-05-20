package com.example.alicia.threetabmusicplayer.view;

import com.example.alicia.threetabmusicplayer.model.ClassicPojo;

import java.util.List;

public interface ClassicViewContract {
    void getClassicList(List<ClassicPojo> classicPojoList);
    void onError(String message);
    void bindPresenter();
    void populateClassic();
}
