package com.company.project.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "用户信息VO")
public class PreorderPetParam implements Serializable {

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "宠物id")
    private String petId;

    @ApiModelProperty(value = "1预购2取消预购")
    private Integer status;
}
