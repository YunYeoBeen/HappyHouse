package com.example.model.dto;

import java.sql.Date;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@ApiModel(value = "BoardDto(공지사항 정보)", description = "공지사항의 번호, 제목, 내용, 마지막 수정일, 작성자ID, 조회수")
public class Board {
    private int no;
    private String title;
    private String content;
    private Date lastModified;
    private String memberId;
    private int readcnt;
}
