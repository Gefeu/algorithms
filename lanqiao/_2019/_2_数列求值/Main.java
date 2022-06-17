package lanqiao._2019._2_数列求值;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a = 1;
		int b = 1;
		int c = 1;
		int i = 4;
		int t = 0;
		int q = 10000;
		while (i <= 20190324) {
			t = c;
			c = ((a % q) + (b % q) + (c % q)) % q;
			a = b;
			b = t;
			i++;
		}
		System.out.println(c);
		scan.close();
	}
}
