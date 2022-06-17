package lanqiao._2020._8_子串分值_逆向思维;

import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		char ch[];
		String s;
		int ans = 0;
		Scanner scan = new Scanner(System.in);
		s = scan.next();
		ch = s.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			int[] h = new int[26];
			int sum = 0;
			for (int j = i; j < ch.length; j++) {
				int idx = ch[j] - 97;
				if (h[idx]==0) {
					sum++;
					h[idx] = 1;
				}else if (h[idx]==1) {
					sum--;
					h[idx]=2;
				}
				ans += sum;
			}
		}

		System.out.println(ans);
		scan.close();
	}

}