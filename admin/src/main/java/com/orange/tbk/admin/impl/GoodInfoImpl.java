package com.orange.tbk.admin.impl;

import com.orange.tbk.api.service.GoodInfoService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkDgOptimusMaterialRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import com.taobao.api.response.TbkDgOptimusMaterialResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service(version = "${version}")
public class GoodInfoImpl implements GoodInfoService {

    @Override
    public List<TbkDgOptimusMaterialResponse.MapData> getGoodInfo(Long pageNo, Long materialId) {
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "31536683", "8c773b5841be36bb6654ef93fe1af876");
        TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();
        req.setPageSize(10L);
        req.setPageNo(pageNo);
        req.setAdzoneId(110928750320L);
        //优质特惠宝贝的物料id:4094Lx`
        //有好货精品id:4092L
        //淘宝客渠道每日实时热销爆款:28026L
        req.setMaterialId(materialId);
//        req.setItemId(602557288901L);
        List<TbkDgOptimusMaterialResponse.MapData> resultList = new ArrayList<>();
        try {
            TbkDgOptimusMaterialResponse response = client.execute(req);
             resultList = response.getResultList();
        } catch (ApiException e) {
//            log.error("获取商品详情失败",e);
        }
        return resultList;
    }

    @Override
    public List<TbkDgMaterialOptionalResponse.MapData> getInfoByKeyword(Long pageNo,String keyword,String sort) {
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "31536683", "8c773b5841be36bb6654ef93fe1af876");
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setQ(keyword);
        req.setAdzoneId(110928750320L);
        if(StringUtils.isNoneBlank(sort)){
            req.setSort(sort);
        }
        List<TbkDgMaterialOptionalResponse.MapData> resultList = new ArrayList<>();
        try {
            TbkDgMaterialOptionalResponse response = client.execute(req);
            resultList = response.getResultList();
        } catch (ApiException e) {
//            log.error("搜索商品详情失败",e);
        }
        return resultList;
    }
}
