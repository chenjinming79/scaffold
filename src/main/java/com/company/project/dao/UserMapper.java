package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends Mapper<User> {

    User findUserByUserName(@Param("userName") String userName, @Param("id") Long id);

}
