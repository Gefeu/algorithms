package lanqiao._2019._1_平方和;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 在此输入您的代码...
		long ans = 0;
		for (int i = 1; i <= 2019; i++) {
			String s = i + "";
			if (s.indexOf('2') >= 0 || s.indexOf('0') >= 0 || s.indexOf('1') >= 0 || s.indexOf('9') >= 0) {
				ans += i * i;
			}
		}
		System.out.println(ans);
		scan.close();
	}
}
