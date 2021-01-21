package com.orange.tbk.admin.dao;

import com.orange.tbk.api.bean.MallConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MallConfigDao extends JpaRepository<MallConfig,Integer> {

    List<MallConfig> findAllByState(int state);
}
