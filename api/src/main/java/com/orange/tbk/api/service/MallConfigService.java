package com.orange.tbk.api.service;

import com.orange.tbk.api.bean.MallConfig;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MallConfigService {
    List<MallConfig> getMallConfigByState(int state);
}
