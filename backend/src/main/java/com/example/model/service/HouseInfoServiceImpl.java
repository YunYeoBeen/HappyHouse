package com.example.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.dao.HouseInfoDAO;
import com.example.model.dto.HouseInfo;

@Service
public class HouseInfoServiceImpl implements HouseInfoService {

    @Resource
    private HouseInfoDAO houseinfodao;
    private static final String appkey = "ea230da389b482136cbefa747a2f38d4";

    @Autowired
    public void setHouseinfo(HouseInfoDAO houseinfodao) {
        this.houseinfodao = houseinfodao;
    }

    @Override
    public List<HouseInfo> getHouseInfoListByDongCode(String dongCode) {
        return houseinfodao.searchHouseInfos(dongCode);
    }

    @Override
    public HouseInfo getHouseInfoByAptCode(int aptCode) {
        // TODO Auto-generated method stub
        return houseinfodao.selectHouseInfo(aptCode);
    }

    @Override
    public List<HouseInfo> getHouseInfoByAptName(String aptName) {
        return houseinfodao.selectHouseInfoByName(aptName);
    }

    @Override
    public List<HouseInfo> getHouseInfoAll() {
        return houseinfodao.selectHouseInfoAll();
    }

    // Todo: 추후 들어올 데이터는 get dist, name 두가지 작업을 한번에 해야함
    @Override
    public boolean updateDistToSubWayAll() {
        List<HouseInfo> houseList = houseinfodao.selectHouseInfoAll();
        for (HouseInfo houseInfo : houseList) {
            houseInfo.setDistFromSubway(getDistToSubWay(houseInfo.getLat(), houseInfo.getLng()));
            houseinfodao.updateDistFromSubway(houseInfo);
        }
        return true;
    }

    // Todo: 추후 들어올 데이터는 get dist, name 두가지 작업을 한번에 해야함
    @Override
    public boolean updateSubWayNameAll() {
        List<HouseInfo> houseList = houseinfodao.selectHouseInfoAll();
        for (HouseInfo houseInfo : houseList) {
            houseInfo.setSubwayName(getSubWayName(houseInfo.getLat(), houseInfo.getLng()));
            houseinfodao.updateSubwayName(houseInfo);
        }
        return true;
    }

    @Override
    public int getDistToSubWay(double y, double x) {
        try {
            String apiURL = "https://dapi.kakao.com/v2/local/search/category.json?category_group_code=SW8&x=" + x + "&y=" + y + "&radius=5000&sort=distance";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Authorization", "KakaoAK " + appkey);
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            BufferedReader br;

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            /* String으로 데이터 받기 */
            /* 데이터를 JSONObject로 변환 */
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.toString());
            /* JSONObject에서 key 추출 */
            JSONArray documents = (JSONArray) jsonObject.get("documents");
            JSONObject document = (JSONObject) documents.get(0);
            int dist = Integer.parseInt(document.get("distance").toString());
            return dist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public String getSubWayName(double y, double x) {
        try {
            String apiURL = "https://dapi.kakao.com/v2/local/search/category.json?category_group_code=SW8&x=" + x + "&y=" + y + "&radius=5000&sort=distance";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Authorization", "KakaoAK " + appkey);
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            BufferedReader br;

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            /* String으로 데이터 받기 */
            /* 데이터를 JSONObject로 변환 */
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.toString());
            /* JSONObject에서 key 추출 */
            JSONArray documents = (JSONArray) jsonObject.get("documents");
            JSONObject document = (JSONObject) documents.get(0);
            String name = document.get("place_name").toString();
            return name;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "비역세권";
    }
	
	@Override
	public boolean updateConvstoreCntAll() {
		List<HouseInfo> houseList = houseinfodao.selectHouseInfoAll();
		for (HouseInfo houseInfo : houseList) {
			houseInfo.setConvStoreCnt(getConvstoreCnt(houseInfo.getLat(),houseInfo.getLng()));
			houseinfodao.updateConvStoreCnt(houseInfo);
		}
		return true;
	}
	
	@Override
	public int getConvstoreCnt(double y,double x) {
        try {
            String apiURL = "https://dapi.kakao.com/v2/local/search/category.json?category_group_code=CS2&x=" + x + "&y=" + y + "&radius=100";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Authorization", "KakaoAK " + appkey);
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            BufferedReader br;

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            /* String으로 데이터 받기 */
            /* 데이터를 JSONObject로 변환 */
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.toString());
            /* JSONObject에서 key 추출 */
            JSONObject meta = (JSONObject) jsonObject.get("meta");
            int cnt = Integer.parseInt(meta.get("total_count").toString());
            return cnt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
	
	@Override
	public boolean updateRestaurantCntAll() {
		List<HouseInfo> houseList = houseinfodao.selectHouseInfoAll();
		for (HouseInfo houseInfo : houseList) {
			houseInfo.setRestaurantCnt(getRestaurantCnt(houseInfo.getLat(),houseInfo.getLng()));
			houseinfodao.updateRestaurantCnt(houseInfo);
		}
		return true;
	}
	
	@Override
	public int getRestaurantCnt(double y,double x) {
        try {
            String apiURL = "https://dapi.kakao.com/v2/local/search/category.json?category_group_code=FD6&x=" + x + "&y=" + y + "&radius=200";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Authorization", "KakaoAK " + appkey);
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            BufferedReader br;

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            /* String으로 데이터 받기 */
            /* 데이터를 JSONObject로 변환 */
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.toString());
            /* JSONObject에서 key 추출 */
            JSONObject meta = (JSONObject) jsonObject.get("meta");
            int cnt = Integer.parseInt(meta.get("total_count").toString());
            return cnt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
	
	@Override
	public boolean updateCafeCntAll() {
		List<HouseInfo> houseList = houseinfodao.selectHouseInfoAll();
		for (HouseInfo houseInfo : houseList) {
			houseInfo.setCafeCnt(getCafeCnt(houseInfo.getLat(),houseInfo.getLng()));
			houseinfodao.updateCafeCnt(houseInfo);
		}
		return true;
	}
	
	@Override
	public int getCafeCnt(double y,double x) {
        try {
            String apiURL = "https://dapi.kakao.com/v2/local/search/category.json?category_group_code=CE7&x=" + x + "&y=" + y + "&radius=200";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Authorization", "KakaoAK " + appkey);
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            BufferedReader br;

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            /* String으로 데이터 받기 */
            /* 데이터를 JSONObject로 변환 */
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.toString());
            /* JSONObject에서 key 추출 */
            JSONObject meta = (JSONObject) jsonObject.get("meta");
            int cnt = Integer.parseInt(meta.get("total_count").toString());
            return cnt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
