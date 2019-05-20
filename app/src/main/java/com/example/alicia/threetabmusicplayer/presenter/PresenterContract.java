package com.example.alicia.threetabmusicplayer.presenter;

import com.example.alicia.threetabmusicplayer.model.ClassicPojo;
import com.example.alicia.threetabmusicplayer.model.PopPojo;
import com.example.alicia.threetabmusicplayer.model.RockPojo;
import com.example.alicia.threetabmusicplayer.view.ClassicViewContract;
import com.example.alicia.threetabmusicplayer.view.PopViewContract;
import com.example.alicia.threetabmusicplayer.view.RockViewContract;

import java.util.List;

import okhttp3.Cache;

public interface PresenterContract {

    void bind(ClassicViewContract view);
    void bind(PopViewContract view);
    void bind(RockViewContract view);

    void unbind();

    List<ClassicPojo> getClassic();
    List<PopPojo> getPop();
    List<RockPojo> getRock();

    void initClassicNetworkConnection(Cache cache);
    void initPopNetworkConnection(Cache cache);
    void initRockNetworkConnection(Cache cache);

    void classicNetworkSuccessful(List<ClassicPojo> classicData);
    void popNetworkSuccessful(List<PopPojo> popData);
    void rockNetworkSuccessful(List<RockPojo> rockData);

    void classicNetworkFailure(String message);
    void popNetworkFailure(String message);
    void rockNetworkFailure(String message);
}
