package com.example.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import nonapi.io.github.classgraph.json.Id;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
@ApiModel(value = "UserDto(유저 정보)", description = "유저의 ID, PassWord, 이름, 주소, 전화번호")
public class User {
    private String id;
    private String pw;
    private String name;
    private int zonecode;
    private String address;
    private String detail_address;
    private String phone;
}
