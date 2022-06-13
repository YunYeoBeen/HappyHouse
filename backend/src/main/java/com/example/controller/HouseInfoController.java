package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.dto.HouseInfo;
import com.example.model.service.HouseInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/apt")
@Api("HouseInfo Controller V6")
public class HouseInfoController {
	private HouseInfoService houseinfoservice;
	@Autowired
	public void setHouseinfoservice(HouseInfoService houseinfoservice) {
		this.houseinfoservice = houseinfoservice;
	}
	
	
	@GetMapping("/dongcode/{dongCode}")
	@ApiOperation(value="동코드으로 아파트 목록 조회", notes= "해당 동코드에 맞는 아파트 목록조회")
	private ResponseEntity<?> aptList(@PathVariable String dongCode) {
		try {
			List<HouseInfo> houseInfoList = houseinfoservice.getHouseInfoListByDongCode(dongCode);
			return new ResponseEntity<List<HouseInfo>>(houseInfoList,HttpStatus.OK);
		}catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/name/{aptName}")
	@ApiOperation(value="아파트명으로 아파트 목록 조회", notes= "해당 아파트명에 맞는 아파트 목록조회")
	private ResponseEntity<?> aptListName(@PathVariable String aptName) {
		try {
			List<HouseInfo> houseInfoList = houseinfoservice.getHouseInfoByAptName(aptName);
			return new ResponseEntity<List<HouseInfo>>(houseInfoList,HttpStatus.OK);
		}catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	@GetMapping
	@ApiOperation(value="아파트 리스트 모두 출력")
	private ResponseEntity<?> getAptList() {
		try {
			List<HouseInfo> houseInfoList = houseinfoservice.getHouseInfoAll();
			return new ResponseEntity<List<HouseInfo>>(houseInfoList,HttpStatus.OK);
		}catch(Exception e){
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/subway/dist")
	@ApiOperation(value="db모든 값 역간최단거리 계산후 거리 db에 업데이트")
	private boolean setSubWayDistAll() {
		try {
			houseinfoservice.updateDistToSubWayAll();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@PutMapping("/subway/name")
	@ApiOperation(value="db모든 값 가까운역 이름 추출후 db에 업데이트")
	private boolean setSubWayNameAll() {
		try {
			houseinfoservice.updateSubWayNameAll();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@PutMapping("/conv/cnt")
	@ApiOperation(value="db모든 값 100m 근방 편의점 개수 업데이트")
	private boolean setConvstoreCntAll() {
		try {
			houseinfoservice.updateConvstoreCntAll();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@PutMapping("/restaurant/cnt")
	@ApiOperation(value="db모든 값 200m 근방 음식점 개수 업데이트")
	private boolean setRestaurantCntAll() {
		try {
			houseinfoservice.updateRestaurantCntAll();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@PutMapping("/cafe/cnt")
	@ApiOperation(value="db모든 값 200m 근방 카페 개수 업데이트")
	private boolean setCafeCntAll() {
		try {
			houseinfoservice.updateCafeCntAll();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
}
