package com.giggal.board.domain.member.application;

import com.giggal.board.domain.admin.dto.response.GeoLocationDto;
import com.giggal.board.domain.member.dto.request.MemberLoginRequest;
import com.giggal.board.domain.member.dto.request.MemberSignupRequest;
import com.giggal.board.domain.member.dto.response.MemberLoginResponse;
import com.giggal.board.domain.member.dto.response.MemberSignupResponse;

public interface MemberService {
//    MemberSignupResponse signUp(MemberSignupRequest request);
    MemberSignupResponse signUp(MemberSignupRequest request, GeoLocationDto location);

    MemberLoginResponse login(MemberLoginRequest request);
}
