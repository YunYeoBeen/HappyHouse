package com.example.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.dao.HouseDealDAO;
import com.example.model.dto.HouseDeal;

@Service
public class HouseDealServiceImpl implements HouseDealService{
	@Resource
	private HouseDealDAO housedealdao;
	
	@Autowired
	public void setHousedeal(HouseDealDAO housedealdao) {
		this.housedealdao = housedealdao;
	}
	
	@Override
	public List<HouseDeal> getHouseDealList(int aptCode){
		return housedealdao.selectHouseDealList(aptCode);
	}
	
	@Override
	public int getDealAmountAvg(int aptCode) {
		return housedealdao.selectDealAmountAvg(aptCode);
	}
}
