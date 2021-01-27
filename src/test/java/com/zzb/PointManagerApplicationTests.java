package com.zzb;

import com.zzb.entity.StUser;
import com.zzb.service.StProjectaboutService;
import com.zzb.service.StUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
class PointManagerApplicationTests {
@Autowired
StProjectaboutService stProjectaboutService;
@Autowired
    StUserService stUserService;
    @Test
    void contextLoads() throws ParseException {
//        String salt = SaltUtils.getSalt(8);
//        Md5Hash md5Hash = new Md5Hash("123456",salt,1024);
//        System.out.println(salt);
//        System.out.println(md5Hash);
        StUser user = stUserService.queryBySF("191223013");
        System.out.println(user);
    }

}
