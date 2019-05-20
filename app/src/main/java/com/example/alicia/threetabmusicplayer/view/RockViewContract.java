package com.example.alicia.threetabmusicplayer.view;

import com.example.alicia.threetabmusicplayer.model.RockPojo;

import java.util.List;

public interface RockViewContract {
    void getRockList(List<RockPojo> rockPojoList);
    void onError(String message);
    void bindPresenter();
    void populateRock();
}
