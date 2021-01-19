package com.zzb;

import com.zzb.entity.UserPointVO;
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
    StUserstageService stUserstageService;
    @Test
    void contextLoads() throws ParseException {
        List<UserPointVO> userPoint = stUserstageService.getUserPoint();
        System.out.println(userPoint);
    }

}
