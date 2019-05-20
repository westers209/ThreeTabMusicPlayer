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



public class ClassicNetworkConnection {

    private ClassicNetworkConnection(){}

    private static ClassicNetworkConnection instance;
    private static Presenter presenter;

    Callback<List<ClassicPojo>> callback = new Callback<List<ClassicPojo>>() {
        @Override
        public void onResponse(Call<List<ClassicPojo>> call, Response<List<ClassicPojo>> response) {
            if (response.isSuccessful() && response != null) {
                presenter.classicNetworkSuccessful(response.body());
            }
        }

        @Override
        public void onFailure(Call<List<ClassicPojo>> call, Throwable t) {
            presenter.classicNetworkFailure(t.getMessage());
        }
    };

    public static ClassicNetworkConnection getInstance() {
        if(instance == null){
            instance = new ClassicNetworkConnection();
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
                .create(ClassicApi.class)
                .getClassicList()
                .enqueue(callback);
    }
}
