package lanqiao.test;

import java.util.Scanner;

public class Main4 {
	public Main4() {
		Scanner scan = new Scanner(System.in);
		long N = scan.nextLong();
		long beg = System.currentTimeMillis();
		long n = 1;
		long m = 0;
		long s = 1;
		while (s < N) {
			if ((n & 1) == 0) {
				s = s * (n + 1) / (n - m + 1);
			} else {
				s = s * (n + 1) / (m + 1);
				m++;
			}
			n++;
		}
		long pre = 0;
		while (true) {
			while (s > N) {
				pre = s;
				s = s * m / (n - m + 1);
				m--;
			}
			if (s == N)
				break;
			s = s + pre;
			n++;
			m++;
		}
		System.out.println(n + " " + m);
		long end = System.currentTimeMillis();
		System.out.printf("%.3f", (end - beg) / 1000.0);
		scan.close();
	}

	public static void main(String[] args) {
		new Main4();
	}
}
