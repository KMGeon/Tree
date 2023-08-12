package study.querydsl.HAHA;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.querydsl.HAHA.dto.BoardSearchCondition;
import study.querydsl.HAHA.dto.BoardUserResponse;
import study.querydsl.HAHA.repository.BoardRepository;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final BoardRepository boardRepository;

    @GetMapping("/test/v1")
    public Page<BoardUserResponse>test(BoardSearchCondition condition, Pageable pageable){
        return boardRepository.searchPageComplex(condition,pageable);
    }
}
