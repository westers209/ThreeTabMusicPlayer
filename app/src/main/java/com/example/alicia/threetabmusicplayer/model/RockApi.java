package com.example.alicia.threetabmusicplayer.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RockApi {

    @GET("search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    Call<List<RockPojo>> getRockList();
}
