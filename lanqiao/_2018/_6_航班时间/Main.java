package lanqiao._2018._6_航班时间;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		// 在此输入您的代码...
		int n = scan.nextInt();
		scan.nextLine();
		for (int i = 0; i < n; i++) {
			String line1 = scan.nextLine();
			String line2 = scan.nextLine();
			long flightTime = (diff(line2) + diff(line1)) / 2;
			Date date = new Date(flightTime);
			// depreciated
			date.setMinutes(date.getMinutes()+date.getTimezoneOffset());
			SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
			System.out.println(fmt.format(date));
		}
		scan.close();
	}

	private static long diff(String line) throws Exception {
		String[] strings = line.split(" ");
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
		Date t1 = fmt.parse(strings[0]);
		String s2 = strings[1];
		Date t2 = fmt.parse(s2);

		long dt = t2.getTime() - t1.getTime();
		if (strings.length == 3) {
			int add = Integer.valueOf(strings[2].charAt(2) + "");
			dt += 1000 * 60 * 60 * 24 * add;
		}
		return dt;
	}

}
