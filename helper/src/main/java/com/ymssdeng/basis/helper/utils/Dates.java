package com.ymssdeng.basis.helper.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.http.util.Args;

/**
 * Date Utilities
 * 
 * @author hui.deng
 *
 */
public class Dates {

  /**
   * Format Date
   * 
   * @param date
   * @param format
   * @return
   */
  public static String format(Calendar date, String format) {
    Args.notNull(date, "date");
    Args.notNull(format, "format");
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date.getTime());
  }

  /**
   * Format now
   * 
   * @param format
   * @return
   */
  public static String formatNow(String format) {
    return format(now(), format);
  }

  /**
   * Current date and time
   * 
   * @return
   */
  public static Calendar now() {
    return Calendar.getInstance();
  }

  /**
   * Current date and time with specified format
   * 
   * @return
   */
  public static Calendar now(String format) throws ParseException {
    return parse(formatNow(format), format);
  }

  /**
   * Date and time before now
   * 
   * @param mills
   * @return
   */
  public static Calendar beforeNow(long mills) {
    return before(now(), mills);
  }

  /**
   * Date and time after now
   * 
   * @param mills
   * @return
   */
  public static Calendar afterNow(long mills) {
    return after(now(), mills);
  }

  /**
   * Date and time before specified calendar
   * 
   * @param base
   * @param mills
   * @return
   */
  public static Calendar before(Calendar base, long mills) {
    Args.notNull(base, "base");
    base.setTimeInMillis(base.getTimeInMillis() - mills);
    return base;
  }

  /**
   * Date and time after specified calendar
   * 
   * @param base
   * @param mills
   * @return
   */
  public static Calendar after(Calendar base, long mills) {
    Args.notNull(base, "base");
    base.setTimeInMillis(base.getTimeInMillis() + mills);
    return base;
  }

  /**
   * Parse string as date using format
   * 
   * @param date
   * @param format
   * @return
   * @throws ParseException
   */
  public static Calendar parse(String date, String format) throws ParseException {
    Args.notNull(date, "date");
    Args.notNull(format, "format");
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    Calendar calendar = now();
    calendar.setTime(sdf.parse(date));
    return calendar;
  }

  /**
   * Check whether string can be parsed as date using format
   * 
   * @param date
   * @param format
   * @return
   */
  public static boolean isValid(String date, String format) {
    try {
      parse(date, format);
      return true;
    } catch (ParseException e) {
      return false;
    }
  }
}
