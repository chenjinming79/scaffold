package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_pet_details")
public class XcPetDetails {
    /**
     * 记录ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_user_id")
    private String createUserId;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 修改人
     */
    @Column(name = "update_user_id")
    private String updateUserId;

    /**
     * 是否删除(0false未删除 1true已删除)
     */
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 业务状态(1代买:2预购:3已被购买)
     */
    private Integer status;

    /**
     * 宠物名称
     */
    @Column(name = "pet_name")
    private String petName;

    /**
     * 宠物图片
     */
    @Column(name = "pet_img")
    private String petImg;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 即抢开枪时间
     */
    @Column(name = "start_to_snap_up")
    private String startToSnapUp;

    /**
     * 即抢结束时间
     */
    @Column(name = "end_to_snap_up")
    private String endToSnapUp;

    /**
     * 可抢购数量
     */
    @Column(name = "snap_up_quantity")
    private String snapUpQuantity;

    /**
     * 购买价格
     */
    @Column(name = "buying_price")
    private String buyingPrice;

    /**
     * 收益时长(天)
     */
    private String earnings;

    /**
     * 聘用周期(天)
     */
    @Column(name = "hiring_cycle")
    private String hiringCycle;

    /**
     * 养护所需 晶体(天)
     */
    @Column(name = "curing_crystal")
    private String curingCrystal;

    /**
     * 养护所需 碎片(天)
     */
    @Column(name = "curing_debris")
    private String curingDebris;

    /**
     * 持续
     */
    private String continue;

    /**
     * 单次
     */
    private String once;

    /**
     * 每天消耗能量石(弃用)
     */
    @Column(name = "curing_energy_stone")
    private String curingEnergyStone;

    /**
     * 合约收益(弃用)
     */
    @Column(name = "contract_revenue")
    private String contractRevenue;

    /**
     * 获取记录ID
     *
     * @return id - 记录ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置记录ID
     *
     * @param id 记录ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人
     *
     * @return create_user_id - 创建人
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建人
     *
     * @param createUserId 创建人
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取修改人
     *
     * @return update_user_id - 修改人
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置修改人
     *
     * @param updateUserId 修改人
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取是否删除(0false未删除 1true已删除)
     *
     * @return is_delete - 是否删除(0false未删除 1true已删除)
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除(0false未删除 1true已删除)
     *
     * @param isDelete 是否删除(0false未删除 1true已删除)
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取业务状态(1代买:2预购:3已被购买)
     *
     * @return status - 业务状态(1代买:2预购:3已被购买)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置业务状态(1代买:2预购:3已被购买)
     *
     * @param status 业务状态(1代买:2预购:3已被购买)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取宠物名称
     *
     * @return pet_name - 宠物名称
     */
    public String getPetName() {
        return petName;
    }

    /**
     * 设置宠物名称
     *
     * @param petName 宠物名称
     */
    public void setPetName(String petName) {
        this.petName = petName;
    }

    /**
     * 获取宠物图片
     *
     * @return pet_img - 宠物图片
     */
    public String getPetImg() {
        return petImg;
    }

    /**
     * 设置宠物图片
     *
     * @param petImg 宠物图片
     */
    public void setPetImg(String petImg) {
        this.petImg = petImg;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取即抢开枪时间
     *
     * @return start_to_snap_up - 即抢开枪时间
     */
    public String getStartToSnapUp() {
        return startToSnapUp;
    }

    /**
     * 设置即抢开枪时间
     *
     * @param startToSnapUp 即抢开枪时间
     */
    public void setStartToSnapUp(String startToSnapUp) {
        this.startToSnapUp = startToSnapUp;
    }

    /**
     * 获取即抢结束时间
     *
     * @return end_to_snap_up - 即抢结束时间
     */
    public String getEndToSnapUp() {
        return endToSnapUp;
    }

    /**
     * 设置即抢结束时间
     *
     * @param endToSnapUp 即抢结束时间
     */
    public void setEndToSnapUp(String endToSnapUp) {
        this.endToSnapUp = endToSnapUp;
    }

    /**
     * 获取可抢购数量
     *
     * @return snap_up_quantity - 可抢购数量
     */
    public String getSnapUpQuantity() {
        return snapUpQuantity;
    }

    /**
     * 设置可抢购数量
     *
     * @param snapUpQuantity 可抢购数量
     */
    public void setSnapUpQuantity(String snapUpQuantity) {
        this.snapUpQuantity = snapUpQuantity;
    }

    /**
     * 获取购买价格
     *
     * @return buying_price - 购买价格
     */
    public String getBuyingPrice() {
        return buyingPrice;
    }

    /**
     * 设置购买价格
     *
     * @param buyingPrice 购买价格
     */
    public void setBuyingPrice(String buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    /**
     * 获取收益时长(天)
     *
     * @return earnings - 收益时长(天)
     */
    public String getEarnings() {
        return earnings;
    }

    /**
     * 设置收益时长(天)
     *
     * @param earnings 收益时长(天)
     */
    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }

    /**
     * 获取聘用周期(天)
     *
     * @return hiring_cycle - 聘用周期(天)
     */
    public String getHiringCycle() {
        return hiringCycle;
    }

    /**
     * 设置聘用周期(天)
     *
     * @param hiringCycle 聘用周期(天)
     */
    public void setHiringCycle(String hiringCycle) {
        this.hiringCycle = hiringCycle;
    }

    /**
     * 获取养护所需 晶体(天)
     *
     * @return curing_crystal - 养护所需 晶体(天)
     */
    public String getCuringCrystal() {
        return curingCrystal;
    }

    /**
     * 设置养护所需 晶体(天)
     *
     * @param curingCrystal 养护所需 晶体(天)
     */
    public void setCuringCrystal(String curingCrystal) {
        this.curingCrystal = curingCrystal;
    }

    /**
     * 获取养护所需 碎片(天)
     *
     * @return curing_debris - 养护所需 碎片(天)
     */
    public String getCuringDebris() {
        return curingDebris;
    }

    /**
     * 设置养护所需 碎片(天)
     *
     * @param curingDebris 养护所需 碎片(天)
     */
    public void setCuringDebris(String curingDebris) {
        this.curingDebris = curingDebris;
    }

    /**
     * 获取持续
     *
     * @return continue - 持续
     */
    public String getContinue() {
        return continue;
    }

    /**
     * 设置持续
     *
     * @param continue 持续
     */
    public void setContinue(String continue) {
        this.continue = continue;
    }

    /**
     * 获取单次
     *
     * @return once - 单次
     */
    public String getOnce() {
        return once;
    }

    /**
     * 设置单次
     *
     * @param once 单次
     */
    public void setOnce(String once) {
        this.once = once;
    }

    /**
     * 获取每天消耗能量石(弃用)
     *
     * @return curing_energy_stone - 每天消耗能量石(弃用)
     */
    public String getCuringEnergyStone() {
        return curingEnergyStone;
    }

    /**
     * 设置每天消耗能量石(弃用)
     *
     * @param curingEnergyStone 每天消耗能量石(弃用)
     */
    public void setCuringEnergyStone(String curingEnergyStone) {
        this.curingEnergyStone = curingEnergyStone;
    }

    /**
     * 获取合约收益(弃用)
     *
     * @return contract_revenue - 合约收益(弃用)
     */
    public String getContractRevenue() {
        return contractRevenue;
    }

    /**
     * 设置合约收益(弃用)
     *
     * @param contractRevenue 合约收益(弃用)
     */
    public void setContractRevenue(String contractRevenue) {
        this.contractRevenue = contractRevenue;
    }
}