package com.zzb.TO;

import com.zzb.VO.InformationVO;
import lombok.Data;

import java.util.List;

/**
 * @author zzbang
 * @create 2021-01-27  9:23
 */

/**
 * 进行分页的时候传输的实体
 */
@Data
public class selectTO {
    private List<InformationVO> list;
    private int total;
}
