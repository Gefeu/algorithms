package lanqiao._2020._10_字串排序_TODO;

import java.util.Scanner;
import static java.lang.Math.*;

public class Main {
	int v, n;
	int[] s;

	int preInv;
	int[] cnt = new int[256]; // 已添加的字母计数

//	int minChar = 'z'+1;

	public Main() {
		Scanner scan = new Scanner(System.in);
		v = scan.nextInt();
		n = 1;
		while (getMaxInv(n) < v) {
			n++;
		}
		System.out.println(n);

		s = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			for (int ch = 'a'; ch <= 'z'; ch++) {
				if (check(i, ch)) {
					s[i] = ch;
					break;
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			System.out.print((char) s[i]);
		}
		scan.close();
	}

	boolean check(int i, int ch) {
		int add1 = 0;

		for (int c = 'a'; c <= 'z'; c++) {
			add1 += c > ch ? cnt[c] : 0;
		}

		cnt[ch]++;
		int[] counts = new int[256];

		int add2 = 0;
		for (int j = 1; i + j <= n; j++) {
			int maxiv = 0;
			int maxch = 'a';
			for (int c = 'a'; c <= 'z'; c++) {
				int iv = j - 1 - counts[c];
				for (int k = 'a'; k <= 'z'; k++)
					iv += k > c ? cnt[k] : 0;
					
				if (iv >= maxiv) {
					maxiv = iv;
					maxch = c;
				}
			}
			add2 += maxiv;
			counts[maxch]++;
		}
		cnt[ch]--;

		int res = preInv + add1 + add2;
		if (res >= v) {
			preInv += add1;
			cnt[ch]++;
			return true;
		}
		return false;
	}

	private int getMaxInv(int n) {
		int res = 0;

		int m = n / 26 + 1, k = n % 26;
		res += k * m * (n - m);

		m = n / 26;
		k = n - n % 26;
		res += k * m * (n - m);

		return res / 2; // 一对算了两次
	}

	public static void main(String[] args) {
		new Main();
	}
}
