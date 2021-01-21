package com.orange.tbk.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.tbk.api.bean.Account;

public interface AccountService extends IService<Account> {
    public Account getInfo(String adzoneId);
}
