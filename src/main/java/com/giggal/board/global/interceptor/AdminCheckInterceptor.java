package com.giggal.board.global.interceptor;

import com.giggal.board.domain.admin.mapper.AdminMapper;
import com.giggal.board.domain.member.entity.Member;
import com.giggal.board.domain.member.repository.MemberRepository;
import com.giggal.board.global.exception.member.NotFoundMemberId;
import com.giggal.board.global.jwt.util.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class AdminCheckInterceptor implements HandlerInterceptor {

    private final AdminMapper adminMapper;
    private final JwtTokenizer jwtTokenizer;
    private final MemberRepository memberRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String token = request.getHeader("Authorization");


        Long memberId = jwtTokenizer.getUserIdFromToken(token);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberId(memberId));


        log.info("memberId:" + memberId);
        log.info("memberCountry" + member.getCountryIsoCode());

        boolean result = adminMapper.findAllAdmin().stream()
                .noneMatch(
                        adminDto ->
                                adminDto.getAdminIp().equals(member.getIp())
                                ||
                                adminDto.getAdminCountry().equals(member.getCountryIsoCode())
                );

        log.info("result:" + result);
        return result;
    }
}
