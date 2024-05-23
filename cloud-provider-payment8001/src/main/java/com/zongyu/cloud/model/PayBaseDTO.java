package com.zongyu.cloud.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 表名：t_pay
 * 表注释：支付交易表
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(title = "支付流水entity")
public class PayBaseDTO implements Serializable {

    /**
     * 支付流水号
     */
    @Schema(title = "支付流水号")
    private String payNo;

    /**
     * 订单流水号
     */
    @Schema(title = "订单流水号")
    private String orderNo;

    /**
     * 用户账单id
     */
    @Schema(title = "用户账单id")
    private Integer userId;

    /**
     * 交易金额
     */
    @Schema(title = "交易金额")
    private BigDecimal amount;

    /**
     * 删除标志，默认0不删除，1删除
     */
    @Schema(title = "删除标志，默认0不删除，1删除")
    private Byte deleted;
}