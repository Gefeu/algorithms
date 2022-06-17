package lanqiao._2018._7_三体攻击_差分数组;

import java.util.Scanner;

public class Main {
	static int A, B, C;
	static int[][][] targets;

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
//		Scanner scan = new Scanner(new FileInputStream("./src/s2018/_7_three_body_attack/input1.txt"));
		// 数据输入
		A = scan.nextInt();
		B = scan.nextInt();
		C = scan.nextInt();
		int m = scan.nextInt();
		targets = new int[A + 2][B + 2][C + 2];

		for (int i = 1; i <= A; i++) {
			for (int j = 1; j <= B; j++) {
				for (int k = 1; k <= C; k++) {
					targets[i][j][k] = scan.nextInt();
				}
			}
		}

		int[][] attacks = new int[m + 1][7 + 1];
		for (int attackIdx = 1; attackIdx <= m; attackIdx++) {
			for (int i = 1; i <= 7; i++) {
				attacks[attackIdx][i] = scan.nextInt();
			}
		}

		// 二分搜索差分数组
		initDiffArray();
		int l = 1;
		int r = m;
		int pre = 0;
		int ans = -1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (mid >= pre) { // 攻击
				for (int i = pre + 1; i <= mid; i++) {
					int[] attack = attacks[i];
					update(attack[1], attack[2], attack[3], attack[4], attack[5], attack[6], -attack[7]);
				}
			} else { // 恢复
				for (int i = pre; i > mid; i--) {
					int[] attack = attacks[i];
					update(attack[1], attack[2], attack[3], attack[4], attack[5], attack[6], attack[7]);
				}
			}

			boolean over = isOver();
			if (over) {
				ans = mid;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
			pre = mid;
		}
		System.out.println(ans);
		scan.close();
	}

	private static void initDiffArray() {
		// 相减从右往左，相加从左往右
		for (int i = A; i >= 1; i--) { // TODO:压缩成一个循环能优化性能？
			for (int j = B; j >= 1; j--) {
				for (int k = C; k >= 1; k--) {
					targets[i][j][k] = targets[i][j][k] - targets[i - 1][j][k];
				}
			}
		}
		for (int i = A; i >= 1; i--) {
			for (int j = B; j >= 1; j--) {
				for (int k = C; k >= 1; k--) {
					targets[i][j][k] = targets[i][j][k] - targets[i][j - 1][k];
				}
			}
		}
		for (int i = A; i >= 1; i--) {
			for (int j = B; j >= 1; j--) {
				for (int k = C; k >= 1; k--) {
					targets[i][j][k] = targets[i][j][k] - targets[i][j][k - 1];
				}
			}
		}
	}

	private static void update(int i1, int i2, int j1, int j2, int k1, int k2, int value) {
		targets[i1][j1][k1] += value;
		targets[i1][j2 + 1][k2 + 1] += value;
		targets[i2 + 1][j2 + 1][k1] += value;
		targets[i2 + 1][j1][k2 + 1] += value;

		targets[i2 + 1][j1][k1] -= value;
		targets[i1][j2 + 1][k1] -= value;
		targets[i1][j1][k2 + 1] -= value;
		targets[i2 + 1][j2 + 1][k2 + 1] -= value;
	}

	private static boolean isOver() {
		long[][][] array = new long[A + 2][B + 2][C + 2];
		for (int i = 1; i <= A; i++) {
			for (int j = 1; j <= B; j++) {
				for (int k = 1; k <= C; k++) {
					array[i][j][k] = targets[i][j][k - 1] + targets[i][j][k];
				}
			}
		}
		for (int i = 1; i <= A; i++) {
			for (int j = 1; j <= B; j++) {
				for (int k = 1; k <= C; k++) {
					array[i][j][k] = array[i][j - 1][k] + array[i][j][k];
				}
			}
		}
		for (int i = 1; i <= A; i++) {
			for (int j = 1; j <= B; j++) {
				for (int k = 1; k <= C; k++) {
					array[i][j][k] = array[i - 1][j][k] + array[i][j][k];
					if (array[i][j][k] < 0) {
						return true;
					}
				}
			}
		}
		return false;
	}
}