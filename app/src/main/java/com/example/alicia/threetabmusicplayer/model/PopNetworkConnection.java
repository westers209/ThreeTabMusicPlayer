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

public class PopNetworkConnection {
    private PopNetworkConnection(){}

    private static PopNetworkConnection instance;
    private static Presenter presenter;

    Callback<List<PopPojo>> callback = new Callback<List<PopPojo>>() {
        @Override
        public void onResponse(Call<List<PopPojo>> call, Response<List<PopPojo>> response) {
            if (response.isSuccessful() && response != null) {
                presenter.popNetworkSuccessful(response.body());
            }
        }

        @Override
        public void onFailure(Call<List<PopPojo>> call, Throwable t) {
            presenter.popNetworkFailure(t.getMessage());
        }
    };



    public static PopNetworkConnection getInstance() {
        if(instance == null){
            instance = new PopNetworkConnection();
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
                .create(PopApi.class)
                .getPopList()
                .enqueue(callback);
    }
}
