package lanqiao._2020._6_成绩分析;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] a = new int[n];
		int max = 0;
		int min = 100;
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			a[i] = scan.nextInt();
			if (a[i] > max) {
				max = a[i];
			}
			if (a[i] < min) {
				min = a[i];
			}
			sum += a[i];
		}
		System.out.println(max);
		System.out.println(min);
		System.out.printf("%.2f", 1.0 * sum / n);
		scan.close();
	}
}
