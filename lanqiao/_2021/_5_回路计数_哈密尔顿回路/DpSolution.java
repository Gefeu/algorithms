package lanqiao._2021._5_回路计数_哈密尔顿回路;

import java.util.HashSet;

public class DpSolution {
	public static void main(String[] args) {
		// 状压dp方法求解
		HashSet<Integer>[] adj = new HashSet[21 + 1];
		for (int i = 0; i < adj.length; i++)
			adj[i] = new HashSet<>();

		for (int i = 1; i <= 21; i++)
			for (int j = i + 1; j <= 21; j++)
				if (gcd(i, j) == 1) {
					adj[i].add(j);
					adj[j].add(i);
				}

		long[][] dp = new long[1 << 22][21 + 1];
		dp[0b10][1] = 1; // 初始状态，变化到当前状态一共有1种路径
		int endStatus = (1 << 21) - 1 << 1;

		for (int status = 0b10; status <= endStatus; status += 2) // 第0位没有使用
			for (int j = 1; j <= 21; j++) {
				if ((status >> j & 1) == 0)
					continue;

				for (Integer k : adj[j])
					if ((status >> k & 1) == 0)
						dp[status | 1 << k][k] += dp[status][j]; // 更新之后的状态
			}

		long ans = 0;
		for (long n : dp[endStatus]) {
			ans += n;
		}
		System.out.println(ans);
	}

	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}