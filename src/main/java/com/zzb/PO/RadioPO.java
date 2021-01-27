package com.zzb.PO;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 某个用户三年中每年的积分实体类
 */
@Data
@Accessors(chain = true)
public class RadioPO {
    private Double radio2018;
    private Double radio2019;
    private Double radio2020;
}
