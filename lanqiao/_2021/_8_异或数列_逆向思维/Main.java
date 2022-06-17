package lanqiao._2021._8_异或数列_逆向思维;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		LinkedList<BigInteger> list = new LinkedList<>();
		for (int i = 0; i < T; i++) {
			int n = scan.nextInt();
			BigInteger r = BigInteger.ZERO;
			list.clear();

			for (int j = 0; j < n; j++) {
				BigInteger number = scan.nextBigInteger();
				r = r.xor(number);
				list.add(number);
			}

			if (r.equals(BigInteger.ZERO)) {
				System.out.println(0);
				continue;
			}

			int len = r.bitLength();
			int zeroCnt = 0;
			int oneCnt = 0;
			for (BigInteger number : list) {
				if (!number.testBit(len - 1)) {
					zeroCnt++;
				} else {
					oneCnt++;
				}
			}

			if (oneCnt == 1) {
				System.out.println(1);
				continue;
			}

			if (zeroCnt % 2 == 0) {
				System.out.println(1);
			} else {
				System.out.println(-1);
			}
		}
		scan.close();
	}
}
