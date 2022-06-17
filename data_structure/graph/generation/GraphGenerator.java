package data_structure.graph.generation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class GraphGenerator {
	public static WeightedGraph getUndirectedWeightedGrapth() {
		Scanner scan = null;
		try {
			scan = new Scanner(
					new FileInputStream("data/undirected-weighted-graph.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int n = scan.nextInt();
		int m = scan.nextInt();
		HashMap<Integer, Integer>[] adj = new HashMap[n + 1];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new HashMap<>();
		}
		for (int i = 1; i <= m; i++) {
			int u = scan.nextInt();
			int v = scan.nextInt();
			int w = scan.nextInt();
			adj[u].put(v, w);
			adj[v].put(u, w);
		}
		return new WeightedGraph(n, m, adj);
	}

	public static UnweightedGraph getDirectedUnweightedGrapth() {
		Scanner scan = null;
		try {
			scan = new Scanner(
					new FileInputStream("data/directed-unweighted-graph.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int n = scan.nextInt();
		int m = scan.nextInt();
		HashSet<Integer>[] adj = new HashSet[n + 1];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new HashSet<>();
		}
		for (int i = 1; i <= m; i++) {
			int u = scan.nextInt();
			int v = scan.nextInt();
			adj[u].add(v);
		}
		return new UnweightedGraph(n, m, adj);
	}

}
