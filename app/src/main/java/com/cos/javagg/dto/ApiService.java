package com.cos.javagg.dto;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    String key = "RGAPI-f44b6312-b89f-40b6-8ab8-cc2303eab11f";
    String site="https://kr.api.riotgames.com/lol/league/v4/grandmasterleagues/by-queue/RANKED_SOLO_5x5?api_key=";
    String apikey = site+key;

    @GET(apikey)
    Call<JoinItem> getData();

}
