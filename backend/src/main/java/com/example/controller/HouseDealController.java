package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.dto.HouseDeal;
import com.example.model.service.HouseDealService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/apt")
@Api("HouseDeal Controller V6")
public class HouseDealController {
	private HouseDealService housedealservice;
	
	@Autowired
	public void setHousedealservice(HouseDealService housedealservice) {
		this.housedealservice = housedealservice;
	}

	@GetMapping("/{aptCode}")
	@ApiOperation(value="아파트 세부사항 조회", notes= "아파트 목록에서 아파트 클릭 후 아파트 세부사항 조회 가능")	
	private ResponseEntity<?> aptDetail(@PathVariable("aptCode")  int aptcode) {
		try {
			List<HouseDeal> houseDealList = housedealservice.getHouseDealList(aptcode);
			return new ResponseEntity<List<HouseDeal>>(houseDealList,HttpStatus.OK);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/deal/avg/{aptCode}")
	@ApiOperation(value="아파트 평균 매매가 조회", notes= "아파트 목록에서 아파트 클릭 후 아파트 세부사항 조회 가능")	
	private ResponseEntity<?> getDealAmountAvg(@PathVariable("aptCode")  int aptcode) {
		try {
			int avg = housedealservice.getDealAmountAvg(aptcode);
			return new ResponseEntity<Integer>(avg,HttpStatus.OK);
		} catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
