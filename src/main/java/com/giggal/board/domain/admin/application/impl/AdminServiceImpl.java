package com.giggal.board.domain.admin.application.impl;

import com.giggal.board.domain.admin.application.AdminService;
import com.giggal.board.domain.admin.dto.request.AdminRequestDto;
import com.giggal.board.domain.admin.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;



    @Override
    @Transactional
    public void blockAccessCountry(AdminRequestDto adminRequestDto) {
        adminMapper.blockCountry(adminRequestDto);
    }

}
