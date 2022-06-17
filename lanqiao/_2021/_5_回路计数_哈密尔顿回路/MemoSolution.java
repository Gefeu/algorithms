package lanqiao._2021._5_回路计数_哈密尔顿回路;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MemoSolution {
	static int ans = 0;

	static HashSet<Integer>[] adj;

	static boolean[] visited = new boolean[21 + 1];

	static HashMap<Integer, Long> memo = new HashMap<>();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 邻接表
		adj = new HashSet[21 + 1];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new HashSet<>();
		}
		for (int i = 1; i <= 21; i++) {
			HashSet<Integer> set = adj[i];
			for (int j = i + 1; j <= 21; j++) {
				if (gcd(j, i) == 1) {
					set.add(j);
					adj[j].add(i);
				}
			}
		}

		long cnt = dfs(1, 1);
		System.out.println(cnt);
		// System.out.println(881012367360L);

		scan.close();
	}

	private static long dfs(int nodeIdx, int visitedCnt) {
		// 终点状态
		if (visitedCnt == 21) {
			if (adj[nodeIdx].contains(1)) {
				return 1;
			}
			return 0;
		}

		// 当前状态曾经访问过
		int status = compressStatus(visited, nodeIdx);
		if (memo.containsKey(status)) {
			return memo.get(status);
		}

		// 新的状态
		long cnt = 0;
		visited[nodeIdx] = true;
		for (int j : adj[nodeIdx]) {
			if (!visited[j]) {
				cnt += dfs(j, visitedCnt + 1);
			}
		}
		visited[nodeIdx] = false;

		// 保存新的状态
		memo.put(status, cnt);
		return cnt;
	}

	// 也可以直接用字符串表示
	private static int compressStatus(boolean[] visited, int nodeIdx) {
		int status = nodeIdx;
		for (int i = 1; i <= 21; i++) {
			status <<= 1;
			status |= (visited[i] ? 1 : 0);
		}
		return status;
	}

	private static int gcd(int j, int i) {
		return i == 0 ? j : gcd(i, j % i);
	}
}
