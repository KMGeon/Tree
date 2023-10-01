package com.example.devproject;

import org.hibernate.event.spi.PostUpdateEventListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String sound1() {
        return "Hello";
    }

    @PostMapping("/save")
    public void save() {
        Member member = Member.builder()
                .name("김무건")
                .build();
        memberRepository.save(member);
    }

    @GetMapping("/member")
    public Member getMember() {
        return memberRepository.findAll().stream()
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
