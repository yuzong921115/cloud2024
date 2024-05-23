package com.zongyu.cloud.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(title = "修改支付流水entity")
public class PayExtDTO extends PayBaseDTO implements Serializable {
    @Schema(title = "主键id")
    private Integer id;
}