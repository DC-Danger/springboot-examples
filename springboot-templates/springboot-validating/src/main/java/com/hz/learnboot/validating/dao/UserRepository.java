package com.hz.learnboot.validating.dao;

import com.hz.learnboot.validating.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/** 用户持久层操作接口
 *
 * Created by hezhao on 2018-06-29 09:16
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
