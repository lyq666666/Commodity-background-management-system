package com.lyq.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式转换工具类
 */
public class DateUtils {
    //日期装换为特定格式的字符串
    public static String Date_to_String(Date date, String sformat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sformat);
        String format = simpleDateFormat.format(date);
        return format;
    }
    //将对应日期的字符串转换为日期
    public static Date String_to_Date(String format,String sformat) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sformat);
        Date parse = simpleDateFormat.parse(format);
        return parse;

    }
}
