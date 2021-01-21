package com.orange.tbk.admin.dao;

import com.orange.tbk.api.bean.AdzoneIdAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdzoneIdAllocationDao extends JpaRepository<AdzoneIdAllocation,Integer> {

    AdzoneIdAllocation findFirstByStatus(int status);
}
