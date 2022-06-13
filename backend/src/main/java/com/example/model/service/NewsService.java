package com.example.model.service;

import com.example.model.dto.News;

import java.util.List;

public interface NewsService {
    List<News> getNewsList(int news_office_checked);
}
