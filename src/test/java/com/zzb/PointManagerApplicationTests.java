package com.zzb;

import com.zzb.entity.InformationPO;
import com.zzb.entity.InformationVO;
import com.zzb.entity.UserPointVO;
import com.zzb.service.StProjectaboutService;
import com.zzb.service.StUserstageService;
import com.zzb.utils.dateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class PointManagerApplicationTests {
@Autowired
StProjectaboutService stProjectaboutService;
    @Test
    void contextLoads() throws ParseException {
        List<InformationPO> list = stProjectaboutService.queryAllInformation();
         List<InformationVO> informationVOS = stProjectaboutService.sortList(list);
        System.out.println(informationVOS);
    }

}
