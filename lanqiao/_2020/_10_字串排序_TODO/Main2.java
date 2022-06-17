package lanqiao._2020._10_字串排序_TODO;

import java.util.*;

public class Main2 {
	private static int V, len, now;
	private static int[] cnt = new int[27], sum = new int[27];

	private static int get_max(int len) {
		return ((len - (len / 26 + 1)) * (len / 26 + 1) * (len % 26)
				+ (26 - len % 26) * (len / 26) * (len - len / 26)) / 2;
	}

	private static boolean check(int x, int n) {
		Arrays.fill(cnt, 0);
		int add1 = 0;
		int add2 = 0;
		for (int j = 26; j >= x + 1; j--) {
			add1 += sum[j];
		}
		sum[x]++;
		for (int L = 1; L <= n; L++) {
			int ma = -1;
			int pos = 0;
			int num = 0;
			for (int j = 26; j >= 1; j--) {
				if (L - 1 - cnt[j] + num > ma) {
					ma = L - 1 - cnt[j] + num;
					pos = j;
				}
				num += sum[j];
			}
			add2 += ma;
			cnt[pos]++;
		}
		if (now + add1 + add2 >= V) {
			now += add1;
			return true;
		} else {
			sum[x]--;
			return false;
		}
	}

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		String ans = "";
		V = cin.nextInt();
		for (int i = 1;; i++) {
			if (get_max(i) >= V) {
				len = i;
				break;
			}
		}
		for (int i = 1; i <= len; i++) {
			for (int j = 1; j <= 26; j++) {
				if (check(j, len - i)) {
					ans += (char) (j + 'a' - 1);
					break;
				}
			}
		}
		System.out.println(ans);
		cin.close();
	}
}