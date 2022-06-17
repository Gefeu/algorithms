package lanqiao._2021._4_路径_最短路径;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class OptimizedDijkstra {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// Dijkstra算法(优先队列)
		final int INF = 0x3f3f3f3f;
		long begin = System.currentTimeMillis();

		// 邻接表
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

		boolean[] visited = new boolean[2021 + 1]; // 是否已经算出最短路径
		int[] dis = new int[2021 + 1]; // 目前到该节点的最短路径
		Arrays.fill(dis, INF);

		PriorityQueue<Node> q = new PriorityQueue<>();
		dis[1] = 0;
		q.add(new Node(1, 0));

		while (!q.isEmpty()) {
			Node node = q.remove();
			if (visited[node.index]) {
				continue;
			}

			visited[node.index] = true;
			HashMap<Integer, Integer> map = adj[node.index];
			for (int k : map.keySet()) {
				if (!visited[k]) {
					int newDis = min(dis[k], node.distance + map.get(k));
					if (newDis < dis[k]) {
						dis[k] = newDis;
						q.add(new Node(k, newDis));
					}
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

class Node implements Comparable<Node> {
	int index;
	int distance;
	public Node(int index, int distance) {
		super();
		this.index = index;
		this.distance = distance;
	}
	@Override
	public int compareTo(Node o) {
		return this.distance - o.distance;
	}
}
