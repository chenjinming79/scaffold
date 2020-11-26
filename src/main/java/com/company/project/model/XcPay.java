package com.company.project.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_pay")
public class XcPay {
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
     * 充值方式
     */
    @Column(name = "top_up_way")
    private String topUpWay;

    /**
     * 充值金额
     */
    @Column(name = "top_up_money")
    private BigDecimal topUpMoney;

    /**
     * 到账金额
     */
    @Column(name = "account_money")
    private BigDecimal accountMoney;

    /**
     * 币种名称
     */
    @Column(name = "currency_name")
    private String currencyName;

    /**
     * 拒绝原因
     */
    private String message;

    /**
     * 手机号
     */
    @Transient
    private String phone;

    /**
     * 开始时间
     */
    @Transient
    private String startTime;

    /**
     * 结束时间
     */
    @Transient
    private String endTime;

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
     * 获取充值方式
     *
     * @return top_up_way - 充值方式
     */
    public String getTopUpWay() {
        return topUpWay;
    }

    /**
     * 设置充值方式
     *
     * @param topUpWay 充值方式
     */
    public void setTopUpWay(String topUpWay) {
        this.topUpWay = topUpWay;
    }

    /**
     * 获取充值金额
     *
     * @return top_up_money - 充值金额
     */
    public BigDecimal getTopUpMoney() {
        return topUpMoney;
    }

    /**
     * 设置充值金额
     *
     * @param topUpMoney 充值金额
     */
    public void setTopUpMoney(BigDecimal topUpMoney) {
        this.topUpMoney = topUpMoney;
    }

    /**
     * 获取到账金额
     *
     * @return account_money - 到账金额
     */
    public BigDecimal getAccountMoney() {
        return accountMoney;
    }

    /**
     * 设置到账金额
     *
     * @param accountMoney 到账金额
     */
    public void setAccountMoney(BigDecimal accountMoney) {
        this.accountMoney = accountMoney;
    }

    /**
     * 获取币种名称
     *
     * @return currency_name - 币种名称
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * 设置币种名称
     *
     * @param currencyName 币种名称
     */
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    /**
     * 获取拒绝原因
     *
     * @return message - 拒绝原因
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置拒绝原因
     *
     * @param message 拒绝原因
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
