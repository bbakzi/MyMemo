package com.sparta.hanghaememo.service;


import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    //게시글 입력
    @Transactional
    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);

        return new MemoResponseDto(memo);
    }
    //게시글 전체 조회
    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemos() {
        List<Memo> memos = memoRepository.findAllByOrderByModifiedAtDesc();

        return memos.stream().map(memo -> new MemoResponseDto(memo)).toList();
    //list는 몇개 부터 list가 null 부터 null 상태에서 조회를 하면 오류가 아니라 아무것도 안나온다는 것
        //과제 :  for 를 통해서 리스트에 값을 일일이 담아서 전체 조회 하는 방법. "response Entity 검색하기" -김승균매니저님 과제-
    }
    //게시글 한개 조회
    @Transactional(readOnly = true)
    public MemoResponseDto getMemo(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return new MemoResponseDto(memo);
    }
    //게시글 수정
    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto requestDto) {

        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
        if (!memo.getPassword().equals(requestDto.getPassword())) {
            memoResponseDto.setMsg("업데이트 실패");
            return memoResponseDto;
        } else {
            memo.update(requestDto);
            memoResponseDto = new MemoResponseDto(memo);
            memoResponseDto.setMsg("업데이트 성공");
            return memoResponseDto;
        }
    }

    // 게시글 삭제
    @Transactional
    public String deleteAll(Long id, String password) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        if (memo.getPassword().equals(password)) { //password를 string 값으로 받았기 때문에 =은 안되고 equals로 비교
            memoRepository.deleteById(id);
            return "삭제 성공"; // 다른 값들은 나오지 않고 삭제성공 msg 만 return하기 위해!
        } else {
            return "비밀번호가 다릅니다.";
        }

    }
}

       /* -> 원래 하려고 했던 게시글 수정
            @Transactional
    public ResponseEntity<?> update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (memo.getPassword().equals(requestDto.getPassword())) {
            memo.update(requestDto);
            return ResponseEntity.ok(new MemoResponseDto(memo));
        } else {
            return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
            //오류 상태코드를 입력오류니 다른 오류를 찾지 말라고 알려주는 것! 리소스 낭비 방지. 입력오류라는 뜻!
        }
    }
        */

/* -> 원래 하려고 했던 삭제
    @Transactional
    public MemoResponseDto deleteAll(Long id, String password) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글 존재하지 않습니다.")
        );
        if (memo.getPassword().equals(password)) {
            memoRepository.deleteById(id);
            return new MemoResponseDto("삭제되었습니다.", HttpStatus.OK.value());//.value 왜 쓸까?
        } else
            return new MemoResponseDto("비밀번호가 다릅니다.", HttpStatus.UNAUTHORIZED.value());//.value 왜 쓸까?
    }
}
 */
