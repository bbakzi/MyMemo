package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MemoResponseDto { //TimeStamped 상속이 왜 안됨?
    //requestDto 로 뱉으면 모든 정보가 다 밖으로 나간다. responseDto 로 내가 보여주고 싶은 내용을 가공해서 보내준다!
    private Long id;  //final을 쓸수는 없는건가? 상수 와 변수의 이해
    private String name;
    private String title;
    private String contents;
    private String msg;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    //    private int statusCode;


    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.name = memo.getName();
        this.contents = memo.getContents();
        this.title = memo.getTitle();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
    }
}
