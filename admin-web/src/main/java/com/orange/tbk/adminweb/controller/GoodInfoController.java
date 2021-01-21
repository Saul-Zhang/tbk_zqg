package com.orange.tbk.adminweb.controller;

import com.orange.tbk.adminweb.model.Response;
import com.orange.tbk.adminweb.model.ResponseCode;
import com.orange.tbk.api.annotation.JwtIgnore;
import com.orange.tbk.api.service.GoodInfoService;
import com.orange.tbk.api.service.UserService;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import com.taobao.api.response.TbkDgOptimusMaterialResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.ssi.ResponseIncludeWrapper;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/good")
public class GoodInfoController {

    @Reference(version = "${admin.version}", check = false)
    private GoodInfoService goodInfoService;

    @JwtIgnore
    @GetMapping("info")
    public Response getInfo(Long pageNo,Long materialId){
        List<TbkDgOptimusMaterialResponse.MapData> goodInfo = goodInfoService.getGoodInfo(pageNo,materialId);
        return Response.build(ResponseCode.QUERY_SUCCESS,goodInfo);
    }

    /**
     * 根据关键词搜索商品
     * @param pageNo
     * @param keyword
     * @return
     */
    @JwtIgnore
    @GetMapping("search")
    public Response getInfoByKeyword(Long pageNo,String keyword,String sort){
        List<TbkDgMaterialOptionalResponse.MapData> goodInfo = goodInfoService.getInfoByKeyword(pageNo,keyword,sort);
        return Response.build(ResponseCode.QUERY_SUCCESS,goodInfo);
    }
}
