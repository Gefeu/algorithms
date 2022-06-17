package lanqiao._2020._8_子串分值_逆向思维;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int ans = 0;

		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		int[] prev = new int[s.length()];
		int[] next = new int[s.length()];
		int[] pre = new int[26];
		int[] nex = new int[26];
		Arrays.fill(pre, -1);
		Arrays.fill(nex, s.length());
		for (int i = 0; i < s.length(); i++) {
			int idx = s.charAt(i) - 'a';
			prev[i] = pre[idx];
			pre[idx] = i;
		}
		for (int i = s.length() - 1; i >= 0; i--) {
			int idx = s.charAt(i) - 'a';
			next[i] = nex[idx];
			nex[idx] = i;
		}

		for (int i = 0; i < s.length(); i++) {
			ans += (i - prev[i]) * (next[i] - i);
		}

		System.out.println(ans);
		scan.close();
	}

}