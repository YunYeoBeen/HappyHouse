package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.dto.Board;
import com.example.model.service.BoardService;
import com.example.model.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("/board")
@Api("Board controller V6")
public class BoardController {

    private BoardService boardService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/top")
    private ResponseEntity<?> selectBoardTop() {
    	List<Board> list = boardService.selectBoardTop();
    	return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
    }

    @GetMapping("/{no}")
    @ApiOperation(value = "세부 공지사항 조회", notes = "공지사항 <big>클릭시 세부 공지사항 조회 가능")
    private ResponseEntity<?> boardDetail(@PathVariable("no") int no) {
        boardService.readBoard(no);
        Board board = boardService.selectBoard(no);
        return new ResponseEntity<Board>(board, HttpStatus.OK);
    }


    @DeleteMapping("/{no}")
    @ApiOperation(value = "공지사항 삭제", notes = "공지사항 <big>클릭 후 삭제 버튼 클릭")
    private ResponseEntity<?> boardDelete(@PathVariable("no") int boardNo) {
        if (boardService.deleteBoard(boardNo)) {
            List<Board> list = boardService.selectBoardList();
            return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }


    @ApiOperation(value = "공지사항 수정", notes = "공지사항 <big>클릭 후 수정 버튼 클릭")
    @PutMapping("/{no}")
    private ResponseEntity<String> updateBoard(@RequestBody Board board, @PathVariable("no") int no) {
        board.setNo(no);
        if (boardService.updateBoard(board)) {
            System.out.println("수정완료");
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        System.out.println("수정실패");
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }


    @PostMapping
    @ApiOperation(value = "공지사항 등록", notes = "공지사항 등록 가능")
    private ResponseEntity<?> boardInsert(@RequestBody Board board) {
        if (board.getMemberId().equals("admin001")) {
            boardService.insertBoard(board);
            List<Board> list = boardService.selectBoardList();
            return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    @ApiOperation(value = "조건에 맞는 공지사항 조회", notes = "option 선택 후 원하는 keyword 입력 --> 조건에 맞는 공지사항 목록 조회")
    public ResponseEntity<?> selectBoardListByName(// RequestParam으로 옵션, 키워드, 페이지의 기본값을 각각 설정해준다.
                                                   @RequestParam("option") String option, // 기본 검색 옵션값을 작성자로 한다.
                                                   @RequestParam("keyword") String keyword) throws Exception {

        System.out.println(option + " " + keyword);
        List<Board> list = boardService.selectBoardListByName(option, keyword);
        if (list != null) {
            return new ResponseEntity<List<Board>>(list, HttpStatus.OK); // 게시판 페이지로 이동
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

}
