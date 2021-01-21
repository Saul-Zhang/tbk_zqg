package com.orange.tbk.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.tbk.api.bean.AdminUser;

public interface AdminUserService extends IService<AdminUser> {

    String verifyUser(String username, String password);

    void logout(String token);

    String sendMsg(String mobile);

}
