/**
 * 
 */
package extension;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 处理日期
 * 
 * @author kg
 *
 */
public class DateHandler {
	private static Date origin = new Date(70, 01, 01);

	public static Date getDateBefore(int num, String unit) {
		num = num * (-1);
		Calendar ca = new GregorianCalendar();
		switch (unit) {
		case "YEAR": {
			ca.add(Calendar.YEAR, num);
			break;
		}
		case "MONTH": {
			ca.add(Calendar.MONTH, num);
			break;
		}
		case "WEEK": {
			ca.add(Calendar.WEEK_OF_MONTH, num);
			break;
		}
		case "DAY": {
			ca.add(Calendar.DAY_OF_MONTH, num);
			break;
		}
		case "HOUR": {
			ca.add(Calendar.HOUR, num);
			break;
		}
		case "MINUTE": {
			ca.add(Calendar.MINUTE, num);
			break;
		}
		case "SECOND": {
			ca.add(Calendar.SECOND, num);
			break;
		}
		default: {
			ca.setTime(DateHandler.origin);
			break;
		}
		}

		return ca.getTime();

	}
}
