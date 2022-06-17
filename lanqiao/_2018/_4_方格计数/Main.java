package lanqiao._2018._4_方格计数;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long ans = 0;
		// 优化，可以先计算内切正方形
//		for (int x = -50000; x < 50000; x++) {
//			for (int y = -50000; y < 50000; y++) {
//				if (dist(x, y, 0, 0) <= 50000 && dist(x, y + 1, 0, 0) <= 50000 && dist(x + 1, y, 0, 0) <= 50000
//						&& dist(x + 1, y + 1, 0, 0) <= 50000) {
//					ans++;
//				}
//			}
//		}
//		System.out.println(ans);
		System.out.println(7853781044L);
		scan.close();
	}

	private static double dist(int x, int y, int i, int j) {
		return Math.sqrt(Math.pow(x - i, 2) + Math.pow(y - j, 2));
	}
}
