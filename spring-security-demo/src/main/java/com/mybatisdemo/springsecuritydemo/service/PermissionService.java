package com.mybatisdemo.springsecuritydemo.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("ss")
public class PermissionService
{
    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermi(String permission) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if("system:user:test".equals(permission)){
            return true;
        }
        throw new Exception("出现授权异常，没有权限。。。");
    }

}