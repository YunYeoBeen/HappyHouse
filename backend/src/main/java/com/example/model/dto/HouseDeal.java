package com.example.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "HouseDealDto(아파트 매매가 정보)", description = "아파트의 번호, aptCode, 가격, 매매가 이루어진 년, 월, 일, 평수, 층수, 타입, 집세")
public class HouseDeal {
    private int no;
    private int aptCode;
    private String dealAmount;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private String area;
    private String floor;
    private String type;
    private String rentMoney;
}
