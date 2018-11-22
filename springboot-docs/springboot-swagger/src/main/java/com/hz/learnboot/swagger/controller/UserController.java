package com.hz.learnboot.swagger.controller;

import com.hz.learnboot.swagger.domain.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.List;

/**
 * 在控制器中描述文档
 *
 * @Author hezhao
 * @Time 2018-06-28 22:54
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户接口",description="关于用户的增删改查接口")
public class UserController {

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "java.lang.Long", name = "id", value = "id", required = true, paramType = "path"),
            @ApiImplicitParam(dataType = "User", name = "user", value = "用户信息", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 500, message = "接口异常")
    })
    @PostMapping(value = "/{id}")
    public User insert(@PathVariable Long id, @RequestBody User user) {
        System.out.println("id:" + id + ", user:" + user);
        user.setId(id);
        return user;
    }

    @ApiOperation(value = "获取指定id用户详细信息", notes = "根据user的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "String", paramType = "path")
    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Long id) {
        return User.builder().id(id).username("abc").password("12345").build();
    }

    @ApiOperation(value = "获取所有用户详细信息", notes = "获取用户列表信息")
    @GetMapping(value = "/users")
    public List<User> getUserList() {
        User user = User.builder().id(15L).username("ricky").password("root").build();
        return Arrays.asList(user);
    }

    @ApiIgnore // 忽略该方法
    @ApiOperation(value = "删除指定id用户", notes = "根据id来删除用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "java.lang.Long", paramType = "path")
    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Long id) {
        System.out.println("delete user:" + id);
        return "OK";
    }

}
