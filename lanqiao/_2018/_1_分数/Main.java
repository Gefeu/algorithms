package lanqiao._2018._1_分数;

import java.util.Scanner;
import java.lang.Math;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print((int)Math.pow(2, 20)-1);
		System.out.print('/');
		System.out.print((int)Math.pow(2, 19));
		scan.close();
	}
}
