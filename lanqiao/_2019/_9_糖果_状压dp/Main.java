package lanqiao._2019._9_糖果_状压dp;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt(); // N包糖果
		int M = scan.nextInt(); // M种口味
		int K = scan.nextInt(); // 每包糖果K颗
		int[] a = new int[N];
		for (int i = 0; i < N; i++) {
			int n = 0;
			for (int j = 0; j < K; j++) {
				n |= 1 << (scan.nextInt() - 1);
			}
			a[i] = n;
		}

		final int INF = 0x0fffff;
		int len = 1 << M;
		int[] f = new int[len];
		Arrays.fill(f, INF);
		f[0] = 0;
		for (int n : a) {
			for (int i = 0; i < len; i++) {
				f[n | i] = Math.min(f[i] + 1, f[n | i]);
			}
		}
		System.out.println(f[len - 1] == INF ? -1 : f[len - 1]);
		scan.close();
	}
}
