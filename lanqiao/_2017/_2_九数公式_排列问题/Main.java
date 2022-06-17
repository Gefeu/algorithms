package lanqiao._2017._2_九数公式_排列问题;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
	static int res = 0;
	static int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		scan.close();
		p(0);
		System.out.println(res / 2);
		// System.out.println(1625);
	}

	private static void p(int k) {
		if (k == 9) {
			for (int s = 1; s < 9; s++) {
				int x = a2i(0, s);
				int y = a2i(s, 9);
				int z = x * y;
				check(z);
			}
			return;
		}

		for (int i = k; i < 9; i++) {
			swap(k, i);
			p(k + 1);
			swap(k, i);
		}
	}

	private static void check(int z) {
		String s = "" + z;
		if (s.length() != 9 || s.indexOf('0') > -1) {
			return;
		}
		HashSet<Character> hashSet = new HashSet<Character>();
		for (int i = 0; i < 9; i++) {
			hashSet.add(s.charAt(i));
		}
		if (hashSet.size() == 9) {
			res++;
		}
	}

	private static int a2i(int beg, int end) {
		int res = 0;
		int p = 1;
		for (int i = end - 1; i >= beg; i--) {
			res += a[i] * p;
			p *= 10;
		}
		return res;
	}

	private static void swap(int k, int i) {
		int t = a[k];
		a[k] = a[i];
		a[i] = t;
	}
}
