/*MemberService

package com.example.sparta.service;

import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.entity.Memo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 한명 조회
    @Transactional (readOnly = true)
    public com.example.sparta.entity.MemberResponseDto findMember(Long id) {
        Member member = memoRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("회원 상세조회 실패")
        );
        return new com.example.sparta.entity.MemberResponseDto(member);
    }

    //회원 모두 조회
    @Transactional (readOnly = true)
    public List<com.example.sparta.entity.MemberResponseDto> findAllMember() {
        List<member> members = memoRepository.findAllByOrderByModifiedAtDesc();

        return members.stream().map(member -> new MemoResponseDto(member)).toList();
    }

}