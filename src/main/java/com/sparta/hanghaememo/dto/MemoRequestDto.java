package com.sparta.hanghaememo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class MemoRequestDto {
    private String name;
    private  String contents;
    private  String title;
    private  String password; //문자열이 있을 수도 있으니 Long 보다 String 효율적이다.
}
