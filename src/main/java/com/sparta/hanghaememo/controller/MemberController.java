/*
MemberController

package com.example.sparta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final com.example.sparta.service.MemberService memberService;

    /*
     * 한 회원의 userId가 주었을때 회원 정보를 조회하는 API
     * @param id


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@GetMapping("/member/{id}")
    public com.example.sparta.entity.MemberResponseDto getMemberInfo(@PathVariable Long id) {
        return memberService.findMember(id);
    }

    /*
     * 회원의 전체 목록을 조회하는 API

    @GetMapping("/member")
    public List<com.example.sparta.entity.MemberResponseDto> getMemberList() {
        return memberService.findAllMember();
    }

}
