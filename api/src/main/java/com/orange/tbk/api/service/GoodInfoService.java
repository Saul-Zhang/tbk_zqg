package com.orange.tbk.api.service;

import com.taobao.api.response.TbkDgMaterialOptionalResponse;

import java.util.List;

public interface GoodInfoService {
    List<com.taobao.api.response.TbkDgOptimusMaterialResponse.MapData> getGoodInfo(Long pageNo, Long materialId);

    List<TbkDgMaterialOptionalResponse.MapData> getInfoByKeyword(Long pageNo, String keyword, String sort);

}
