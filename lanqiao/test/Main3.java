package lanqiao.test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main3 {
	public Main3() {
		Scanner scan = new Scanner(System.in);
		long n = scan.nextLong();
		Instant ins = Instant.ofEpochMilli(n);
		LocalDateTime dateTime = LocalDateTime.ofInstant(ins,
				ZoneOffset.UTC);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
		System.out.println(fmt.format(dateTime));
		

		scan.close();
	}

	public static void main(String[] args) {
		new Main3();
	}
}
