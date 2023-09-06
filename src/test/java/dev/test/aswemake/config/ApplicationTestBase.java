package dev.test.aswemake.config;

import dev.test.aswemake.domain.repository.MemberRepository;
import dev.test.aswemake.domain.repository.RoleRepository;
import dev.test.aswemake.domain.service.MemberService;
import dev.test.aswemake.global.jwt.util.JwtTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootTest
public class ApplicationTestBase {
    @Autowired
    protected MemberRepository memberRepository;
    @Autowired
    protected RoleRepository roleRepository;
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected JwtTokenizer jwtTokenizer;
    @Autowired
    protected MemberService memberService;
}
