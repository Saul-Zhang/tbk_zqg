package com.orange.tbk.adminweb.controller;

import com.orange.tbk.adminweb.annotation.JwtIgnore;
import com.orange.tbk.adminweb.model.Response;
import com.orange.tbk.adminweb.model.ResponseCode;
import com.orange.tbk.api.bean.MallConfig;
import com.orange.tbk.api.service.MallConfigService;
import com.orange.tbk.api.vo.open.CarouselVo;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "mallConfig")
public class MallConfigController {
    @Reference(version = "${admin.version}", check = false)
    private MallConfigService mallConfigService;

    @JwtIgnore
    @RequestMapping(value = "getListByState")
    public Response getListBySort(int state) {

        List<MallConfig> mallConfigList = mallConfigService.getMallConfigByState(state);

        return Response.build(ResponseCode.QUERY_SUCCESS, mallConfigList);
    }

}
