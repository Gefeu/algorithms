package lanqiao._2020._2_既约分数;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int ans = 0;
		for (int i = 1; i <= 2020; i++) {
			for (int j = 1; j <= 2020; j++) {
				if (gcd(i, j) == 1) {
					ans++;
				}
			}
		}
		System.out.println(ans);
		scan.close();
	}

	private static int gcd(int i, int j) {
		return j == 0 ? i : gcd(j, i % j);
	}
}
