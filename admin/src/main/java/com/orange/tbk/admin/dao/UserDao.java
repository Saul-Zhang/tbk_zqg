package com.orange.tbk.admin.dao;

import com.orange.tbk.api.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {

    User findTopByUsernameAndPassword(String userName, String password);

    User findByMobile(String mobile);

    User findByAdzoneId(String adzoneId);
}
