package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sys_config")
public class XcSysConfig {
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
     * ASC兑换USDT
     */
    @Column(name = "asc_for_usdt")
    private String ascForUsdt;

    /**
     * 提币手续费
     */
    @Column(name = "withdraw_service_charge")
    private String withdrawServiceCharge;

    /**
     * 兑换手续费
     */
    @Column(name = "exchange_service_charge")
    private String exchangeServiceCharge;

    /**
     * 最小赠送数量 ASC
     */
    @Column(name = "min_number_of_gifts_asc")
    private String minNumberOfGiftsAsc;

    /**
     * 最小赠送数量 能量石
     */
    @Column(name = "min_number_of_gifts_energy")
    private String minNumberOfGiftsEnergy;

    /**
     * 赠送手续费 ASC
     */
    @Column(name = "gift_fee")
    private String giftFee;

    /**
     * 能量石价格 USDT
     */
    @Column(name = "energy_stone_price")
    private String energyStonePrice;

    /**
     * 合成数量 碎片
     */
    @Column(name = "synthetic_quantity")
    private String syntheticQuantity;

    /**
     * 合成时间 天
     */
    @Column(name = "synthetic_time")
    private String syntheticTime;

    /**
     * 最大合成队列 颗
     */
    @Column(name = "max_composition_queue")
    private String maxCompositionQueue;

    /**
     * 一级推广分佣ASC
     */
    @Column(name = "one_spread_asc")
    private String oneSpreadAsc;

    /**
     * 一级推广分佣碎片
     */
    @Column(name = "one_spread_debris")
    private String oneSpreadDebris;

    /**
     * 二级推广分佣ASC
     */
    @Column(name = "two_spread_asc")
    private String twoSpreadAsc;

    /**
     * 二级推广分佣碎片
     */
    @Column(name = "two_spread_debris")
    private String twoSpreadDebris;

    /**
     * 三级推广分佣ASC
     */
    @Column(name = "three_spread_asc")
    private String threeSpreadAsc;

    /**
     * 三级推广分佣碎片
     */
    @Column(name = "three_spread_debris")
    private String threeSpreadDebris;

    /**
     * 四级推广分佣ASC
     */
    @Column(name = "four_spread_asc")
    private String fourSpreadAsc;

    /**
     * 四级推广分佣碎片
     */
    @Column(name = "four_spread_debris")
    private String fourSpreadDebris;

    /**
     * 五级推广分佣ASC
     */
    @Column(name = "five_spread_asc")
    private String fiveSpreadAsc;

    /**
     * 五级推广分佣碎片
     */
    @Column(name = "five_spread_debris")
    private String fiveSpreadDebris;

    public XcSysConfig() {
    }

