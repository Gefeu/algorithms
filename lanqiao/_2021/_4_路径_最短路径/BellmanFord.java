package lanqiao._2021._4_路径_最短路径;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class BellmanFord {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// Bellman-Ford算法
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
		Arrays.fill(dis, INF);
		dis[1] = 0;

		// 每次增加一条最短路径，在有解情况下最多迭代nodeLen-1次
		for (int i = 0; i < 2021 - 1; i++) {
			for (int j = 2; j <= 2021; j++) {
				// 用邻接矩阵遍历边的复杂度是O(nodeLen*2)，就和Floyd一样了
				// 故用邻接表的方式
				HashMap<Integer, Integer> map = adj[j];
				for (int k : map.keySet()) {
					dis[j] = min(dis[j], dis[k] + map.get(k));
				}
			}
		}

		System.out.println((System.currentTimeMillis() - begin) / 1000.0);
		System.out.println(dis[2021]);
		// 3.714
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
