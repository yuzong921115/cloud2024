package com.zongyu.cloud.model;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 表名：t_order
 * 表注释：订单表
 */
@Table(name = "t_order")
@ToString
public class Order implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 产品ID
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 金娥
     */
    private Long money;

    /**
     * 订单状态（0创建中；1已完成）
     */
    private Integer status;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return userId - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取产品ID
     *
     * @return productId - 产品ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置产品ID
     *
     * @param productId 产品ID
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取数量
     *
     * @return count - 数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置数量
     *
     * @param count 数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取金娥
     *
     * @return money - 金娥
     */
    public Long getMoney() {
        return money;
    }

    /**
     * 设置金娥
     *
     * @param money 金娥
     */
    public void setMoney(Long money) {
        this.money = money;
    }

    /**
     * 获取订单状态（0创建中；1已完成）
     *
     * @return status - 订单状态（0创建中；1已完成）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态（0创建中；1已完成）
     *
     * @param status 订单状态（0创建中；1已完成）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}