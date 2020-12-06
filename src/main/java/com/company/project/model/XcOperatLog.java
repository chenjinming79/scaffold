package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_operat_log")
public class XcOperatLog {
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
     * 业务状态(1充值;2提币;3星宠设置;4通用配置;5能量石配置;6赠送配置;7推广分享配置;8账户修改;)
     */
    private Integer status;

    /**
     * 操作用户名
     */
    @Column(name = "operat_name")
    private String operatName;
    /**
     * 操作功能
     */
    @Column(name = "operat_function")
    private String operatFunction;

    /**
     * 操作模块
     */
    @Column(name = "operat_module")
    private String operatModule;

    /**
     * 操作内容
     */
    @Column(name = "operat_content")
    private String operatContent;

    /**
     * 操作状态
     */
    @Column(name = "operat_state")
    private String operatState;

    /**
     * ip地址
     */
    @Column(name = "operat_ip")
    private String operatIp;

    /**
     * 手机号
     */
    @Transient
    private String phone;

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
     * 获取业务状态(1充值;2提币;3星宠设置;4通用配置;5能量石配置;6赠送配置;7推广分享配置;8账户修改;)
     *
     * @return status - 业务状态(1充值;2提币;3星宠设置;4通用配置;5能量石配置;6赠送配置;7推广分享配置;8账户修改;)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置业务状态(1充值;2提币;3星宠设置;4通用配置;5能量石配置;6赠送配置;7推广分享配置;8账户修改;)
     *
     * @param status 业务状态(1充值;2提币;3星宠设置;4通用配置;5能量石配置;6赠送配置;7推广分享配置;8账户修改;)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperatName() {
        return operatName;
    }

    public void setOperatName(String operatName) {
        this.operatName = operatName;
    }

    public String getOperatFunction() {
        return operatFunction;
    }

    public void setOperatFunction(String operatFunction) {
        this.operatFunction = operatFunction;
    }

    public String getOperatModule() {
        return operatModule;
    }

    public void setOperatModule(String operatModule) {
        this.operatModule = operatModule;
    }

    public String getOperatContent() {
        return operatContent;
    }

    public void setOperatContent(String operatContent) {
        this.operatContent = operatContent;
    }

    public String getOperatState() {
        return operatState;
    }

    public void setOperatState(String operatState) {
        this.operatState = operatState;
    }

    public String getOperatIp() {
        return operatIp;
    }

    public void setOperatIp(String operatIp) {
        this.operatIp = operatIp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}