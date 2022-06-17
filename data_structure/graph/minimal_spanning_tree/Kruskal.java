package data_structure.graph.minimal_spanning_tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import data_structure.graph.generation.GraphGenerator;
import data_structure.graph.generation.WeightedGraph;

public class Kruskal {
	class Edge implements Comparable<Edge> {
		int u, v;
		int w;

		public Edge(int u, int v, int w) {
			super();
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}

		@Override
		public String toString() {
			return "Edge [u=" + u + ", v=" + v + ", w=" + w + "]";
		}

	}
	int[] parent;
	public Kruskal() {
		WeightedGraph graph = GraphGenerator.getUndirectedWeightedGrapth();
		int n = graph.n;
		HashMap<Integer, Integer>[] adj = graph.adj;

		parent = new int[n + 1];
		LinkedList<Edge> mst = new LinkedList<>();
		LinkedList<Edge> edges = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			HashMap<Integer, Integer> map = adj[i];
			for (Integer to : map.keySet()) {
				int w = map.get(to);
				edges.add(new Edge(i, to, w));
			}
		}
		Collections.sort(edges);

		while (!edges.isEmpty()) {
			Edge edge = edges.removeFirst();
			int u = edge.u;
			int v = edge.v;
			int uPar = findParent(u);
			int vPar = findParent(v);
			if (uPar != vPar) {
				mst.add(edge);
				parent[uPar] = vPar;
			}
		}
		System.out.println(mst);
	}

	private int findParent(int v) {
		if (parent[v] == 0)
			return v;
		return parent[v] = findParent(parent[v]);
	}

	public static void main(String[] args) {
		new Kruskal();
	}
}
