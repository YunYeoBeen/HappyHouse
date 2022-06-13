package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.dto.User;
import com.example.model.service.JwtServiceImpl;
import com.example.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@Api("User Controller V6")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private UserService userService;

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    @ApiOperation(value = "유저 회원가입", notes = "유저의 입력 내용에 따라 회원가입")
    private ResponseEntity<?> register(@RequestBody User user) throws Exception {
        boolean check = userService.register(user);
        if (check) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping
    @ApiOperation(value = "유저 정보 수정", notes = "회원정보 클릭 후 수정 가능")
    private ResponseEntity<?> memberModify(@RequestBody User user) {
        if (userService.update(user)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "유저 회원탈퇴", notes = "유저 회원정보란에서 회원탈퇴 가능")
    private ResponseEntity<?> memberDelete(@PathVariable String id) {
        if (userService.delete(id)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User memberDto) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        System.out.println("memberDto : " + memberDto.toString());
        User loginUser = userService.login(memberDto.getId(), memberDto.getPw());
        System.out.println(memberDto.getId());
        System.out.println(memberDto.getPw());
        System.out.println(loginUser);
        if (loginUser == null) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<Map<String, Object>>(resultMap, status);
        }
        String token = jwtService.create("userid", loginUser.getId(), "access-token");// key, data, subject

        logger.debug("로그인 토큰정보 : {}", token);
        resultMap.put("access-token", token);
        resultMap.put("message", SUCCESS);
        status = HttpStatus.ACCEPTED;
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
    @GetMapping("/info/{userid}")
    public ResponseEntity<Map<String, Object>> getInfo(
            @PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
            HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            logger.info("사용 가능한 토큰!!!");
            try {
//				로그인 사용자 정보.
                User memberDto = userService.userInfo(userid);
                resultMap.put("userInfo", memberDto);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                logger.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            logger.error("사용 불가능 토큰!!!");
            resultMap.put("message", FAIL);
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
