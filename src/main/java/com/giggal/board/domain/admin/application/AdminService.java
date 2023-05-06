package com.schedule.todo.domain.admin.application;

import com.schedule.todo.domain.admin.dto.MemberSignResponse;

import java.net.InetAddress;

public interface AdminService {
    void blockAccessCountry(InetAddress ipAddress);
    MemberSignResponse findMemberAll();
}
