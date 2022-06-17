package lanqiao._2020._3_蛇形填数;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 在此输入您的代码...
		boolean up = true;
		int n = 1;
		int i = 1;
		int j = 1;
		while (true) {
			if (i == 20 && j == 20) {
				System.out.println(n);
				break;
			}
			if (up) {
				if (i == 1) {
					j++;
					up = false;
				} else {
					j++;
					i--;
				}
			} else {
				if (j == 1) {
					i++;
					up = true;
				} else {
					i++;
					j--;
				}
			}
			n++;
		}
		scan.close();
	}
}
