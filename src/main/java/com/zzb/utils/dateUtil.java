package com.zzb.utils;

import com.zzb.entity.InformationPO;
import com.zzb.entity.RadioPO;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class dateUtil {
    public static RadioPO  SetRadio(List<InformationPO> list) throws ParseException {
        RadioPO radioPO=new RadioPO();
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar Afterdate = Calendar.getInstance();
        Calendar Beforedate = Calendar.getInstance();
        double radio2018=0.0;
        double radio2019=0.0;
        double radio2020=0.0;
        BigDecimal a;//除数
        BigDecimal b;//被除数
        if(!list.isEmpty()){
            Long countTime = daysBetween(list.get(0).getStartdate(), list.get(list.size() - 1).getEnddate());
            b=new BigDecimal(String.valueOf(countTime));
            Afterdate.setTime(list.get(list.size() - 1).getEnddate());
            Beforedate.setTime(list.get(0).getStartdate());
            if(Beforedate.get(Calendar.YEAR)==2018){
                if(Afterdate.get(Calendar.YEAR)==2018){
                  radio2018=1.0;
                }else if(Afterdate.get(Calendar.YEAR)==2019){
                    a=new BigDecimal(String.valueOf(daysBetween(list.get(0).getStartdate(), dateFormat1.parse("2018-12-31"))));
                    radio2018= a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    a=new BigDecimal(String.valueOf( daysBetween(dateFormat1.parse("2019-01-01"),  list.get(list.size() - 1).getEnddate())));
                    radio2019 = a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
                }else  if(Afterdate.get(Calendar.YEAR)==2020){
                    a=new BigDecimal(String.valueOf(daysBetween(list.get(0).getStartdate(), dateFormat1.parse("2018-12-31"))));
                    radio2018=a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    radio2019 = new BigDecimal("365.0").divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    a=new BigDecimal(String.valueOf(daysBetween(dateFormat1.parse("2020-01-01"), list.get(list.size() - 1).getEnddate())));
                    radio2020=a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
                }
            }else if(Beforedate.get(Calendar.YEAR)==2019){
                if(Afterdate.get(Calendar.YEAR)==2019){
                    radio2019=1.0;
                }else if(Afterdate.get(Calendar.YEAR)==2020){
                    a=new BigDecimal(String.valueOf(daysBetween(list.get(0).getStartdate(), dateFormat1.parse("2019-12-31"))));
                    radio2019=a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    a=new BigDecimal(String.valueOf(daysBetween(dateFormat1.parse("2020-01-01"), list.get(list.size() - 1).getEnddate())));
                    radio2020= a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
                }
            }else {
                radio2020=1.0;
            }
            return radioPO.setRadio2018(radio2018).setRadio2019(radio2019).setRadio2020(radio2020);
        }
        return null;
    }


    public static Integer getYear(Date startDate,Date endDate){
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(startDate);
        aft.setTime(endDate);
        int surplus = aft.get(Calendar.DATE) - bef.get(Calendar.DATE);
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int year = aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR);
        return year;
    }

    public static Long daysBetween(Date smdate,Date bdate) throws ParseException
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        //System.out.println(between_days);
        return between_days;
    }


}
