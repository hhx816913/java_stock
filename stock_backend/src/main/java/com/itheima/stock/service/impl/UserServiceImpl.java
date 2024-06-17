package com.itheima.stock.service.impl;

import com.itheima.stock.mapper.SysUserMapper;
import com.itheima.stock.pojo.entity.SysUser;
import com.itheima.stock.service.UserService;
import com.itheima.stock.vo.req.LoginReqVo;
import com.itheima.stock.vo.resp.LoginRespVo;
import com.itheima.stock.vo.resp.R;
import com.itheima.stock.vo.resp.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //根据用户名查询id信息
    @Override
    public SysUser findByUserName(String username) {
        return sysUserMapper.findUserInfoByUserName(username);
    }
    //实现用户登录功能
    @Override
    public R<LoginRespVo> login(LoginReqVo vo) {
        //1、判断参数是否合法
        if (vo == null || StringUtils.isBlank(vo.getUsername()) || StringUtils.isBlank(vo.getPassword()) || StringUtils.isBlank(vo.getUsername())) {
            return R.error(ResponseCode.DATA_ERROR);
        }
        //2、根据用户名去数据库查询用户信息，获取密码的密文
        SysUser dbuser = sysUserMapper.findUserInfoByUserName(vo.getUsername());
        if (dbuser == null){
            return R.error(ResponseCode.ACCOUNT_EXISTS_ERROR);
        }

        //3、调用密码匹配器匹配输入的明文密码和数据库的密文密码
        if (!passwordEncoder.matches(vo.getPassword(), dbuser.getPassword())){
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        //4、响应
        LoginRespVo respVo = new LoginRespVo();
//        respVo.setUsername(dbuser.getUsername());
//        respVo.setNickName(dbuser.getNickName());
//        respVo.setPhone(dbuser.getPhone());
//        respVo.setId(dbuser.getId());
        // LoginRespVo 与 Sysuser 的属性名称一致
        //必须保证属性名称和类型一致
        BeanUtils.copyProperties(dbuser, respVo);
        return R.ok(respVo);


    }

}
