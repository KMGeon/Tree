package com.giggal.board.domain.admin.mapper;

import com.giggal.board.domain.admin.dto.response.AdminDto;
import com.giggal.board.domain.admin.dto.request.AdminRequestDto;
import com.giggal.board.domain.admin.dto.response.MemberAdminResponse;
import com.giggal.board.domain.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    void blockCountry(AdminRequestDto adminRequestDto);

    List<AdminDto> findAllAdmin();

    List<MemberAdminResponse> findMember();

    MemberAdminResponse findMemberByEmail(String memberEmail);
}
