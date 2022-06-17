package data_structure.graph.minimal_spanning_tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import data_structure.graph.generation.GraphGenerator;
import data_structure.graph.generation.WeightedGraph;

public class Prim {
	class Edge {
		int u, v;

		public Edge(int u, int v) {
			super();
			this.u = u;
			this.v = v;
		}

		@Override
		public String toString() {
			return "Edge [u=" + u + ", v=" + v + "]";
		}

	}
	public static final int INF = 0x3f3f3f3f;
	public Prim() {
		WeightedGraph g = GraphGenerator.getUndirectedWeightedGrapth();
		int n = g.n;
		HashMap<Integer, Integer>[] adj = g.adj;

		boolean[] visited = new boolean[n + 1];
		int[] dis = new int[n + 1];
		int[] closest = new int[n + 1];
		Arrays.fill(dis, INF);
		dis[1] = 0;
		closest[1] = 1;
		LinkedList<Edge> mst = new LinkedList<>();
		
		while (true) {
			int node = -1;
			int minv = INF;
			for (int i = 1; i <= n; i++) {
				if (!visited[i] && dis[i] < minv) {
					node = i;
					minv = dis[i];
				}
			}
			if (node == -1)
				break;
			
			visited[node] = true;
			if (node != 1)
				mst.add(new Edge(closest[node], node));
			HashMap<Integer, Integer> map = adj[node];
			for (Integer to : map.keySet()) {
				int w = map.get(to);
				if (w < dis[to]) {
					dis[to] = w;
					closest[to] = node;
				}
			}
		}
		System.out.println(mst);
	}

	public static void main(String[] args) {
		new Prim();
	}
}
