package com.schedule.todo.domain.admin.controller;

import com.schedule.todo.domain.admin.dto.GeoLocationDto;
import com.schedule.todo.domain.admin.application.GeoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class GeoRestController {

    private final GeoService geoService;

    @GetMapping("/view")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GeoLocationDto> city() {

        InetAddress ipAddress = getIpAddress();
        GeoLocationDto geoLocationDto = geoService.findLocation(ipAddress);

        return ResponseEntity.ok(geoLocationDto);
    }

    @PostMapping("/blockCountry")
    public void addCountryOfBlock() {
        InetAddress ipAddress = getIpAddress();
        
    }

    public static InetAddress getIpAddress() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String ip = req.getHeader("X-FORWARDED-FOR");

        if (ip == null || ip.isEmpty()) {
            ip = req.getRemoteAddr();
        }
        if (ip == null || ip.isEmpty()) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty()) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty()) {
            ip = req.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty()) {
            ip = req.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty()) {
            ip = req.getRemoteAddr();
        }
        if (ip == null || ip.isEmpty()) {
            throw new RuntimeException();
        }

        InetAddress ipAddress = null;
        try {
            ipAddress = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        log.info(">>>" + ipAddress);
        return ipAddress;
    }
}