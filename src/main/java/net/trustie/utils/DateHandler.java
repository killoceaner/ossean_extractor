package net.trustie.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateHandler {
	private static Logger logger = LoggerFactory.getLogger("DateHandler");
	private static String dateFormat = "yyy-MM-dd HH:mm:ss";
	private static String[] dateType = {
			"[0-9]{4,}-[0-9]{2,}-[0-9]{2,} [0-9]{2,}:[0-9]{2,}:[0-9]{2,}",
			"[0-9]{4,}-[0-9]{2,}-[0-9]{2,}",
			"[0-9]{4,}-[0-9]{2,}-[0-9]{2,} [0-9]{2,}:[0-9]{2,}" };

	/**
	 * 获取抽取时间
	 * 
	 * @return 以yyy-MM-dd HH:mm:ss形式的字符串返回
	 */
	public static String getExtractTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 判断Strings是否可以转换为yyyy-mm-dd HH:MM:SS形式的Date型
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean canFormatToDate(String... strings) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		for (String s : strings) {
			try {
				sdf.parse(s);
			} catch (ParseException e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 字符串形式的date上加上一个时间（秒）
	 * 
	 * @param str
	 * @param second
	 * @return 字符串
	 */
	public static String addTimeToDate(String str, long second) {
		if (canFormatToDate(str)) {
			return addTimeToDate(stringToDate(str), second);
		}
		return null;
	}

	/**
	 * 在date类型时间上加上一个时间（秒）
	 * 
	 * @param dateTime
	 * @param second
	 * @return yyyy-mm-dd HH:mm:ss
	 */
	public static String addTimeToDate(Date date, long second) {
		return dateToString(timeStampToDate(dateToTimeStamp(date) + second
				* 1000));
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static String formatAllTypeDate(String string) {
		if (StringHandler.canMatchRightString(string.trim(), dateType[0]))
			return StringHandler.matchRightString(string, dateType[0]).trim();

		else if (StringHandler.canMatchRightString(string.trim(), dateType[1]))
			return StringHandler.matchRightString(string, dateType[1]).trim()
					+ " 00:00:00";

		else if (StringHandler.canMatchRightString(string.trim(), dateType[2]))
			return StringHandler.matchRightString(string, dateType[2]).trim()
					+ ":00";

		else
			return handlerDefaultDate(string);
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	private static String handlerDefaultDate(String string) {
		if (StringHandler.canMatchRightStrings(string, "\\d+", "年", "前")
				|| StringHandler.canMatchRightStrings(string, "\\d+", "years")) {
			int years = Integer.parseInt(StringHandler.matchRightString(string,
					"\\d+"));
			return addTimeToDate(new Date(), -years * 365 * 24 * 60 * 60);
		}

		else if (StringHandler.canMatchRightStrings(string, "半" + "年", "前")
				|| StringHandler.canMatchRightStrings(string, "half", "year"))
			return addTimeToDate(new Date(), -183 * 24 * 60 * 60);

		else if (StringHandler.canMatchRightStrings(string, "\\d+", "月", "前")
				|| StringHandler.canMatchRightStrings(string, "\\d", "months")) {
			int months = Integer.parseInt(StringHandler.matchRightString(
					string, "\\d+"));
			return addTimeToDate(new Date(), -months * 30 * 24 * 60 * 60);
		}

		else if (StringHandler.canMatchRightStrings(string, "半", "月", "前")
				|| StringHandler
						.canMatchRightStrings(string, "half ", " month"))
			return addTimeToDate(new Date(), -15 * 24 * 60 * 60);

		else if (StringHandler.canMatchRightStrings(string, "\\d+", "天", "前")
				|| StringHandler.canMatchRightStrings(string, "\\d+", "day")) {
			int days = Integer.parseInt(StringHandler.matchRightString(string,
					"\\d+"));
			return addTimeToDate(new Date(), -days * 24 * 60 * 60);
		}

		else if (StringHandler.canMatchRightStrings(string, "\\d+", "小时", "前")
				|| StringHandler.canMatchRightStrings(string, "\\d+", "hour")) {
			int hours = Integer.parseInt(StringHandler.matchRightString(string,
					"\\d+"));
			return addTimeToDate(new Date(), -hours * 60 * 60);
		}

		else if (StringHandler.canMatchRightStrings(string, "半", "小时", "前")
				|| StringHandler.canMatchRightStrings(string, "half ", " hour"))
			return addTimeToDate(new Date(), -30 * 60);

		else if (StringHandler.canMatchRightStrings(string, "\\d+", "分钟", "前")
				|| StringHandler.canMatchRightStrings(string, "\\d+", "minute")) {
			int minutes = Integer.parseInt(StringHandler.matchRightString(
					string, "\\d+"));
			return addTimeToDate(new Date(), -minutes * 60);
		}

		else if (StringHandler.canMatchRightStrings(string, "\\d+", "秒", "前")
				|| StringHandler.canMatchRightStrings(string, "\\d+", "second")) {
			int seconds = Integer.parseInt(StringHandler.matchRightString(
					string, "\\d+"));
			return addTimeToDate(new Date(), -seconds);
		}

		else
			return null;
	}

	/**
	 * 日期转化为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(date);
	}

	/**
	 * 字符串转化为Date
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.error("String:" + str + "Can Not Format To Date", e);
			return null;
		}

	}

	/**
	 * Date型转化为时间戳
	 * 
	 * @param date
	 * @return
	 */
	public static long dateToTimeStamp(Date date) {
		return date.getTime();
	}

	/**
	 * 时间戳转化为Date
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static Date timeStampToDate(long timeStamp) {
		return new Date(timeStamp);
	}
}
