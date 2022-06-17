package lanqiao._2019._8_修改数组_线段的并查集;

import java.util.Scanner;

public class Main {
	static int[] parent = new int[1000_000 + 5];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		for (int i = 0; i < N; i++) {
			int n = scan.nextInt();
			int p = findParent(n);
			System.out.print(p + " ");
			// 更新新增数的父亲节点为范围最大数+1
			parent[p] = findParent(p + 1);
		}

		scan.close();
	}

	private static int findParent(int n) {
		// 并查集每个等价类的根节点是所在范围的最大数+1
		if (parent[n] == 0) {
			return n;
		}
		return parent[n] = findParent(parent[n]);
	}

}
