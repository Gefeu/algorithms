package lanqiao._2018._8_全球变暖;

import java.util.Scanner;

public class Main {
	static char[][] pic;

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
//		Scanner scan = new Scanner(new FileInputStream("./src/s2018/_8_global_warming/input1.txt"));
		// 在此输入您的代码...
		int n = scan.nextInt();
		char[][] pre = new char[n][n];
		pic = new char[n][n];

		scan.nextLine();
		for (int i = 0; i < n; i++) {
			pre[i] = scan.nextLine().toCharArray();
		}

		// 淹没像素
		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < n - 1; j++) {
				if (pre[i][j] == '#' && pre[i - 1][j] == '#' && pre[i + 1][j] == '#' && pre[i][j - 1] == '#'
						&& pre[i][j + 1] == '#') {
					pic[i][j] = pre[i][j];
				}
			}
		}
		int ans = 0;

		for (int i = 2; i < n - 2; i++) {
			for (int j = 2; j < n - 2; j++) {
				if (pic[i][j] == '#') {
					dfs(i, j);
					ans++;
				}
			}
		}

		System.out.println(ans);
		scan.close();
	}

	private static void dfs(int i, int j) {
		pic[i][j] = '$';
		if (pic[i - 1][j] == '#') {
			dfs(i - 1, j);
		}
		if (pic[i + 1][j] == '#') {
			dfs(i + 1, j);
		}
		if (pic[i][j - 1] == '#') {
			dfs(i, j - 1);
		}
		if (pic[i][j + 1] == '#') {
			dfs(i, j + 1);
		}
	}
}
