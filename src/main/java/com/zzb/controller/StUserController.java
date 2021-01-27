package com.zzb.controller;

import com.zzb.VO.LoginVO;
import com.zzb.entity.Result;
import com.zzb.entity.ResultCode;
import com.zzb.entity.StUser;
import com.zzb.service.StUserService;
import com.zzb.utils.JWTUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (StUser)表控制层
 *
 * @author zzbang
 * @since 2021-01-25 13:38:53
 */
@RestController
@RequestMapping("stUser")
public class StUserController {
    /**
     * 服务对象
     */
    @Resource
    private StUserService stUserService;



    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public StUser selectOne(Integer id) {
        return this.stUserService.queryById(id);
    }

    @GetMapping("selectAll")
    @RequiresRoles("admin")
    public Result selectAll() {
        List<StUser> stUsers = stUserService.queryAll(new StUser());
        return Result.success(stUsers);
    }

    /**
     * 对账号密码进行校验，成功登录返回token、用户名以及角色
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login")
    public Result login(String username,String password){
        StUser user=stUserService.queryBySF(username);
        String salt=user.getSalt();
        String md5password = new Md5Hash(password, salt, 1024).toHex();
        System.out.println(md5password);
        if(user.getPassword().equals(md5password)){
            String token = JWTUtils.sign(username, md5password);
            LoginVO loginVO = new LoginVO();
            loginVO.setUsername(user.getUsername()).setToken(token).setAdmin(user.getAdmin());
            return Result.success(loginVO);
        }else {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }

    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result unauthorized() {
        return  Result.failure(ResultCode.USER_NOT_LOGGED_IN);
    }

}