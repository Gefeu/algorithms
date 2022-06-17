package lanqiao._2018._9_倍数问题_余数分类优化迭代;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 在此输入您的代码...
		int n = scan.nextInt();
		int K = scan.nextInt();
		int[][] groups = new int[K][3]; // 只需保存前三大的数，第四大的不用考虑
		for (int i = 0; i < n; i++) {
			int num = scan.nextInt();
			int remainder = num % K;
			for (int j = 0; j < 2; j++) {
				if (num > groups[remainder][j]) {
					groups[remainder][j] = num;
					break;
				}
			}
		}
		// 遍历其中三组
		int maxSum = 0;
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				int k = (2 * K - i - j) % K;
				int[] indices = new int[K];
				int sum = groups[i][indices[i]++] + groups[j][indices[j]++]
						+ groups[k][indices[k]++];
				if (sum > maxSum) {
					maxSum = sum;
				}
			}
		}
		System.out.println(maxSum);
		scan.close();
	}
}
