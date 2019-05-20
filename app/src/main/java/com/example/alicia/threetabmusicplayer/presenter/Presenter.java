package com.example.alicia.threetabmusicplayer.presenter;

import com.example.alicia.threetabmusicplayer.model.ClassicNetworkConnection;
import com.example.alicia.threetabmusicplayer.model.ClassicPojo;
import com.example.alicia.threetabmusicplayer.model.PopNetworkConnection;
import com.example.alicia.threetabmusicplayer.model.PopPojo;
import com.example.alicia.threetabmusicplayer.model.RockNetworkConnection;
import com.example.alicia.threetabmusicplayer.model.RockPojo;
import com.example.alicia.threetabmusicplayer.view.ClassicViewContract;
import com.example.alicia.threetabmusicplayer.view.PopViewContract;
import com.example.alicia.threetabmusicplayer.view.RockViewContract;

import java.util.List;

import okhttp3.Cache;

public class Presenter implements PresenterContract {

    private ClassicViewContract classicViewContract;
    private PopViewContract popViewContract;
    private RockViewContract rockViewContract;

    private ClassicNetworkConnection classicNetworkConnection;
    private PopNetworkConnection popNetworkConnection;
    private RockNetworkConnection rockNetworkConnection;

    private List<ClassicPojo> classicPojoList;
    private List<PopPojo> popPojoList;
    private List<RockPojo> rockPojoList;

    @Override
    public void bind(ClassicViewContract view) {
        this.classicViewContract = view;
        classicNetworkConnection = ClassicNetworkConnection.getInstance();
    }

    @Override
    public void bind(PopViewContract view) {
        this.popViewContract = view;
        popNetworkConnection = PopNetworkConnection.getInstance();
    }

    @Override
    public void bind(RockViewContract view) {
        this.rockViewContract = view;
        rockNetworkConnection = RockNetworkConnection.getInstance();
    }

    @Override
    public void unbind() {

    }

    @Override
    public List<ClassicPojo> getClassic() {
        return null;
    }

    @Override
    public List<PopPojo> getPop() {
        return null;
    }

    @Override
    public List<RockPojo> getRock() {
        return null;
    }

    @Override
    public void initClassicNetworkConnection(Cache cache) {
        classicNetworkConnection.setPresenter(this);
    }

    @Override
    public void initPopNetworkConnection(Cache cache) {
        popNetworkConnection.setPresenter(this);
    }

    @Override
    public void initRockNetworkConnection(Cache cache) {
        rockNetworkConnection.setPresenter(this);
    }

    @Override
    public void classicNetworkSuccessful(List<ClassicPojo> classicData) {
        classicViewContract.getClassicList(classicData);
    }

    @Override
    public void popNetworkSuccessful(List<PopPojo> popData) {
        popViewContract.getPopList(popData);
    }

    @Override
    public void rockNetworkSuccessful(List<RockPojo> rockData) {
        rockViewContract.getRockList(rockData);
    }

    @Override
    public void classicNetworkFailure(String message) {
        classicViewContract.onError(message);
    }

    @Override
    public void popNetworkFailure(String message) {
        popViewContract.onError(message);
    }

    @Override
    public void rockNetworkFailure(String message) {
        rockViewContract.onError(message);
    }
}
