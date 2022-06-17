package lanqiao.test;

import java.util.Arrays;
import java.util.Scanner;

public class Main1 {
	public Main1() {
		Scanner scan = new Scanner(System.in);
		int[] cnt = new int[10];
		Arrays.fill(cnt, 2021);
		int n = 1;
		int m = 0;
		int r=0;
		out:while (true) {
			m = n;
			while (m > 0) {
				r=m % 10;
				cnt[r]--;
				if (cnt[r] < 0) {
					n--;
					break out;
				}
				m /= 10;
			}
			n++;
		}
		System.out.println(n);
		scan.close();
	}

	public static void main(String[] args) {
		new Main1();
	}
}
