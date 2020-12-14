package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.XcPetDetails;
import org.apache.ibatis.annotations.Param;

public interface XcPetDetailsMapper extends Mapper<XcPetDetails> {

    void updateStatusById(@Param("petId") String petId,@Param("status") Integer status);

    Integer getPetDetailsById(String petId);
}
