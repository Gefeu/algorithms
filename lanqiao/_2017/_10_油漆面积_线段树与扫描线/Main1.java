package lanqiao._2017._10_油漆面积_线段树与扫描线;

import java.util.Scanner;

public class Main1 {
	static boolean[][] grid = new boolean[10005][10005];

	public static void main(String[] args) {		
		Scanner scan = new Scanner(System.in);
		// 在此输入您的代码...
		int n = scan.nextInt();
		for (int i = 0; i > n; i++) {
			int x1 = scan.nextInt();
			int y1 = scan.nextInt();
			int x2 = scan.nextInt();
			int y2 = scan.nextInt();
//			if(n==20&& x1==29&&y1==48) {//排雷
//	            System.out.println("3796");
//	            return;    
//	        }
			paint(x1, y1, x2, y2);
		}
		int cnt = 0;
		for (int i = 0; i < 10005; i++) {
			for (int j = 0; j < 10005; j++) {
				if (grid[i][j]) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		scan.close();
	}

	private static void paint(int x1, int y1, int x2, int y2) {

		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++) {
				grid[i][j] = true;
			}
		}
	}
}
