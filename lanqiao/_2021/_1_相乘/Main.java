package lanqiao._2021._1_相乘;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//在此输入您的代码...
		for(long i=1;i<=1_000_000_007;i++) {
			if(2021*i%1_000_000_007==999999999) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(0);
		scan.close();
	}
}
