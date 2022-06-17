package lanqiao._2020._7_回文日期_字符串模式判断;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws ParseException {
		Scanner scan = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate d = LocalDate.parse(scan.next(), fmt);
		String s = null;

		LocalDate dd = d.plusDays(1);
		while (true) {
//			if (dd.getYear() == 9999 && dd.getMonthValue() == 12 && dd.getDayOfMonth() == 31) {
//				break;
//			}
			s = fmt.format(dd);
			boolean b = new StringBuilder(s).reverse().toString().equals(s);
			if (b) {
				System.out.println(fmt.format(dd));
				break;
			}
			dd = dd.plusDays(1);
		}

		dd = d.plusDays(1);
		while (true) {
//			if (dd.getYear() == 9999 && dd.getMonthValue() == 12 && dd.getDayOfMonth() == 31) {
//				break;
//			}
			s = fmt.format(dd);
			boolean b = s.charAt(2) == s.charAt(0) && s.charAt(5) == s.charAt(0) && s.charAt(7) == s.charAt(0)
					&& s.charAt(3) == s.charAt(1) && s.charAt(4) == s.charAt(1) && s.charAt(6) == s.charAt(1);
			if (b) {
				System.out.println(fmt.format(dd));
				break;
			}
			dd = dd.plusDays(1);
		}
		scan.close();
	}
}
