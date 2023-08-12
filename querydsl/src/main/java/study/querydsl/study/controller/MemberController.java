package study.querydsl.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.querydsl.study.dto.MemberSearchCondition;
import study.querydsl.study.dto.MemberTeamDto;
import study.querydsl.study.repository.MemberJpaRepository;
import study.querydsl.study.repository.MemberRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition) {
        return memberJpaRepository.search(condition);
    }

    @GetMapping("/v2/members")
    public Page<MemberTeamDto> searchMember2(MemberSearchCondition condition, Pageable pageable) {
        return memberRepository.searchPageSimple(condition,pageable);
    }
    @GetMapping("/v3/members")
    public Page<MemberTeamDto> searchMember3(MemberSearchCondition condition, Pageable pageable) {
        return memberRepository.searchPageComplex(condition,pageable);
    }
}
