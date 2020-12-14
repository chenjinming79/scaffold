package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.XcConsumePetRecord;
import com.company.project.model.XcPetDetails;
import com.company.project.param.PreorderPetParam;
import com.company.project.service.XcConsumePetRecordService;
import com.company.project.service.XcPetDetailsService;
import com.company.project.service.XcPetService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName XcPetController
 * @Description //TODO
 * @Author cjm
 * @Date 2020/12/9 14:51
 * @Version 1.0
 **/
@RestController
@RequestMapping("/xc/pet")
@Api(tags = {"/xc/pet"}, description = "星宠系统模块")
public class XcPetController {

    @Autowired
    private XcPetDetailsService xcPetDetailsService;

    @Autowired
    private XcConsumePetRecordService xcConsumePetRecordService;

    @Autowired
    private XcPetService xcPetService;

    @ApiOperation(value = "新增宠物详情", notes = "新增宠物详情")
    @RequestMapping(value = "/addPetDetails", method = {RequestMethod.POST,RequestMethod.GET})
    public Result addPetDetails(@RequestBody List<XcPetDetails> xcPetDetailsList) {
        xcPetDetailsService.save(xcPetDetailsList);
        Result result = ResultGenerator.genSuccessResult(xcPetDetailsList);
        return result;
    }

    @ApiOperation(value = "用户预购(取消)宠物", notes = "用户预购(取消)宠物")
    @RequestMapping(value = "/addPreorderPet", method = {RequestMethod.POST,RequestMethod.GET})
    public Result addPreorderPet(@RequestBody PreorderPetParam param) {
        return xcPetService.addPreorderPet(param);
    }

    @ApiOperation(value = "分页查询宠物的预购排队列表", notes = "分页查询宠物的预购排队列表")
    @RequestMapping(value = "/findPreorderPetByModal", method = {RequestMethod.POST,RequestMethod.GET})
    public Result findPreorderPetByModal(@RequestParam(defaultValue="1",required=false) Integer page, @RequestParam(defaultValue="20",required=false) Integer size,
                                         @RequestBody(required =false) XcConsumePetRecord xcConsumePetRecord) {
        return xcConsumePetRecordService.list(page,size,xcConsumePetRecord);
    }

    @ApiOperation(value = "用户购买宠物", notes = "用户购买宠物")
    @RequestMapping(value = "/addPurchasePet", method = {RequestMethod.POST,RequestMethod.GET})
    public Result addPurchasePet(@RequestBody PreorderPetParam param) {
        return xcPetService.addPurchasePet(param);
    }

    @ApiOperation(value = "分页查询我的消费宠物记录", notes = "分页查询我的消费宠物记录")
    @RequestMapping(value = "/findPetConsumeRecordByModal", method = {RequestMethod.POST,RequestMethod.GET})
    public Result findPetConsumeRecordByModal(Integer page, Integer size,@RequestBody XcConsumePetRecord xcConsumePetRecord) {
        return xcConsumePetRecordService.list(page,size,xcConsumePetRecord);
    }

    @ApiOperation(value = "分页查询我的宠物列表", notes = "分页查询我的宠物列表")
    @RequestMapping(value = "/findPetByModal", method = {RequestMethod.POST,RequestMethod.GET})
    public Result findPetByModal(@RequestParam(defaultValue="1",required=false) Integer page, @RequestParam(defaultValue="20",required=false) Integer size,
                                 @RequestBody(required =false) XcConsumePetRecord xcConsumePetRecord) {
        return xcConsumePetRecordService.list(page,size,xcConsumePetRecord);
    }

    @ApiOperation(value = "养护宠物", notes = "养护宠物")
    @RequestMapping(value = "/addfeedPet", method = {RequestMethod.POST,RequestMethod.GET})
    public Result addfeedPet(@RequestBody PreorderPetParam param) {
        return xcPetService.addfeedPet(param);
    }
}
