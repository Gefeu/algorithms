package lanqiao._2019._6_完全二叉树的权值;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int[] weights = new int[n];
		long[] sums = new long[n + 1];
		for (int i = 0; i < n; i++) {
			weights[i] = scan.nextInt();
		}

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 1, weights[0]));

		while (!q.isEmpty()) {
			Node node = q.remove();
			int index = node.index;
			int depth = node.depth;
			int weight = node.weight;

			sums[depth] += weight;

			int leftIdx = 2 * index + 1;
			int rightIdx = 2 * index + 2;
			if (0 <= leftIdx && leftIdx < n) {
				q.add(new Node(leftIdx, depth + 1, weights[leftIdx]));
			}
			if (0 <= rightIdx && rightIdx < n) {
				q.add(new Node(rightIdx, depth + 1, weights[rightIdx]));
			}
		}
		long maxSum = Long.MIN_VALUE;
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			if (sums[i] > maxSum) {
				ans = i;
				maxSum = sums[i];
			}
		}

		System.out.println(ans);
		scan.close();
	}

}

class Node {
	int index;
	int depth;
	int weight;

	public Node(int index, int depth, int weight) {
		super();
		this.index = index;
		this.depth = depth;
		this.weight = weight;
	}

}
