package com.dsc.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value="com.dsc.supergo.pojo.Order")
@Table(name = "tb_order")
public class Order implements Serializable {
    /**
     * 订单id
     */
    @Id
    @Column(name = "order_id")
    @ApiModelProperty(value="orderId订单id")
    private String orderId;

    /**
     * 实付金额
     */
    @ApiModelProperty(value="payment实付金额")
    private BigDecimal payment;

    /**
     * 支付类型 1在线支付 2货到付款
     */
    @Column(name = "payment_type")
    @ApiModelProperty(value="paymentType支付类型 1在线支付 2货到付款")
    private Integer paymentType;

    /**
     * 邮费
     */
    @Column(name = "post_fee")
    @ApiModelProperty(value="postFee邮费")
    private BigDecimal postFee;

    /**
     * 状态 0未付款 1已付款 2未发货 3已发货 4交易成功 5交易关闭 6交易失败
     */
    @ApiModelProperty(value="status状态 0未付款 1已付款 2未发货 3已发货 4交易成功 5交易关闭 6交易失败")
    private Integer status;

    /**
     * 订单创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value="createTime订单创建时间")
    private Date createTime;

    /**
     * 订单更新时间
     */
    @Column(name = "update_time")
    @ApiModelProperty(value="updateTime订单更新时间")
    private Date updateTime;

    /**
     * 付款时间
     */
    @Column(name = "payment_time")
    @ApiModelProperty(value="paymentTime付款时间")
    private Date paymentTime;

    /**
     * 发货时间
     */
    @Column(name = "consign_time")
    @ApiModelProperty(value="consignTime发货时间")
    private Date consignTime;

    /**
     * 交易完成时间
     */
    @Column(name = "end_time")
    @ApiModelProperty(value="endTime交易完成时间")
    private Date endTime;

    /**
     * 交易关闭时间
     */
    @Column(name = "close_time")
    @ApiModelProperty(value="closeTime交易关闭时间")
    private Date closeTime;

    /**
     * 物流名称
     */
    @Column(name = "shipping_name")
    @ApiModelProperty(value="shippingName物流名称")
    private String shippingName;

    /**
     * 物流单号
     */
    @Column(name = "shipping_code")
    @ApiModelProperty(value="shippingCode物流单号")
    private String shippingCode;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    @ApiModelProperty(value="userId用户id")
    private Long userId;

    /**
     * 买家留言
     */
    @Column(name = "buyer_message")
    @ApiModelProperty(value="buyerMessage买家留言")
    private String buyerMessage;

    /**
     * 买家昵称
     */
    @Column(name = "buyer_nick")
    @ApiModelProperty(value="buyerNick买家昵称")
    private String buyerNick;

    /**
     * 买家是否已经评价
     */
    @Column(name = "buyer_comment")
    @ApiModelProperty(value="buyerComment买家是否已经评价")
    private Boolean buyerComment;

    private static final long serialVersionUID = 1L;

    public Order(String orderId, BigDecimal payment, Integer paymentType, BigDecimal postFee, Integer status, Date createTime, Date updateTime, Date paymentTime, Date consignTime, Date endTime, Date closeTime, String shippingName, String shippingCode, Long userId, String buyerMessage, String buyerNick, Boolean buyerComment) {
        this.orderId = orderId;
        this.payment = payment;
        this.paymentType = paymentType;
        this.postFee = postFee;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.paymentTime = paymentTime;
        this.consignTime = consignTime;
        this.endTime = endTime;
        this.closeTime = closeTime;
        this.shippingName = shippingName;
        this.shippingCode = shippingCode;
        this.userId = userId;
        this.buyerMessage = buyerMessage;
        this.buyerNick = buyerNick;
        this.buyerComment = buyerComment;
    }

    public Order() {
        super();
    }

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取实付金额
     *
     * @return payment - 实付金额
     */
    public BigDecimal getPayment() {
        return payment;
    }

    /**
     * 设置实付金额
     *
     * @param payment 实付金额
     */
    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    /**
     * 获取支付类型 1在线支付 2货到付款
     *
     * @return payment_type - 支付类型 1在线支付 2货到付款
     */
    public Integer getPaymentType() {
        return paymentType;
    }

