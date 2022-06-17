package lanqiao._2021._4_路径_最短路径;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Dijkstra {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// Dijkstra算法（暴力）
		final int INF = 0x3f3f3f3f;

		long begin = System.currentTimeMillis();
		HashMap<Integer, Integer>[] adj = new HashMap[2021 + 1];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new HashMap();
		}

		for (int i = 1; i <= 2021; i++) {
			for (int j = i + 1; j <= 2021; j++) {
				if (i != j && abs(j - i) <= 21) {
					int n = lcm(i, j);
					adj[i].put(j, n);
					adj[j].put(i, n);
				}
			}
		}

		int[] dis = new int[2021 + 1];
		boolean[] visited = new boolean[2021 + 1];
		Arrays.fill(dis, INF);
		dis[1] = 0;
		visited[1] = true; // 到1的最短路径已经找到

		while (true) {
			int idx = -1;
			int minDis = INF;
			// 可以改用优先队列
			for (int i = 1; i <= 2021; i++) { // 第一步找到的是1节点
				if (!visited[i] && dis[i] < minDis) {
					idx = i;
					minDis = dis[i];
				}
			}
			if (idx == -1) { // 所有已经visited，，或者剩下的都不能到达
				break;
			}

			visited[idx] = true;
			HashMap<Integer, Integer> map = adj[idx];
			for (int k : map.keySet()) {
				if (!visited[k]) { // 以新找到的节点为中间节点更新剩余节点距离
					dis[k] = min(dis[k], dis[idx] + map.get(k));
				}
			}
		}

		System.out.println((System.currentTimeMillis() - begin) / 1000.0);
		System.out.println(dis[2021]);
		// 0.05
		// 10266837
		scan.close();

	}

	private static int lcm(int i, int j) {
		return i * j / gcd(i, j);
	}

	private static int gcd(int i, int j) {
		return j == 0 ? i : gcd(j, i % j);
	}
}
