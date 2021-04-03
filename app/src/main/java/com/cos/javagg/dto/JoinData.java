package com.cos.javagg.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinData {
    @SerializedName("wins")
    private String wins;
    @SerializedName("freshBlood")
    private String freshBlood;
    @SerializedName("inactive")
    private String inactive;
    @SerializedName("veteran")
    private String veteran;
    @SerializedName("hotStreak")
    private String hotStreak;
    @SerializedName("summonerName")
    private String summonerName;
    @SerializedName("rank")
    private String rank;
    @SerializedName("summonerId")
    private String summonerId;
    @SerializedName("leaguePoints")
    private int leaguePoints;
    @SerializedName("losses")
    private String losses;



}
