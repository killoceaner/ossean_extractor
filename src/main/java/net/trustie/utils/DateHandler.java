package net.trustie.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

public class DateHandler {

	/**
	 * 获取抽取时间
	 * 
	 * @return 以YYYY-MM-DD hh:mm:ss形式的字符串返回
	 */
	public static String getExtractTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 判断Strings是否可以转换为yyyy-mm-dd HH:MM:SS形式的Date型
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean canFormatToDate(String... strings) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
		try {
			for (String s : strings) {
				sdf.parse(s);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 在系统时间上加上一个时间戳（秒）
	 * 
	 * @param dateTime
	 * @param second
	 * @return
	 */
	public static String addTimeToDate(String dateTime, int second) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(dateTime));
			c.add(Calendar.SECOND, second);
			return sdf.format(c.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static String formatAllTypeDate(String string) {
		if (StringHandler.canMatchRightString(string.trim(),
				"[0-9]{4,}-[0-9]{2,}-[0-9]{2,} [0-9]{2,}:[0-9]{2,}:[0-9]{2,}"))
			return string.trim();

		else if (StringHandler.canMatchRightString(string.trim(),
				"[0-9]{4,}-[0-9]{2,}-[0-9]{2,}"))
			return string.trim() + " 00:00:00";

		else if (StringHandler.canMatchRightString(string.trim(),
				"[0-9]{4,}-[0-9]{2,}-[0-9]{2,} [0-9]{2,}:[0-9]{2,}"))
			return string.trim() + ":00";

		else
			return handlerDefaultDate(string);
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	private static String handlerDefaultDate(String string) {
		if (StringHandler.canMatchRightStrings(string, "\\d+年", "前")
				|| StringHandler.canMatchRightStrings(string, "\\d+ years")) {
			int years = Integer.parseInt(StringHandler.matchRightString(string,
					"\\d+"));
			return addTimeToDate(dateToString(new Date()), -years * 365 * 24
					* 60 * 60);
		}

		else if (StringHandler.canMatchRightStrings(string, "半" + "年", "前")
				|| StringHandler.canMatchRightStrings(string, "half", "year"))
			return addTimeToDate(dateToString(new Date()), -183 * 24 * 60 * 60);

		else if (StringHandler.canMatchRightStrings(string, "\\d+个月", "前")
				|| StringHandler.canMatchRightString(string, "\\d months")) {
			int months = Integer.parseInt(StringHandler.matchRightString(
					string, "\\d+"));
			return addTimeToDate(dateToString(new Date()), -months * 30 * 24
					* 60 * 60);
		}

		else if (StringHandler.canMatchRightStrings(string, "半", "月", "前")
				|| StringHandler
						.canMatchRightStrings(string, "half ", " month"))
			return addTimeToDate(dateToString(new Date()), -15 * 24 * 60 * 60);

		else if (StringHandler.canMatchRightStrings(string, "\\d+小时", "前")
				|| StringHandler.canMatchRightStrings(string, "\\d+ hour")) {
			int hours = Integer.parseInt(StringHandler.matchRightString(string,
					"\\d+"));
			return addTimeToDate(dateToString(new Date()), -hours * 60 * 60);
		}

		else if (StringHandler.canMatchRightStrings(string, "半", "小时", "前")
				|| StringHandler.canMatchRightStrings(string, "half ", " hour"))
			return addTimeToDate(dateToString(new Date()), -30 * 60);

		else if (StringHandler.canMatchRightStrings(string, "\\d+分钟", "前")
				|| StringHandler.canMatchRightStrings(string, "\\d+ minute")) {
			int minutes = Integer.parseInt(StringHandler.matchRightString(
					string, "\\d+"));
			return addTimeToDate(dateToString(new Date()), -minutes * 60);
		}

		else if (StringHandler.canMatchRightStrings(string, "\\d+秒", "前")
				|| StringHandler.canMatchRightStrings(string, "\\d+ second")) {
			int seconds = Integer.parseInt(StringHandler.matchRightString(
					string, "\\d+"));
			return addTimeToDate(dateToString(new Date()), -seconds);
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		return df.format(date);
	}
}
