package com.sparta.hanghaememo.controller;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    //게시글 입력
    @PostMapping("/post")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){
        return memoService.createMemo(requestDto);
    }

    //게시글 전체조회
    @GetMapping("/get")
        public List<MemoResponseDto> getMemos() {
        return memoService.getMemos();
    }

    //게시글 하나 조회
    @GetMapping("/get/{id}")
    public MemoResponseDto getMemo(@PathVariable Long id) {
        return memoService.getMemo(id);
    }

    //게시글 수정
    @PutMapping("/put/{id}")
    public MemoResponseDto update(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.update(id, requestDto);
    }

    //게시글 삭제
    @DeleteMapping("/delete/{id}")
    public String deleteAll(@PathVariable Long id, @RequestBody String password) {
        // RequestBody로 password를 입력받으면 그 값을 비교해서 삭제시킴
        return memoService.deleteAll(id,password);
    }
}