    /**
     * 设置支付类型 1在线支付 2货到付款
     *
     * @param paymentType 支付类型 1在线支付 2货到付款
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 获取邮费
     *
     * @return post_fee - 邮费
     */
    public BigDecimal getPostFee() {
        return postFee;
    }

    /**
     * 设置邮费
     *
     * @param postFee 邮费
     */
    public void setPostFee(BigDecimal postFee) {
        this.postFee = postFee;
    }

    /**
     * 获取状态 0未付款 1已付款 2未发货 3已发货 4交易成功 5交易关闭 6交易失败
     *
     * @return status - 状态 0未付款 1已付款 2未发货 3已发货 4交易成功 5交易关闭 6交易失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0未付款 1已付款 2未发货 3已发货 4交易成功 5交易关闭 6交易失败
     *
     * @param status 状态 0未付款 1已付款 2未发货 3已发货 4交易成功 5交易关闭 6交易失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取订单创建时间
     *
     * @return create_time - 订单创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置订单创建时间
     *
     * @param createTime 订单创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取订单更新时间
     *
     * @return update_time - 订单更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置订单更新时间
     *
     * @param updateTime 订单更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取付款时间
     *
     * @return payment_time - 付款时间
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * 设置付款时间
     *
     * @param paymentTime 付款时间
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 获取发货时间
     *
     * @return consign_time - 发货时间
     */
    public Date getConsignTime() {
        return consignTime;
    }

    /**
     * 设置发货时间
     *
     * @param consignTime 发货时间
     */
    public void setConsignTime(Date consignTime) {
        this.consignTime = consignTime;
    }

    /**
     * 获取交易完成时间
     *
     * @return end_time - 交易完成时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置交易完成时间
     *
     * @param endTime 交易完成时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取交易关闭时间
     *
     * @return close_time - 交易关闭时间
     */
    public Date getCloseTime() {
        return closeTime;
    }

    /**
     * 设置交易关闭时间
     *
     * @param closeTime 交易关闭时间
     */
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * 获取物流名称
     *
     * @return shipping_name - 物流名称
     */
    public String getShippingName() {
        return shippingName;
    }

    /**
     * 设置物流名称
     *
     * @param shippingName 物流名称
     */
    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    /**
     * 获取物流单号
     *
     * @return shipping_code - 物流单号
     */
    public String getShippingCode() {
        return shippingCode;
    }

    /**
     * 设置物流单号
     *
     * @param shippingCode 物流单号
     */
    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取买家留言
     *
     * @return buyer_message - 买家留言
     */
    public String getBuyerMessage() {
        return buyerMessage;
    }

    /**
     * 设置买家留言
     *
     * @param buyerMessage 买家留言
     */
    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    /**
     * 获取买家昵称
     *
     * @return buyer_nick - 买家昵称
     */
    public String getBuyerNick() {
        return buyerNick;
    }

    /**
     * 设置买家昵称
     *
     * @param buyerNick 买家昵称
     */
    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    /**
     * 获取买家是否已经评价
     *
     * @return buyer_comment - 买家是否已经评价
     */
    public Boolean getBuyerComment() {
        return buyerComment;
    }

    /**
     * 设置买家是否已经评价
     *
     * @param buyerComment 买家是否已经评价
     */
    public void setBuyerComment(Boolean buyerComment) {
        this.buyerComment = buyerComment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", payment=").append(payment);
        sb.append(", paymentType=").append(paymentType);
        sb.append(", postFee=").append(postFee);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", paymentTime=").append(paymentTime);
        sb.append(", consignTime=").append(consignTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", closeTime=").append(closeTime);
        sb.append(", shippingName=").append(shippingName);
        sb.append(", shippingCode=").append(shippingCode);
        sb.append(", userId=").append(userId);
        sb.append(", buyerMessage=").append(buyerMessage);
        sb.append(", buyerNick=").append(buyerNick);
        sb.append(", buyerComment=").append(buyerComment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
