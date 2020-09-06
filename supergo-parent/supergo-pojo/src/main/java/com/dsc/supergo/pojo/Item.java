package com.dsc.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value="com.dsc.supergo.pojo.Item")
@Table(name = "tb_item")
public class Item implements Serializable {
    /**
     * 商品id，同时也是商品编号
     */
    @Id
    @ApiModelProperty(value="id商品id，同时也是商品编号")
    private Long id;

    /**
     * 商品标题
     */
    @ApiModelProperty(value="title商品标题")
    private String title;

    /**
     * 商品卖点
     */
    @Column(name = "sell_point")
    @ApiModelProperty(value="sellPoint商品卖点")
    private String sellPoint;

    /**
     * 商品价格
     */
    @ApiModelProperty(value="price商品价格")
    private BigDecimal price;

    /**
     * 库存数量
     */
    @ApiModelProperty(value="num库存数量")
    private Integer num;

    /**
     * 售卖数量限制
     */
    @Column(name = "limit_num")
    @ApiModelProperty(value="limitNum售卖数量限制")
    private Integer limitNum;

    /**
     * 商品图片
     */
    @ApiModelProperty(value="image商品图片")
    private String image;

    /**
     * 所属分类
     */
    @ApiModelProperty(value="cid所属分类")
    private Long cid;

    /**
     * 商品状态 1正常 0下架
     */
    @ApiModelProperty(value="status商品状态 1正常 0下架")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="created创建时间")
    private Date created;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="updated更新时间")
    private Date updated;
    /**
     * 剩余库存
     */
    @Column(name = "stock_count")
    @ApiModelProperty(value="stockCount剩余库存")
    private Integer stockCount;

    private static final long serialVersionUID = 1L;

    public Item(Long id, String title, String sellPoint, BigDecimal price, Integer num, Integer limitNum, String image, Long cid, Integer status, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.sellPoint = sellPoint;
        this.price = price;
        this.num = num;
        this.limitNum = limitNum;
        this.image = image;
        this.cid = cid;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    public Item() {
        super();
    }

    /**
     * 获取商品id，同时也是商品编号
     *
     * @return id - 商品id，同时也是商品编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置商品id，同时也是商品编号
     *
     * @param id 商品id，同时也是商品编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商品标题
     *
     * @return title - 商品标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置商品标题
     *
     * @param title 商品标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取商品卖点
     *
     * @return sell_point - 商品卖点
     */
    public String getSellPoint() {
        return sellPoint;
    }

    /**
     * 设置商品卖点
     *
     * @param sellPoint 商品卖点
     */
    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    /**
     * 获取商品价格
     *
     * @return price - 商品价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     *
     * @param price 商品价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取库存数量
     *
     * @return num - 库存数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 获取剩余库存
     *
     * @return stock_count - 剩余库存
     */
    public Integer getStockCount() {
        return stockCount;
    }

    /**
     * 设置剩余库存
     *
     * @param stockCount 剩余库存
     */
    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    /**
     * 设置库存数量
     *
     * @param num 库存数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取售卖数量限制
     *
     * @return limit_num - 售卖数量限制
     */
    public Integer getLimitNum() {
        return limitNum;
    }

    /**
     * 设置售卖数量限制
     *
     * @param limitNum 售卖数量限制
     */
    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    /**
     * 获取商品图片
     *
     * @return image - 商品图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置商品图片
     *
     * @param image 商品图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取所属分类
     *
     * @return cid - 所属分类
     */
    public Long getCid() {
        return cid;
    }

    /**
     * 设置所属分类
     *
     * @param cid 所属分类
     */
    public void setCid(Long cid) {
        this.cid = cid;
    }

    /**
     * 获取商品状态 1正常 0下架
     *
     * @return status - 商品状态 1正常 0下架
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置商品状态 1正常 0下架
     *
     * @param status 商品状态 1正常 0下架
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取更新时间
     *
     * @return updated - 更新时间
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 设置更新时间
     *
     * @param updated 更新时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", sellPoint=").append(sellPoint);
        sb.append(", price=").append(price);
        sb.append(", num=").append(num);
        sb.append(", limitNum=").append(limitNum);
        sb.append(", image=").append(image);
        sb.append(", cid=").append(cid);
        sb.append(", status=").append(status);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
