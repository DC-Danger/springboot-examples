package com.hz.learnboot.security.security;

import com.hz.learnboot.security.entity.SysPermission;
import com.hz.learnboot.security.entity.SysRole;
import com.hz.learnboot.security.entity.UserInfo;
import com.hz.learnboot.security.sevice.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息校验
 *
 * Created by hezhao on 2018-07-31 16:52
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询账号是否存在，是就返回一个UserDetails的对象，否就抛出异常！
        UserInfo user = userInfoService.findByUsername(username);

        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            for(SysRole role:user.getRoleList()){
                for(SysPermission p:role.getPermissions()){
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(String.valueOf(p.getId()));
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("UserName: " + username + " do not exist!");
        }
    }

}
