package lanqiao._2018._2_星期一;

import java.util.Scanner;
import java.time.*;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		LocalDate date = LocalDate.of(1901, 1, 1);
		LocalDate end = LocalDate.of(2000, 12, 31);
		int n = 0;
		while (date.isBefore(end)) {
			if (date.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
				n++;
			}
			date = date.plusDays(1);
		}
		if (end.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
			n++;
		}
		System.out.println(n);
		scan.close();
	}
}
