package com.giggal.board.domain.admin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MemberAdminResponse {
    private Long member_id;
    private String city_name;
    private String continent_name;
    private String country_iso_code;
    private String country_name;
    private String member_email;
    private String ip;
    private String member_name;
    private String password;
    private String subdivision_name;
}
