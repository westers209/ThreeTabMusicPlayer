package com.example.alicia.threetabmusicplayer.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClassicApi {

    /*
    Ok looks I messed up from way back here. I didn't realize this was a json object that contained a json array until sunday night.
    Would extracting the json array happen here or would I have to do it elsewhere? and how would I do so?
    Would the rest of the code work as is if I could extract the json array?
    Could you link me a good tutorial for json object with retrofit.
    Am I doing something wrong with recycler view? I don't think I've ever got it to work outside of training.
    What's this HWUI protection issue I'm getting? I think it's related to my using List on a json object issue.

    */

    @GET("search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    Call<List<ClassicPojo>> getClassicList();


}
