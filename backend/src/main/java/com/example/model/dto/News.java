package com.example.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@ApiModel(value = "NewsDto(뉴스정보)", description = "뉴스 제목, 링크, 언론사, 시간")
public class News {
    private String title;
    private String link;
    private String press;
    private String time;
}
