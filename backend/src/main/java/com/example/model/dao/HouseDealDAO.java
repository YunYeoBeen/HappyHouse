package com.example.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.dto.HouseDeal;

@Mapper
public interface HouseDealDAO {
	public List<HouseDeal> selectHouseDealList(int aptCode);
	
	public int selectDealAmountAvg(int aptCode);
}
