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
public class StatusDto {
    @SerializedName("summonerId")
    private String summonerId;
    @SerializedName("profileIconId")
    private String profileIconId;
}
