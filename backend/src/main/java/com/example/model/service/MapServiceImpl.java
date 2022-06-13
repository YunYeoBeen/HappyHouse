package com.example.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import com.example.model.dto.Location;

@Service
public class MapServiceImpl implements MapService {
    private static final String appkey = "ea230da389b482136cbefa747a2f38d4";

    @Override
    public Location getFocusLocation(String addr) {
        try {
            addr = URLEncoder.encode(addr, "UTF-8");
            String apiURL = "https://dapi.kakao.com/v2/local/search/address.json?query=" + addr;
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
            double x = Double.parseDouble(document.get("x").toString());
            double y = Double.parseDouble(document.get("y").toString());
            return new Location(x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Location(126.570667, 33.450701);
    }

}
