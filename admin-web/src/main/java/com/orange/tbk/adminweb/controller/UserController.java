package com.orange.tbk.adminweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.orange.tbk.adminweb.annotation.JwtIgnore;
import com.orange.tbk.adminweb.model.Audience;
import com.orange.tbk.adminweb.model.Response;
import com.orange.tbk.adminweb.model.ResponseCode;
import com.orange.tbk.adminweb.util.JwtTokenUtil;
import com.orange.tbk.api.bean.User;
import com.orange.tbk.api.redis.RedisKeyConstant;
import com.orange.tbk.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private Audience audience;

    @Reference(version = "${admin.version}", check = false)
    private UserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param mobile   手机号
     * @param password 密码
     * @param code     短信验证码
     */
    @PostMapping("/login")
    @JwtIgnore
    public Response login(String mobile, String password, String code) {
        if (StringUtils.isBlank(password) && StringUtils.isBlank(code)) {
            return Response.build(ResponseCode.LOGIN_ERROR);
        }
        // 如果传了验证码则校验验证码
        if (StringUtils.isNotBlank(code)) {
            Object object = redisTemplate.opsForValue().get(RedisKeyConstant.CODE + mobile);
            if (object == null) {
                return Response.build(ResponseCode.CODE_ERROR);
            }
        }
        User user = userService.login(mobile, password, code);
        if (user == null) {
            return Response.build(ResponseCode.LOGIN_ERROR);
        }
        String role = "normal";
        // 创建token
        String token = JwtTokenUtil.createJWT(user.getId(), mobile, role, audience);
        user.setToken(token);
        return Response.build(ResponseCode.QUERY_SUCCESS, user);
    }

    @GetMapping("/info")
    @JwtIgnore
    public Response<User> userInfo(String userId) {
        User user = userService.getUserInfo(userId);
        return Response.build(ResponseCode.QUERY_SUCCESS, user);
    }

    @PostMapping("/register")
    @JwtIgnore
    public Response register(String mobile, String password, String rePassword, String code) {
        User user;
        try {
            //redisTemplate.opsForValue().set(RedisKeyConstant.CODE + mobile, code, 15, TimeUnit.MINUTES);
            user = userService.register(mobile,password,rePassword,code);
            String role = "normal";
            // 创建token
            String token = JwtTokenUtil.createJWT(user.getId(), mobile, role, audience);
            user.setToken(token);
        } catch (Exception e) {
            log.error("注册失败"+e);
            return Response.build(ResponseCode.ERROR,"验证码错误");
        }
        return Response.build(ResponseCode.QUERY_SUCCESS,user);
    }

    /**
     * 发送验证码
     */
    @JwtIgnore
    @RequestMapping(value = "/sendMsg")
    public Response sendMsg(String mobile) {
        String response = userService.sendMsg(mobile);
        System.out.println(response);
        JSONObject jsonObject = JSONObject.parseObject(response);
        if ("0000".equals(jsonObject.get("code"))) {
            return Response.build(ResponseCode.QUERY_SUCCESS);
        } else {
            redisTemplate.delete(RedisKeyConstant.CODE + mobile);
            return Response.build(ResponseCode.ERROR);
        }
    }
    @RequestMapping("/hello")
    @JwtIgnore
    public Response hello(){
        System.out.println("3333333");
        if(1==1){
            throw new RuntimeException("1111");
        }
        return Response.build(ResponseCode.SUCCESS);
    }
}
