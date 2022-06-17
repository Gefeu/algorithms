package lanqiao._2021._4_路径_最短路径;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.Scanner;

public class Floyd {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 佛洛依德算法
		final int INF = 0x3f3f3f3f;
		long begin = System.currentTimeMillis();
		int[][] f = new int[2021 + 1][2021 + 1]; // 邻接矩阵
		for (int i = 1; i <= 2021; i++) {
			for (int j = i; j <= 2021; j++) {
				if (i != j && abs(j - 1) <= 21) {
					f[j][i] = f[i][j] = lcm(i, j);
				} else {
					f[i][j] = INF;
				}
			}
		}

		for (int i = 1; i <= 2021; i++) {
			for (int j = 1; j <= 2021; j++) {
				for (int k = 1; k <= 2021; k++) { // 从1个增加到2021个节点，动态规划
					f[i][j] = min(f[i][j], f[i][k] + f[k][j]);
				}
			}
		}
		System.out.println((System.currentTimeMillis() - begin) / 1000.0);
		System.out.println(f[2021][2021]);
		// 47.849
		// 1061109567
		scan.close();

	}

	private static int lcm(int i, int j) {
		return i * j / gcd(i, j);
	}

	private static int gcd(int i, int j) {
		return j == 0 ? i : gcd(j, i % j);
	}
}
