package com.orange.tbk.admin.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.tbk.admin.mapper.AccountMapper;
import com.orange.tbk.api.bean.Account;
import com.orange.tbk.api.service.AccountService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service(version = "${version}")
@Component
public class AccountImpl extends ServiceImpl<AccountMapper,Account> implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Override
    public Account getInfo(String adzoneId) {
        return accountMapper.selectById(adzoneId);
    }
}

