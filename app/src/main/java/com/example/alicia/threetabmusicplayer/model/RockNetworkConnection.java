package com.example.alicia.threetabmusicplayer.model;

import com.example.alicia.threetabmusicplayer.presenter.Presenter;

import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RockNetworkConnection {
    private RockNetworkConnection(){}

    private static RockNetworkConnection instance;
    private static Presenter presenter;

    Callback<List<RockPojo>> callback = new Callback<List<RockPojo>>() {
        @Override
        public void onResponse(Call<List<RockPojo>> call, Response<List<RockPojo>> response) {
            if (response.isSuccessful() && response != null) {
                presenter.rockNetworkSuccessful(response.body());
            }
        }

        @Override
        public void onFailure(Call<List<RockPojo>> call, Throwable t) {
            presenter.rockNetworkFailure(t.getMessage());
        }
    };



    public static RockNetworkConnection getInstance() {
        if(instance == null){
            instance = new RockNetworkConnection();
        }
        return instance;
    }

    public void setPresenter(Presenter presenter) {
        instance.presenter = presenter;
    }

    public void initRetrofit(Cache cache){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RockApi.class)
                .getRockList()
                .enqueue(callback);
    }
}
