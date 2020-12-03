package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.XcUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface XcUserMapper extends Mapper<XcUser> {

    XcUser findUserByPhone(String phone);

    String getUserPhoneById(String createUserId);

    List<XcUser> list(XcUser xcUser);
}
