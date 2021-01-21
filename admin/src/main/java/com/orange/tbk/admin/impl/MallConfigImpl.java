package com.orange.tbk.admin.impl;

import com.orange.tbk.admin.dao.MallConfigDao;
import com.orange.tbk.api.bean.MallConfig;
import com.orange.tbk.api.service.MallConfigService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "${version}")
public class MallConfigImpl implements MallConfigService {
    @Autowired
    private MallConfigDao mallConfigDao;
    @Override
    public List<MallConfig> getMallConfigByState(int state) {
        return mallConfigDao.findAllByState(state);
    }
}
