package calendar_view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 项目名称：data
 * 类描述：
 * 创建人：qq2
 * 创建时间：2015/11/6 11:36
 * 修改人：qq2
 * 修改时间：2015/11/6 11:36
 * 修改备注：
 */
public class CalendarUtil {

    /**
     * 获取当月的 天数
     * */
    public static int getCurrentMonthDay() {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据年 月 获取对应的月份 天数
     * */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据日期 找到对应日期的 星期
     */
    public static String getDayOfWeekByDate(DateBean dateBean) {
        String dayOfweek = "-1";
        String date="";
        try {
            date=dateBean.getYear()+"-"+dateBean.getMonth()+"-"+dateBean.getDay();
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("E");
            String str = formatter.format(myDate);
            dayOfweek = str;

        } catch (Exception e) {
            System.out.println("错误!");
        }
        return dayOfweek;
    }

    public  static int getDayOfWeekByDate(String weekStr){
        int week=-1;
        if("周日".equals(weekStr)){
            week=0;
        }else if("周一".equals(weekStr)){
            week=1;
        }else if("周二".equals(weekStr)){
            week=2;
        }else if("周三".equals(weekStr)){
            week=3;
        }else if("周四".equals(weekStr)){
            week=4;
        }else if("周五".equals(weekStr)){
            week=5;
        }else if("周六".equals(weekStr)){
            week=6;
        }
        return week;
    }
    public  static String getMonthEN(int month){
        String monthEN="";
        if(month==1){
            monthEN="JAN";
        }else if(month==2){
            monthEN="FEB";
        }else if(month==3){
            monthEN="MAR";
        }else if(month==4){
            monthEN="APR";
        }else if(month==5){
            monthEN="MAY";
        }else if(month==6){
            monthEN="JUN";
        }else if(month==7){
            monthEN="JUL";
        }else if(month==8){
            monthEN="AUG";
        }else if(month==9){
            monthEN="SEP";
        }else if(month==10){
            monthEN="OCT";
        }else if(month==11){
            monthEN="NOV";
        }else if(month==12){
            monthEN="DEC";
        }
        return monthEN;
    }

    public  static String getMonthENLower(int month){
        String monthEN="";
        if(month==1){
            monthEN="Jan";
        }else if(month==2){
            monthEN="Feb";
        }else if(month==3){
            monthEN="Mar";
        }else if(month==4){
            monthEN="Apr";
        }else if(month==5){
            monthEN="May";
        }else if(month==6){
            monthEN="Jun";
        }else if(month==7){
            monthEN="Jul";
        }else if(month==8){
            monthEN="Aug";
        }else if(month==9){
            monthEN="Sep";
        }else if(month==10){
            monthEN="Oct";
        }else if(month==11){
            monthEN="Nov";
        }else if(month==12){
            monthEN="Dec";
        }
        return monthEN;
    }

}
