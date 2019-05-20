package com.example.alicia.threetabmusicplayer.view;

import com.example.alicia.threetabmusicplayer.model.PopPojo;

import java.util.List;

public interface PopViewContract {
    void getPopList(List<PopPojo> popPojoList);
    void onError(String message);
    void bindPresenter();
    void populatePop();
}
