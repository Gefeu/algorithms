
package lanqiao._2020._5_平面分割_两圆交点;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 先考虑只有直线，再不断加圆
		int n = 20;
		int a = 1 + (n + 1) * n / 2; // 直线和直线相交
		int b = n * (n - 1); // 圆和圆相交，一个顶点增加一个部分
		int c = 2 * n * n; // 圆和直线相交，一个顶点增加一个部分
		System.out.println(a + b + c);
		scan.close();
	}
}
