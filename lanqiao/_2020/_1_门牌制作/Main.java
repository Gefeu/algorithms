
package lanqiao._2020._1_门牌制作;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 在此输入您的代码...
		int ans = 0;
		for (int i = 0; i <= 2020; i++) {
			String s = i + "";
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '2') {
					ans++;
				}

			}
		}
		System.out.println(ans);
		scan.close();
	}
}
