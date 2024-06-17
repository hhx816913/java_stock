package com.itheima.stock.service;

import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;
import lombok.extern.slf4j.Slf4j;


public interface UserService {
    SysUser findByUserName(String username);
    //用户登录
    R<LoginRespVo> login(LoginReqVo vo);
}
