package com.hz.learnboot.security.dao;

import com.hz.learnboot.security.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 权限信息数据
 */
public interface SysPermissionDao extends JpaRepository<SysPermission,Integer> {

}