package lanqiao._2017._5_字母组串;

import java.util.*;

public class Main {
	// a个A，b个B，c个C 字母，能组成多少个不同的长度为n的串。
	static int f(int a, int b, int c, int n) {
		if (a < 0 || b < 0 || c < 0)
			return 0;
		if (n == 0)
			return 1;

		int bb = factorial(a);
		int combi = 0;
		int ans = 0;
		for (int i = 0; i <= a; i++) {
			for (int j = 0; j <= b; j++) {
				for (int k = 0; k <= c; k++) {
					if (i + j + k == n) {
						combi++;
						ans += factorial(n) / factorial(i) / factorial(j) / factorial(k);
					}
				}
			}
		}
		return ans; // 填空题 TODO
		// return f(a - 1, b, c, n - 1) 
		// + f(a, b - 1, c, n - 1)
		// + f(a, b, c - 1, n - 1); 
	}

	private static int factorial(int a) {
		if (a == 0) {
			return 1;
		} else {
			return a * factorial(a - 1);
		}
	}

	public static void main(String[] args) {
		System.out.println(f(1, 1, 1, 2));
		System.out.println(f(1, 2, 3, 3));
	}
}