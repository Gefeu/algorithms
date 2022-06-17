package data_structure.graph.generation;

import java.util.HashSet;

public class UnweightedGraph {
	public int n;
	public int m;
	public HashSet<Integer>[] adj;
	public UnweightedGraph(int n, int m, HashSet<Integer>[] adj) {
		super();
		this.n = n;
		this.m = m;
		this.adj = adj;
	}

}