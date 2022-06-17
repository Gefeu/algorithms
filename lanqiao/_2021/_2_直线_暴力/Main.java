package lanqiao._2021._2_直线_暴力;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 在此输入您的代码...
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < 20 * 21; i++) {
			int x1 = i / 21;
			int y1 = i % 21;
			for (int j = i + 1; j < 20 * 21; j++) {
				int x2 = j / 21;
				int y2 = j % 21;
				int d = gcd(y2 - y1, x2 - x1);
				int a = (x2 - x1) / d;
				int b = (y2 - y1) / d;
				int c = b * x1 - a * y1;
				set.add( a +" "+ b+" " + c);
			}
		}
		System.out.println(set.size());
		scan.close();
	}

	private static int gcd(int i, int j) {
		return j == 0 ? i : gcd(j, i % j);
	}
}
