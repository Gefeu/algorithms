package lanqiao.test;

import java.math.BigInteger;
import java.time.ZoneId;
import java.util.Scanner;

public class Main {
	public Main() {
		Scanner scan = new Scanner(System.in);
		System.out.println(ZoneId.systemDefault());
		
		scan.close();
	}

	public static void main(String[] args) {
		new Main();
	}
}
