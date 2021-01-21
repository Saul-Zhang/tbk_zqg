package com.orange.tbk.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orange.tbk.api.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    @Select("select * from t_user where mobile = #{mobile} and password = #{password}")
    User findByMobileAndPassword(@Param("mobile") String mobile, @Param("password")String password);

    @Select("select * from t_user where mobile = #{mobile}")
    User findByMobile(@Param("mobile")String mobile);

    @Select("select * from t_user where adzone_id = #{adzoneId}")
    User findByAdzoneId(@Param("adzoneId")String adzoneId);
}