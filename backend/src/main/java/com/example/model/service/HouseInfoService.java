package com.example.model.service;

import java.util.List;

import com.example.model.dto.HouseInfo;

public interface HouseInfoService {
	public List<HouseInfo> getHouseInfoListByDongCode(String dongCode);
	
	public HouseInfo getHouseInfoByAptCode(int aptCode);

	public List<HouseInfo> getHouseInfoByAptName(String aptName);

	int getDistToSubWay(double y, double x);

	List<HouseInfo> getHouseInfoAll();

	boolean updateSubWayNameAll();

	boolean updateDistToSubWayAll();

	String getSubWayName(double y, double x);

	int getConvstoreCnt(double y, double x);

	boolean updateConvstoreCntAll();

	boolean updateRestaurantCntAll();

	int getRestaurantCnt(double y, double x);

	boolean updateCafeCntAll();

	int getCafeCnt(double y, double x);
}
