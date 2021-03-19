package com.cos.javagg.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JoinItem {
    @SerializedName("tier")
    public String tier;

    @SerializedName("leagueId")
    public String liagueId;

    @SerializedName("queue")
    public String queue;

    @SerializedName("name")
    public String name;

    @SerializedName("entries")
    public List<JoinData> body;

    public String toString(){
        return "JoinItem{"+"body="+body+'}';
    }

}
