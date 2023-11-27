package com.giggal.board.domain.admin.application;


import com.giggal.board.domain.admin.dto.request.AdminRequestDto;

public interface AdminService {
    void blockAccessCountry(AdminRequestDto adminRequestDto);
}
