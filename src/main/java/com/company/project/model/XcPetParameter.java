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
     * 业务状态(1启用:2封禁)
     */
    private Integer status;

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
     * 周期
     */
    private String period;

    /**
     * 收益
     */
    private String earnings;

    /**
     * 养护能量石
     */
    @Column(name = "curing_energy_stone")
    private String curingEnergyStone;

    /**
     * 数量
     */
    private String quantity;

    /**
     * 能量池
     */
    @Column(name = "energy_scale")
    private String energyScale;

    /**
     * 种子数量兑换能量石
     */
    @Column(name = "seed_quantity_exchange_energy_stone")
    private String seedQuantityExchangeEnergyStone;

    /**
     * USDT兑换能量石
     */
    @Column(name = "usdt_exchange_energy_stone")
    private String usdtExchangeEnergyStone;

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
     * 获取业务状态(1启用:2封禁)
     *
     * @return status - 业务状态(1启用:2封禁)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置业务状态(1启用:2封禁)
     *
     * @param status 业务状态(1启用:2封禁)
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
     * 获取周期
     *
     * @return period - 周期
     */
    public String getPeriod() {
        return period;
    }

    /**
     * 设置周期
     *
     * @param period 周期
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * 获取收益
     *
     * @return earnings - 收益
     */
    public String getEarnings() {
        return earnings;
    }

    /**
     * 设置收益
     *
     * @param earnings 收益
     */
    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }

    /**
     * 获取养护能量石
     *
     * @return curing_energy_stone - 养护能量石
     */
    public String getCuringEnergyStone() {
        return curingEnergyStone;
    }

    /**
     * 设置养护能量石
     *
     * @param curingEnergyStone 养护能量石
     */
    public void setCuringEnergyStone(String curingEnergyStone) {
        this.curingEnergyStone = curingEnergyStone;
    }

    /**
     * 获取数量
     *
     * @return quantity - 数量
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * 设置数量
     *
     * @param quantity 数量
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取能量池
     *
     * @return energy_scale - 能量池
     */
    public String getEnergyScale() {
        return energyScale;
    }

    /**
     * 设置能量池
     *
     * @param energyScale 能量池
     */
    public void setEnergyScale(String energyScale) {
        this.energyScale = energyScale;
    }

    /**
     * 获取种子数量兑换能量石
     *
     * @return seed_quantity_exchange_energy_stone - 种子数量兑换能量石
     */
    public String getSeedQuantityExchangeEnergyStone() {
        return seedQuantityExchangeEnergyStone;
    }

    /**
     * 设置种子数量兑换能量石
     *
     * @param seedQuantityExchangeEnergyStone 种子数量兑换能量石
     */
    public void setSeedQuantityExchangeEnergyStone(String seedQuantityExchangeEnergyStone) {
        this.seedQuantityExchangeEnergyStone = seedQuantityExchangeEnergyStone;
    }

    /**
     * 获取USDT兑换能量石
     *
     * @return usdt_exchange_energy_stone - USDT兑换能量石
     */
    public String getUsdtExchangeEnergyStone() {
        return usdtExchangeEnergyStone;
    }

    /**
     * 设置USDT兑换能量石
     *
     * @param usdtExchangeEnergyStone USDT兑换能量石
     */
    public void setUsdtExchangeEnergyStone(String usdtExchangeEnergyStone) {
        this.usdtExchangeEnergyStone = usdtExchangeEnergyStone;
    }
}