package com.example.controller;

import java.util.List;

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

import com.example.model.dto.Qna;
import com.example.model.service.CommentService;
import com.example.model.service.QnaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/qna")
public class QnaController {

    private QnaService qnaService;
    private CommentService commentService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    public void setQnaservice(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    private ResponseEntity<?> InsertQna(@RequestBody Qna qna) {
        if (qnaService.insertQna(qna)) {
            List<Qna> list = qnaService.selectQnaList();
            return new ResponseEntity<List<Qna>>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    private ResponseEntity<?> QnaList() {
        List<Qna> QnaList = qnaService.selectQnaList();
        for (Qna qna : QnaList) {
            int cnt = commentService.commentTotal(qna.getId());
            qna.setTotalComment(cnt);
            System.out.println(qna.toString());
        }
        // 오류나면 여기 한번 확인
        if (QnaList != null) {
            return new ResponseEntity<List<Qna>>(QnaList, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> QnaDetail(@PathVariable(value = "id") int id) {
        qnaService.readQna(id);
        Qna Qnadetail = qnaService.selectQna(id);
        return new ResponseEntity<Qna>(Qnadetail, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> QnaDelete(@PathVariable(value = "id") int id) {
        qnaService.deleteQna(id);
        if (qnaService.deleteQna(id)) {
            List<Qna> list = qnaService.selectQnaList();
            return new ResponseEntity<List<Qna>>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> QnaUpdate(@RequestBody Qna qna, @PathVariable("id") int id) {
        qna.setId(id);
        if (qnaService.updateQna(qna)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

}
