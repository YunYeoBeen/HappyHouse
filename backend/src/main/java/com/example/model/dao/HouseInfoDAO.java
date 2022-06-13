package com.example.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.dto.HouseInfo;

@Mapper
public interface HouseInfoDAO {
	public List<HouseInfo> searchHouseInfos(String dongCode);
	
	public HouseInfo selectHouseInfo(int aptCode);

	public List<HouseInfo> selectHouseInfoByName(String aptName);

	public List<HouseInfo> selectHouseInfoAll();

	public void updateDistFromSubway(HouseInfo houseInfo);

	public void updateSubwayName(HouseInfo houseInfo);

	public void updateConvStoreCnt(HouseInfo houseInfo);

	public void updateRestaurantCnt(HouseInfo houseInfo);

	public void updateCafeCnt(HouseInfo houseInfo);
}
