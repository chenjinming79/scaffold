package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.XcPetDetails;

public interface XcPetDetailsMapper extends Mapper<XcPetDetails> {

    void updateStatusById(String petId);

    Integer getPetDetailsById(String petId);
}