    public XcSysConfig(Long id) {
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
     * 获取ASC兑换USDT
     *
     * @return asc_for_usdt - ASC兑换USDT
     */
    public String getAscForUsdt() {
        return ascForUsdt;
    }

    /**
     * 设置ASC兑换USDT
     *
     * @param ascForUsdt ASC兑换USDT
     */
    public void setAscForUsdt(String ascForUsdt) {
        this.ascForUsdt = ascForUsdt;
    }

    /**
     * 获取提币手续费
     *
     * @return withdraw_service_charge - 提币手续费
     */
    public String getWithdrawServiceCharge() {
        return withdrawServiceCharge;
    }

    /**
     * 设置提币手续费
     *
     * @param withdrawServiceCharge 提币手续费
     */
    public void setWithdrawServiceCharge(String withdrawServiceCharge) {
        this.withdrawServiceCharge = withdrawServiceCharge;
    }

    /**
     * 获取兑换手续费
     *
     * @return exchange_service_charge - 兑换手续费
     */
    public String getExchangeServiceCharge() {
        return exchangeServiceCharge;
    }

    /**
     * 设置兑换手续费
     *
     * @param exchangeServiceCharge 兑换手续费
     */
    public void setExchangeServiceCharge(String exchangeServiceCharge) {
        this.exchangeServiceCharge = exchangeServiceCharge;
    }

    /**
     * 获取最小赠送数量 ASC
     *
     * @return min_number_of_gifts_asc - 最小赠送数量 ASC
     */
    public String getMinNumberOfGiftsAsc() {
        return minNumberOfGiftsAsc;
    }

    /**
     * 设置最小赠送数量 ASC
     *
     * @param minNumberOfGiftsAsc 最小赠送数量 ASC
     */
    public void setMinNumberOfGiftsAsc(String minNumberOfGiftsAsc) {
        this.minNumberOfGiftsAsc = minNumberOfGiftsAsc;
    }

    /**
     * 获取最小赠送数量 能量石
     *
     * @return min_number_of_gifts_energy - 最小赠送数量 能量石
     */
    public String getMinNumberOfGiftsEnergy() {
        return minNumberOfGiftsEnergy;
    }

    /**
     * 设置最小赠送数量 能量石
     *
     * @param minNumberOfGiftsEnergy 最小赠送数量 能量石
     */
    public void setMinNumberOfGiftsEnergy(String minNumberOfGiftsEnergy) {
        this.minNumberOfGiftsEnergy = minNumberOfGiftsEnergy;
    }

    /**
     * 获取赠送手续费 ASC
     *
     * @return gift_fee - 赠送手续费 ASC
     */
    public String getGiftFee() {
        return giftFee;
    }

    /**
     * 设置赠送手续费 ASC
     *
     * @param giftFee 赠送手续费 ASC
     */
    public void setGiftFee(String giftFee) {
        this.giftFee = giftFee;
    }

    /**
     * 获取能量石价格 USDT
     *
     * @return energy_stone_price - 能量石价格 USDT
     */
    public String getEnergyStonePrice() {
        return energyStonePrice;
    }

    /**
     * 设置能量石价格 USDT
     *
     * @param energyStonePrice 能量石价格 USDT
     */
    public void setEnergyStonePrice(String energyStonePrice) {
        this.energyStonePrice = energyStonePrice;
    }

    /**
     * 获取合成数量 碎片
     *
     * @return synthetic_quantity - 合成数量 碎片
     */
    public String getSyntheticQuantity() {
        return syntheticQuantity;
    }

    /**
     * 设置合成数量 碎片
     *
     * @param syntheticQuantity 合成数量 碎片
     */
    public void setSyntheticQuantity(String syntheticQuantity) {
        this.syntheticQuantity = syntheticQuantity;
    }

    /**
     * 获取合成时间 天
     *
     * @return synthetic_time - 合成时间 天
     */
    public String getSyntheticTime() {
        return syntheticTime;
    }

    /**
     * 设置合成时间 天
     *
     * @param syntheticTime 合成时间 天
     */
    public void setSyntheticTime(String syntheticTime) {
        this.syntheticTime = syntheticTime;
    }

    /**
     * 获取最大合成队列 颗
     *
     * @return max_composition_queue - 最大合成队列 颗
     */
    public String getMaxCompositionQueue() {
        return maxCompositionQueue;
    }

    /**
     * 设置最大合成队列 颗
     *
     * @param maxCompositionQueue 最大合成队列 颗
     */
    public void setMaxCompositionQueue(String maxCompositionQueue) {
        this.maxCompositionQueue = maxCompositionQueue;
    }

    /**
     * 获取一级推广分佣ASC
     *
     * @return one_spread_asc - 一级推广分佣ASC
     */
    public String getOneSpreadAsc() {
        return oneSpreadAsc;
    }

    /**
     * 设置一级推广分佣ASC
     *
     * @param oneSpreadAsc 一级推广分佣ASC
     */
    public void setOneSpreadAsc(String oneSpreadAsc) {
        this.oneSpreadAsc = oneSpreadAsc;
    }

    /**
     * 获取一级推广分佣碎片
     *
     * @return one_spread_debris - 一级推广分佣碎片
     */
    public String getOneSpreadDebris() {
        return oneSpreadDebris;
    }

    /**
     * 设置一级推广分佣碎片
     *
     * @param oneSpreadDebris 一级推广分佣碎片
     */
    public void setOneSpreadDebris(String oneSpreadDebris) {
        this.oneSpreadDebris = oneSpreadDebris;
    }

    /**
     * 获取二级推广分佣ASC
     *
     * @return two_spread_asc - 二级推广分佣ASC
     */
    public String getTwoSpreadAsc() {
        return twoSpreadAsc;
    }

    /**
     * 设置二级推广分佣ASC
     *
     * @param twoSpreadAsc 二级推广分佣ASC
     */
    public void setTwoSpreadAsc(String twoSpreadAsc) {
        this.twoSpreadAsc = twoSpreadAsc;
    }

    /**
     * 获取二级推广分佣碎片
     *
     * @return two_spread_debris - 二级推广分佣碎片
     */
    public String getTwoSpreadDebris() {
        return twoSpreadDebris;
    }

    /**
     * 设置二级推广分佣碎片
     *
     * @param twoSpreadDebris 二级推广分佣碎片
     */
    public void setTwoSpreadDebris(String twoSpreadDebris) {
        this.twoSpreadDebris = twoSpreadDebris;
    }

    /**
     * 获取三级推广分佣ASC
     *
     * @return three_spread_asc - 三级推广分佣ASC
     */
    public String getThreeSpreadAsc() {
        return threeSpreadAsc;
    }

    /**
     * 设置三级推广分佣ASC
     *
     * @param threeSpreadAsc 三级推广分佣ASC
     */
    public void setThreeSpreadAsc(String threeSpreadAsc) {
        this.threeSpreadAsc = threeSpreadAsc;
    }

    /**
     * 获取三级推广分佣碎片
     *
     * @return three_spread_debris - 三级推广分佣碎片
     */
    public String getThreeSpreadDebris() {
        return threeSpreadDebris;
    }

    /**
     * 设置三级推广分佣碎片
     *
     * @param threeSpreadDebris 三级推广分佣碎片
     */
    public void setThreeSpreadDebris(String threeSpreadDebris) {
        this.threeSpreadDebris = threeSpreadDebris;
    }

    /**
     * 获取四级推广分佣ASC
     *
     * @return four_spread_asc - 四级推广分佣ASC
     */
    public String getFourSpreadAsc() {
        return fourSpreadAsc;
    }

    /**
     * 设置四级推广分佣ASC
     *
     * @param fourSpreadAsc 四级推广分佣ASC
     */
    public void setFourSpreadAsc(String fourSpreadAsc) {
        this.fourSpreadAsc = fourSpreadAsc;
    }

    /**
     * 获取四级推广分佣碎片
     *
     * @return four_spread_debris - 四级推广分佣碎片
     */
    public String getFourSpreadDebris() {
        return fourSpreadDebris;
    }

    /**
     * 设置四级推广分佣碎片
     *
     * @param fourSpreadDebris 四级推广分佣碎片
     */
    public void setFourSpreadDebris(String fourSpreadDebris) {
        this.fourSpreadDebris = fourSpreadDebris;
    }

    /**
     * 获取五级推广分佣ASC
     *
     * @return five_spread_asc - 五级推广分佣ASC
     */
    public String getFiveSpreadAsc() {
        return fiveSpreadAsc;
    }

    /**
     * 设置五级推广分佣ASC
     *
     * @param fiveSpreadAsc 五级推广分佣ASC
     */
    public void setFiveSpreadAsc(String fiveSpreadAsc) {
        this.fiveSpreadAsc = fiveSpreadAsc;
    }

    /**
     * 获取五级推广分佣碎片
     *
     * @return five_spread_debris - 五级推广分佣碎片
     */
    public String getFiveSpreadDebris() {
        return fiveSpreadDebris;
    }

    /**
     * 设置五级推广分佣碎片
     *
     * @param fiveSpreadDebris 五级推广分佣碎片
     */
    public void setFiveSpreadDebris(String fiveSpreadDebris) {
        this.fiveSpreadDebris = fiveSpreadDebris;
    }
}
