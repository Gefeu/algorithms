package lanqiao._2018._10_付账问题_贪心;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 在此输入您的代码...
		int n = scan.nextInt();
		long S = scan.nextLong();
		long[] a = new long[n];
		for (int i = 0; i < n; i++) {
			a[i] = scan.nextLong();
		}

		Arrays.sort(a);
		double avg = (double) S / n;
		double newAvg = 0;
		double ans = 0;

		int i = 0;
		for (; i < n; i++) {
			long ai = a[i];
			if (ai <= avg) {
				ans += Math.pow(ai - avg, 2);
				S -= ai;
			} else {
				newAvg = (double) S / (n - i);
				break;
			}
		}
		for (; i < n; i++) {
			ans += Math.pow(newAvg - avg, 2);
		}

		ans = Math.sqrt(ans / n);
		System.out.printf("%.4f", ans);
		scan.close();
	}
}
