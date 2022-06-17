package data_structure.graph.connected_component;

import static java.lang.Math.min;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

import data_structure.graph.generation.GraphGenerator;
import data_structure.graph.generation.UnweightedGraph;

public class Tarjan {
	HashSet<Integer>[] adj;

	int[] num;
	int[] low;
	Stack<Integer> stack = new Stack<>();
	boolean[] inStack;

	int[] cc;
	int ccCnt = 0;

	public Tarjan() {
		Scanner scan = new Scanner(System.in);
		UnweightedGraph g = GraphGenerator.getDirectedUnweightedGrapth();
		int n = g.n;
		adj = g.adj;
		num = new int[n + 1];
		low = new int[n + 1];
		inStack = new boolean[n + 1];

		cc = new int[n + 1];
		for (int i = 1; i <= n; i++)
			if (cc[i] == 0)
				tarjan(i);

		System.out.println(Arrays.toString(cc));
		scan.close();
	}

	private void tarjan(int i) {
		low[i] = num[i] = i;
		stack.push(i);
		inStack[i] = true;
		for (int j : adj[i]) {
			if (inStack[j])
				low[i] = min(low[i], num[j]); // 返回边
			else if (cc[j] == 0) {
				tarjan(j);
				low[i] = min(low[i], low[j]); // 前向边
			}
		}

		if (low[i] == num[i]) {
			int v;
			ccCnt++;
			do {
				v = stack.pop();
				inStack[v] = false;
				cc[v] = ccCnt;
			} while (v != i);
		}
	}

	public static void main(String[] args) {
		new Tarjan();
	}
}
