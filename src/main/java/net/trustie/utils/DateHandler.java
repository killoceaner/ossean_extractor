package net.trustie.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
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
			if (StringUtils.isBlank(s))
				return false;
			try {
				sdf.parse(s);
			} catch (ParseException e) {
				logger.error("String　[" + s + "] ## Can Not Format To Date", e);
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
		if (date == null)
			return null;
		return dateToString(timeStampToDate(dateToTimeStamp(date) + second
				* 1000));
	}

	/**
	 * 自动处理各种类型的日期类型字符串，匹配yyyy-mm-dd
	 * hh:mm:ss类型日期;补全yyyy-mm-dd类型的日期;补全补全yyyy-mm-dd hh:mm类型的日期;转化x(年 or 月 or
	 * 天)前类型的日期，同时支持英文。
	 * 
	 * @param string
	 * @return 日期以字符串形式返回
	 */
	public static String formatAllTypeDate(String string) {
		if (StringUtils.isBlank(string))
			return null;

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
	 * 转化x(年 or 月 or 天)前类型的日期，同时支持英文，日后还需扩展
	 * 
	 * @param string
	 * @return
	 */
	private static String handlerDefaultDate(String string) {
		if (StringHandler.canMatchRightStrings(string, "\\d+", "年", "前")
				|| StringHandler.canMatchRightStrings(string, "\\d+", "year")) {
			int years = Integer.parseInt(StringHandler.matchRightString(string,
					"\\d+"));
			return addTimeToDate(new Date(), -years * 365 * 24 * 60 * 60);
		}

		else if (StringHandler.canMatchRightStrings(string, "半" + "年", "前")
				|| StringHandler.canMatchRightStrings(string, "half", "year"))
			return addTimeToDate(new Date(), -183 * 24 * 60 * 60);

		else if (StringHandler.canMatchRightStrings(string, "\\d+", "月", "前")
				|| StringHandler.canMatchRightStrings(string, "\\d+", "mon")) {
			int months = Integer.parseInt(StringHandler.matchRightString(
					string, "\\d+"));
			return addTimeToDate(new Date(), -months * 30 * 24 * 60 * 60);
		}

		else if (StringHandler.canMatchRightStrings(string, "半", "月", "前")
				|| StringHandler
						.canMatchRightStrings(string, "half ", " mon"))
			return addTimeToDate(new Date(), -15 * 24 * 60 * 60);

		else if (StringHandler.canMatchRightStrings(string, "\\d+", "周", "前")
				|| StringHandler.canMatchRightStrings(string, "\\d+ ", " week")) {
			int weeks = Integer.parseInt(StringHandler.matchRightString(string,
					"\\d+"));
			return addTimeToDate(new Date(), -weeks * 7 * 24 * 60 * 60);
		}

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
				|| StringHandler.canMatchRightStrings(string, "\\d+", "min")) {
			int minutes = Integer.parseInt(StringHandler.matchRightString(
					string, "\\d+"));
			return addTimeToDate(new Date(), -minutes * 60);
		}

		else if (StringHandler.canMatchRightStrings(string, "\\d+", "秒", "前")
				|| StringHandler.canMatchRightStrings(string, "\\d+", "sec")) {
			int seconds = Integer.parseInt(StringHandler.matchRightString(
					string, "\\d+"));
			return addTimeToDate(new Date(), -seconds);
		}

		else
			return null;
	}

	/**
	 * Date转化为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		if (date == null)
			return null;
		return new SimpleDateFormat(dateFormat).format(date);
	}

	/**
	 * 字符串转化为Date
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		if (StringUtils.isBlank(str))
			return null;
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.error("String [" + str + "] ## Can Not Format To Date", e);
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
