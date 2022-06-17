package lanqiao._2020._4_七段码;

import java.util.Scanner;

public class Main {
	static int ans = 0;
	static int[][] adj = { { 5, 1 }, { 0, 6, 2 }, { 1, 6, 3 }, { 2, 4 }, { 3, 6, 5 }, { 0, 6, 4 }, { 1, 2, 4, 5 }, };

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[] arr = new int[7];
		f(0, arr);
		System.out.println(ans);
		scan.close();
	}

	private static void f(int i, int[] arr) {
		if (i == 7) {
			if (isConnected(arr)) {
				ans++;
			}
			return;
		}
		arr[i] = 0;
		f(i + 1, arr);
		arr[i] = 1;
		f(i + 1, arr);
	}

	private static boolean isConnected(int[] arr) {
		int c = 0;
		boolean[] visited = new boolean[7];
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i] && arr[i] == 1) {
				c++;
				dfs(i, arr, visited);
			}
		}
		return c == 1;
	}

	private static void dfs(int i, int[] arr, boolean[] visited) {
		visited[i] = true;
		for (int j : adj[i]) {
			if (!visited[j] && arr[j] == 1) {
				dfs(j, arr, visited);
			}
		}
	}
}
