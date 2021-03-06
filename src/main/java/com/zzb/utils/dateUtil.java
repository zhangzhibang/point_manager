package com.zzb.utils;

import com.zzb.PO.InformationPO;
import com.zzb.PO.RadioPO;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class dateUtil {
    /**
     * 工具类，传入一组时间数据，判断项目在每年的时间占整个项目时间的比例
     * @param
     * @return
     * @throws ParseException
     */
    public static RadioPO  SetRadio(List<InformationPO> all) throws ParseException {
        RadioPO radioPO=new RadioPO();
        List<InformationPO> list = all.stream().filter(item -> {
            return item.getStartdate() != null && item.getEnddate() != null&!"".equals(item.getStartdate())&&!"".equals(item.getEnddate());
        }).collect(Collectors.toList());
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar Afterdate = Calendar.getInstance();
        Calendar Beforedate = Calendar.getInstance();
        int index=0;
        double radio2018=0.0;
        double radio2019=0.0;
        double radio2020=0.0;
        BigDecimal a;//除数
        BigDecimal b;//被除数
        if(!list.isEmpty()){
            Long countTime = daysBetween(list.get(0).getStartdate(), list.get(list.size() - 1).getEnddate());
            b=new BigDecimal(String.valueOf(countTime));
            Afterdate.setTime(dateFormat1.parse(list.get(list.size() - 1).getEnddate()));
            Beforedate.setTime(dateFormat1.parse(list.get(0).getStartdate()));
            if(Beforedate.get(Calendar.YEAR)==2018){
                if(Afterdate.get(Calendar.YEAR)==2018){
                  radio2018=1.0;
                }else if(Afterdate.get(Calendar.YEAR)==2019){
                    a=new BigDecimal(String.valueOf(daysBetween(list.get(0).getStartdate(), "2018-12-31")));
                    radio2018= a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    a=new BigDecimal(String.valueOf( daysBetween("2019-01-01",  list.get(list.size() - 1).getEnddate())));
                    radio2019 = a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
                }else  if(Afterdate.get(Calendar.YEAR)==2020){
                    a=new BigDecimal(String.valueOf(daysBetween(list.get(0).getStartdate(), "2018-12-31")));
                    radio2018=a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    radio2019 = new BigDecimal("365.0").divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    a=new BigDecimal(String.valueOf(daysBetween("2020-01-01", list.get(list.size() - 1).getEnddate())));
                    radio2020=a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
                }
            }else if(Beforedate.get(Calendar.YEAR)==2019){
                if(Afterdate.get(Calendar.YEAR)==2019){
                    radio2019=1.0;
                }else if(Afterdate.get(Calendar.YEAR)==2020){
                    a=new BigDecimal(String.valueOf(daysBetween(list.get(0).getStartdate(), "2019-12-31")));
                    radio2019=a.divide(b,2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    a=new BigDecimal(String.valueOf(daysBetween("2020-01-01", list.get(list.size() - 1).getEnddate())));
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

    public static Long daysBetween(String smdate,String bdate) throws ParseException
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(simpleDateFormat.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(simpleDateFormat.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return between_days;
    }


}
