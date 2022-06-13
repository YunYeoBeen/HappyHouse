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
@ApiModel(value = "HouseDealDto(유저 정보)", description = "아파트의 aptCode, 이름, 동코드, 동이름,건축년도,지번주소, 위도, 경도, 이미지, 역간거리, 가까운역이름")
public class HouseInfo {
	private int aptCode;
	private String aptName;
	private String dongCode;
	private String dongName;
	private String buildYear;
	private String jibunAddr;
	private double lat;
	private double lng;
	private String img;
	private int distFromSubway;
	private String subwayName;
	private int convStoreCnt;
	private int restaurantCnt;
	private int cafeCnt;
}
