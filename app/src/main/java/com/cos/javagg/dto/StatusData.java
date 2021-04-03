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
public class StatusData {
    @SerializedName("accountId")
    private String accountId;
    @SerializedName("profileIconId")
    private String profileIconId;
    @SerializedName("revisionDate")
    private long revisionDate;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;
    @SerializedName("puuid")
    private String puuid;
    @SerializedName("summonerLevel")
    private long summonerLevel;
}
