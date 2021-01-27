package com.zzb.TO;

import com.zzb.entity.InformationVO;
import lombok.Data;

import java.util.List;

/**
 * @author zzbang
 * @create 2021-01-27  9:23
 */
@Data
public class selectTO {
    private List<InformationVO> list;
    private int total;
}
