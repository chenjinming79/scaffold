package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.param.PreorderPetParam;

public interface XcPetService {

    Result addPreorderPet(PreorderPetParam param);

    Result addPurchasePet(PreorderPetParam param);

    Result addfeedPet(PreorderPetParam param);
}
