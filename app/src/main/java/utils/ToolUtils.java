package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolUtils {
    private ToolUtils(){

    }
    /**
     * strDate格式 HHmmssSSS
     * @param strDate
     * @return
     */
    public static Date str2date(String strDate) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmssSSS");
        try {
            date = formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return date;
        }
    }

    /**
     * 返回秒级时间差
     * @param date1
     * @param date2
     * @return
     */
    public static int computeDiff(Date date1, Date date2) {
        //这样得到的差值是毫秒级别
        long diff = date2.getTime() - date1.getTime();
        int seconds = (int)(diff/1000);
        return seconds;
    }
}
