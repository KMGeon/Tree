package com.giggal.board.domain.admin.controller;

import com.giggal.board.domain.admin.application.AdminService;
import com.giggal.board.domain.admin.dto.response.GeoLocationDto;
import com.giggal.board.domain.admin.dto.request.AdminRequestDto;
import com.giggal.board.domain.admin.dto.response.MemberAdminResponse;
import com.giggal.board.domain.admin.mapper.AdminMapper;
import com.giggal.board.common.GeoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.List;

import static com.giggal.board.domain.admin.entity.Admin.getIpAddress;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class GeoRestController {

    private final GeoService geoService;
    private final AdminService adminService;
    private final AdminMapper adminMapper;

    @GetMapping("/view")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GeoLocationDto> city() {

        InetAddress ipAddress = getIpAddress();
        GeoLocationDto geoLocationDto = geoService.findLocation(ipAddress);

        return ResponseEntity.ok(geoLocationDto);
    }

    @PostMapping("/blockCountry")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCountryOfBlock(
             @RequestBody AdminRequestDto adminRequestDto
    ) {
        adminService.blockAccessCountry(adminRequestDto);
    }

    @GetMapping("/members/list")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberAdminResponse> findAllMemberList() {
        return adminMapper.findMember();
    }

    @GetMapping("/member/detail")
    @ResponseStatus(HttpStatus.OK)
    public MemberAdminResponse MemberDetail(
            @RequestParam String memberEmail
    ) {
        return adminMapper.findMemberByEmail(memberEmail);
    }

}