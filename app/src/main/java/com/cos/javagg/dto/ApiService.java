package com.cos.javagg.dto;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    String key = "RGAPI-57b1a61f-b817-4950-9ef4-b87cce9d07b0";
    String site="https://kr.api.riotgames.com/lol/league/v4/challengerleagues/by-queue/RANKED_SOLO_5x5?api_key=";
    String status = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/";
    String apikey = site+key;

    @GET(apikey)
    Call<JoinItem> getData();


    @GET(status+"{summerId}"+"?api_key="+key) // 섬머아이디를 받고 처리
    Call<StatusData> getStatus(@Path("summerId") String summerId);

}
