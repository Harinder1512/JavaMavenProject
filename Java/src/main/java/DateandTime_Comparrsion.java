import java.util.Calendar;

public class DateandTime_Comparrsion {

	public static void main(String[] args) {
		
		Calendar cal = Calendar.getInstance();

		// System.out.println("cal ::::::"+cal);
		// Set time of calendar to 18:00

		cal.set(Calendar.HOUR_OF_DAY, 18);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// Check if current time is after 18:00 today
		boolean afterSix = Calendar.getInstance().after(cal);
		boolean beforenine = Calendar.getInstance().before(cal);

		System.out.println("afterSix ::: " + afterSix);

		System.out.println("beforenine ::: " + beforenine);

		if (afterSix && beforenine) {
			System.out.println("Inside");
		}

		if (beforenine) {
			System.out.println("Go home, it's after 6 PM! QQQ ");
		} else {
			System.out.println("Hello!");
		}

	}

}
