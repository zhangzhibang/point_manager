package com.zzb.TO;

import com.zzb.VO.UserPointVO;
import lombok.Data;

import java.util.List;

/**
 * @author zzbang
 * @create 2021-01-27  9:37
 */

/**
 * 分页传输实体
 */
@Data
public class UserPointTO {
    private List<UserPointVO> list;
    private int total;
}
