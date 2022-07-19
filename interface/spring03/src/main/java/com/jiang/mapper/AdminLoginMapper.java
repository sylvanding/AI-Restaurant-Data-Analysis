package com.jiang.mapper;

import com.jiang.pojo.Admin;

public interface AdminLoginMapper {

    Admin getAdminByUsername(String username);
}
