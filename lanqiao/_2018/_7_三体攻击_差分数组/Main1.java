package lanqiao._2018._7_三体攻击_差分数组;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main1 {
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner scan = new Scanner(System.in);
		Scanner scan = new Scanner(new FileInputStream("./src/s2018/_7_three_body_attack/input1.txt"));

		// 在此输入您的代码...
		int A = scan.nextInt();
		int B = scan.nextInt();
		int C = scan.nextInt();
		int m = scan.nextInt();
		long[][][] targets = new long[A + 1][B + 1][C + 1];
		for (int index = 0; index < A * B * C; index++) {
			long num = scan.nextLong();
			// 也可通过三层for循环，最外层的增加得最慢
			int i = index / C / B + 1; 
			int j = index / C % B + 1;
			int k = index % C + 1;
			targets[i][j][k] = num;
		}
		for (int index = 1; index <= m; index++) {
			int lat = scan.nextInt();
			int rat = scan.nextInt();
			int lbt = scan.nextInt();
			int rbt = scan.nextInt();
			int lct = scan.nextInt();
			int rct = scan.nextInt();
			long ht = scan.nextLong();
			for (int i = lat; i <= rat; i++) {
				for (int j = lbt; j <= rbt; j++) {
					for (int k = lct; k <= rct; k++) {
						// 少一次判断，可减少很多运行时间
//						if (1 <= i && i <= A && 1 <= j && j <= B && 1 <= k && k <= C) {
							targets[i][j][k] -= ht;
							if (targets[i][j][k] < 0) {
								System.out.println(index);
								return;
							}
//						}
					}
				}
			}
		}
		scan.close();
	}
}
