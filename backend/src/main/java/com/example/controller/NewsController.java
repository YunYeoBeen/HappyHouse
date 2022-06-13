package com.example.controller;

import com.example.model.dto.News;
import com.example.model.service.NewsService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/news")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/{news_office_checked}")
    public List<News> getNewsList(@PathVariable int news_office_checked) {
        return newsService.getNewsList(news_office_checked);
    }
}
