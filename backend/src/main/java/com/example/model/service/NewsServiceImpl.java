package com.example.model.service;

import com.example.model.dto.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Override
    public List<News> getNewsList(int news_office_checked) {
        List<News> newsDtoList = new ArrayList<>();
        String url = "https://search.naver.com/search.naver?where=news&query=%EC%95%84%ED%8C%8C%ED%8A%B8&sm=tab_opt&sort=1&photo=0&field=0&pd=0&ds=&de=&docid=&related=0&mynews=1&office_type=1&office_section_code=1&news_office_checked=" + news_office_checked + "&nso=so%3Add%2Cp%3Aall&is_sug_officeid=0";
        Document doc = null;

        try {
            //원하는 url에서 전체 구조를 받아온다
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements tests = doc.select(".news_area");        // 각 기사 객체 추출

        for (Element test : tests) {
            Elements elA = test.select("a"); // 링크, 제목 추출
            Elements elA2 = test.select(".info_group"); // 시간, 언론사 추출

            if (elA2.select("span[class=info]").text().split(" ").length > 2)
                newsDtoList.add(new News(elA.attr("title"), elA.get(0).attr("data-url"), elA2.select("a").get(0).text().split(" ")[0], elA2.select("span[class=info]").text().split(" ", 3)[2]));
            else
                newsDtoList.add(new News(elA.attr("title"), elA.get(0).attr("data-url"), elA2.select("a").get(0).text().split(" ")[0], elA2.select("span[class=info]").text()));
        }
        return newsDtoList;
    }
}
