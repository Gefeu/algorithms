package lanqiao._2017._8_包子凑数_不定方程和动态规划;

import java.util.Scanner;

public class Main {
	static int[] a = new int[100];
	static boolean[] f = new boolean[10000];
	static int g;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		f[0] = true;
		for (int i = 0; i < n; i++) {
			a[i] = scan.nextInt();
			if (i == 0) {
				g = a[i];
			} else {
				g = gcd(g, a[i]);
			}
			for (int j = 0; j < 10000; j++) {
				int jj = j + a[i];
				if (f[j] && jj < 10000) {
					f[jj] = true;
				}
			}
		}
		scan.close();
		if (g != 1) {
			System.out.println("INF");
			return;
		}

		int cnt = 0;
		for (boolean ff : f) {
			if (!ff) {
				cnt++;
			}
		}
		System.out.println(cnt);

	}

	private static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
