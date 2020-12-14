package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.XcPetDetailsMapper;
import com.company.project.model.XcConsumePetRecord;
import com.company.project.model.XcFeedPetRecord;
import com.company.project.param.PreorderPetParam;
import com.company.project.service.XcConsumePetRecordService;
import com.company.project.service.XcFeedPetRecordService;
import com.company.project.service.XcPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Transactional
public class XcPetServiceImpl implements XcPetService {

    @Autowired
    private XcConsumePetRecordService xcConsumePetRecordService;

    @Autowired
    private XcFeedPetRecordService xcFeedPetRecordService;

    @Autowired
    private XcPetDetailsMapper xcPetDetailsMapper;

    /**
     * 用户预购宠物
     * @return
     */
    @Override
    public Result addPreorderPet(PreorderPetParam param) {

        Integer rows = xcPetDetailsMapper.getPetDetailsById(param.getPetId());
        if (0 == rows){
            return ResultGenerator.genFailResult(ResultCode.PREORDER_PET_NULL_ERROR,"宠物不存在，请重新预购");
        }

        //1预购2取消预购
        if (1 == param.getStatus()){
            //修改宠物详情状态
            xcPetDetailsMapper.updateStatusById(param.getPetId(),1);

            //增加预购详情记录
            XcConsumePetRecord xcConsumePetRecord = new XcConsumePetRecord();
            xcConsumePetRecord.setCreateTime(new Date());
            xcConsumePetRecord.setCreateUserId(param.getUserId());
            xcConsumePetRecord.setStatus(1);
            xcConsumePetRecord.setPetId(param.getPetId());
            xcConsumePetRecordService.save(xcConsumePetRecord);
        }else if (2 == param.getStatus()){
            //修改宠物详情状态
            xcPetDetailsMapper.updateStatusById(param.getPetId(),2);

            //增加预购详情记录
            XcConsumePetRecord xcConsumePetRecord = new XcConsumePetRecord();
            xcConsumePetRecord.setCreateTime(new Date());
            xcConsumePetRecord.setCreateUserId(param.getUserId());
            xcConsumePetRecord.setStatus(2);
            xcConsumePetRecord.setPetId(param.getPetId());
            xcConsumePetRecordService.save(xcConsumePetRecord);
        }

        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @Override
    public Result addPurchasePet(PreorderPetParam param) {
        //修改宠物详情状态
        xcPetDetailsMapper.updateStatusById(param.getPetId(),3);

        //增加预购详情记录
        XcConsumePetRecord xcConsumePetRecord = new XcConsumePetRecord();
        xcConsumePetRecord.setCreateTime(new Date());
        xcConsumePetRecord.setCreateUserId(param.getUserId());
        xcConsumePetRecord.setStatus(3);
        xcConsumePetRecord.setPetId(param.getPetId());
        xcConsumePetRecordService.save(xcConsumePetRecord);
        Result result = ResultGenerator.genSuccessResult();
        return result;
    }

    @Override
    public Result addfeedPet(PreorderPetParam param) {

        Integer rows = xcPetDetailsMapper.getPetDetailsById(param.getPetId());
        if (0 == rows){
            return ResultGenerator.genFailResult(ResultCode.FEED_PET_NULL_ERROR,"宠物不存在，请刷新后重试");
        }

        //增加宠物养护详情记录
        XcFeedPetRecord xcFeedPetRecord = new XcFeedPetRecord();
        xcFeedPetRecord.setCreateTime(new Date());
        xcFeedPetRecord.setCreateUserId(param.getUserId());
        xcFeedPetRecord.setPetId(param.getPetId());
        xcFeedPetRecordService.save(xcFeedPetRecord);

        //计算收益记录，需根据原型图或者字段

        Result result = ResultGenerator.genSuccessResult();
        return result;
    }
}
