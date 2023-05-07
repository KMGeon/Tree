package com.giggal.board.domain.admin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequestDto {
    private Long adminId;
    private String adminIp;
    private String adminCountry;
}
