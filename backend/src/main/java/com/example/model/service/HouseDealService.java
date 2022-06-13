package com.example.model.service;

import java.util.List;

import com.example.model.dto.HouseDeal;

public interface HouseDealService{
	
	public List<HouseDeal> getHouseDealList(int aptCode);
	
	public int getDealAmountAvg(int aptCode);

}
