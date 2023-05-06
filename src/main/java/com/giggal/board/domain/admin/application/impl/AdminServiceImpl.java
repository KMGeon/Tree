package com.schedule.todo.domain.admin.application.impl;

import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Continent;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Subdivision;
import com.schedule.todo.domain.admin.application.AdminService;
import com.schedule.todo.domain.admin.dto.MemberSignResponse;
import com.schedule.todo.domain.admin.geoip.GeoReader;
import com.schedule.todo.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Service
public class AdminServiceImpl implements AdminService {

    private final GeoReader geoReader;
    private final MemberRepository memberRepository;

    public AdminServiceImpl(GeoReader geoReader, MemberRepository memberRepository) {
        this.geoReader = geoReader;
        this.memberRepository = memberRepository;
    }

    @Override
    public void blockAccessCountry(InetAddress ipAddress) {
        CityResponse response = geoReader.city(ipAddress);

        Subdivision subdivision = response.getMostSpecificSubdivision();
        City city = response.getCity();
        Continent continent = response.getContinent();
        Country country = response.getCountry();

    }

    @Override
    public MemberSignResponse findMemberAll() {

        return null;
    }
}
