package com.orange.tbk.api.service;

import com.orange.tbk.api.bean.User;

public interface UserService {

    User login(String username, String password,String code);

    void logout(String token);

    String sendMsg(String mobile);

    User getUserInfo(String userId);

    User register(String mobile, String password, String rePassword, String code) throws Exception;

    User findByAdzoneId(String adzoneId);
}
