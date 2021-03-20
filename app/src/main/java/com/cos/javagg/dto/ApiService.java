package com.cos.javagg.dto;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    String key = "RGAPI-158bb094-e231-4cac-bffc-d4d66e099497";
    String site="https://kr.api.riotgames.com/lol/league/v4/challengerleagues/by-queue/RANKED_SOLO_5x5?api_key=";
    String apikey = site+key;

    @GET(apikey)
    Call<JoinItem> getData();

}
