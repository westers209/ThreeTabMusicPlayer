package com.example.alicia.threetabmusicplayer.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PopApi {

    @GET("search?term=pop&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    Call<List<PopPojo>> getPopList();
}
