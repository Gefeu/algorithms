package data_structure.graph.generation;

import java.util.HashMap;

public class WeightedGraph {
	public int n;
	public int m;
	public HashMap<Integer, Integer>[] adj;
	public WeightedGraph(int n, int m, HashMap<Integer, Integer>[] adj) {
		super();
		this.n = n;
		this.m = m;
		this.adj = adj;
	}

}