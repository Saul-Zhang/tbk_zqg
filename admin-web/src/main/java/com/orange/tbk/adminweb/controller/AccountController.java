package com.orange.tbk.adminweb.controller;

import com.orange.tbk.adminweb.model.Response;
import com.orange.tbk.adminweb.model.ResponseCode;
import com.orange.tbk.adminweb.util.JwtTokenUtil;
import com.orange.tbk.api.bean.Account;
import com.orange.tbk.api.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController {

    @Reference(version = "${admin.version}", check = false)
    private AccountService accountService;

    @GetMapping("info")
    public Response getInfo(String adzoneId){
        if(StringUtils.isBlank(adzoneId)){
            return Response.build(ResponseCode.UNKNOWN_ERROR);
        }
        Account account = accountService.getInfo(adzoneId);
        return Response.build(ResponseCode.QUERY_SUCCESS,account);
    }
}
