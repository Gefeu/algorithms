package lanqiao._2021._3_货物摆放_枚举因子减少迭代;

import java.util.Scanner;

public class Main1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long n = 2021041820210418L;
		long m = 0;
		long k = 0;
		int count = 0;
		int[] counts = {0, 1, 3, 6};
		long ans = 0;
		for (long i = 1; i * i * i <= n; i++) {
			if (n % i != 0) {
				continue;
			}
			m = n / i;
			// i<=j<=k;
			for (long j = i; j * j <= m; j++) {
				if (m % j != 0) {
					continue;
				}
				k = m / j;
				count = 1;
				if (i != j) {
					count++;
				}
				if (j != k) {
					count++;
				}
				ans += counts[count];
			}
		}

		System.out.println(ans);
		scan.close();
	}
}
