package lanqiao._2017._4_方格分割_方格的dfs;

import java.util.Scanner;

public class Main {
	static int[][] visited = new int[7][7];
	static int ans = 0;
	static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1},};

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		scan.close();
		dfs(3, 3);
		System.out.println(ans / 4);
	}

	private static void dfs(int i, int j) {
		if (i == 0 || i == 6 || j == 0 || j == 6) {
			ans++;
			return;
		}
		visited[i][j] = 1;
		visited[6 - i][6 - j] = 1; // 不能和对称位置交叉
		for (int[] dir : dirs) {
			int ii = i + dir[0];
			int jj = j + dir[1];
			if (ii < 0 || ii > 6 || jj < 0 || jj > 6) {
				continue;
			}
			if (visited[ii][jj] == 0) {
				dfs(ii, jj);
			}
		}
		visited[i][j] = 0;
		visited[6 - i][6 - j] = 0;
	}

}
