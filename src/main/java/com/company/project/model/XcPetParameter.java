package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_pet_parameter")
public class XcPetParameter {
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
     * 业务状态(1显示:2不显示)
     */
    private Integer status;

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
     * 宠物名称
     */
    @Column(name = "pet_name")
    private String petName;

    /**
     * 购买价格
     */
    @Column(name = "buying_price")
    private String buyingPrice;

    /**
     * 每天消耗能量石
     */
    @Column(name = "curing_energy_stone")
    private String curingEnergyStone;

    /**
     * 收益时长(天)
     */
    private String earnings;

    /**
     * 合约收益
     */
    @Column(name = "contract_revenue")
    private String contractRevenue;

    /**
     * 可抢购数量
     */
    @Column(name = "snap_up_quantity")
    private String snapUpQuantity;

    /**
     * 在养殖数量
     */
    @Column(name = "quantity_in_cultivation")
    private String quantityInCultivation;

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

    public XcPetParameter() {
    }

    public XcPetParameter(Long id) {
        this.id = id;
    }


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
     * 获取业务状态(1显示:2不显示)
     *
     * @return status - 业务状态(1显示:2不显示)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置业务状态(1显示:2不显示)
     *
     * @param status 业务状态(1显示:2不显示)
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 获取每天消耗能量石
     *
     * @return curing_energy_stone - 每天消耗能量石
     */
    public String getCuringEnergyStone() {
        return curingEnergyStone;
    }

    /**
     * 设置每天消耗能量石
     *
     * @param curingEnergyStone 每天消耗能量石
     */
    public void setCuringEnergyStone(String curingEnergyStone) {
        this.curingEnergyStone = curingEnergyStone;
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
     * 获取合约收益
     *
     * @return contract_revenue - 合约收益
     */
    public String getContractRevenue() {
        return contractRevenue;
    }

    /**
     * 设置合约收益
     *
     * @param contractRevenue 合约收益
     */
    public void setContractRevenue(String contractRevenue) {
        this.contractRevenue = contractRevenue;
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
     * 获取在养殖数量
     *
     * @return quantity_in_cultivation - 在养殖数量
     */
    public String getQuantityInCultivation() {
        return quantityInCultivation;
    }

    /**
     * 设置在养殖数量
     *
     * @param quantityInCultivation 在养殖数量
     */
    public void setQuantityInCultivation(String quantityInCultivation) {
        this.quantityInCultivation = quantityInCultivation;
    }

    public String getStartToSnapUp() {
        return startToSnapUp;
    }

    public void setStartToSnapUp(String startToSnapUp) {
        this.startToSnapUp = startToSnapUp;
    }

    public String getEndToSnapUp() {
        return endToSnapUp;
    }

    public void setEndToSnapUp(String endToSnapUp) {
        this.endToSnapUp = endToSnapUp;
    }
}
