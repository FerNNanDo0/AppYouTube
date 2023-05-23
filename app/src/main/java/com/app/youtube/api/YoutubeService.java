package com.app.youtube.api;

import com.app.youtube.model.Resultado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeService {

    /*
    *
     https://www.googleapis.com/youtube/v3/
     searsh
     ?part=snippet
     &order=date
     &maxResults=20
     &key=AIzaSyDv2KcvdmdHlz0btGPR1xnQHFoR9xBju9A
     &channelId=UCXk_wC99hHboSi42VrgUjlw
     &q=dev+android
    */
    @GET("search")
    Call<Resultado> getVideos(
          @Query("part") String part,
          @Query("order") String order,
          @Query("maxResults") String maxResults,
          @Query("key") String key,
          @Query("channelId") String channelId,
          @Query("q") String q
    );

}
