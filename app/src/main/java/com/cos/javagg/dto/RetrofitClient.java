package com.cos.javagg.dto;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    //서버 url설정
                    .baseUrl("https://kr.api.riotgames.com")
                    //데이터 파싱 설정
                    .addConverterFactory(GsonConverterFactory.create())
                    //객체정보 반환
                    .build();
        }
        return retrofit;
    }
}
