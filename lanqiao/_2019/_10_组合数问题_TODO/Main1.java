package lanqiao._2019._10_组合数问题_TODO;

import java.math.BigInteger;
import java.util.Scanner;

public class Main1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		BigInteger k = scan.nextBigInteger();
		BigInteger _0 = BigInteger.ZERO;
		BigInteger _1 = BigInteger.ONE;
		BigInteger _2 = BigInteger.valueOf(2);
		BigInteger resModulus = BigInteger.valueOf(1000_000_000 + 7);
		for (int i = 0; i < t; i++) {
			BigInteger n = scan.nextBigInteger();
			BigInteger m = scan.nextBigInteger();
			BigInteger a = n.divide(k);
			BigInteger b = m.divide(k);
			BigInteger r = n.mod(k);
			BigInteger res = _0;
			if (a.equals(_0)) {
				System.out.println(0);
				continue;
			}
			if (b.equals(_0)) {
				BigInteger p = m.subtract(_1).multiply(m).divide(_2);
				BigInteger h = a.subtract(_1).multiply(p);

				BigInteger u = m.subtract(_1).min(r).min(k.subtract(_2));
				BigInteger g = m.subtract(_1).multiply(u.add(_1)).subtract(u.multiply(u.add(_1)).divide(_2));

				res = h.add(g);
				System.out.println(res.mod(resModulus));
			} else if (b.compareTo(a) > 0) {
				BigInteger h = a.multiply(a.subtract(_1)).multiply(k).multiply(k.subtract(_1))
						.divide(BigInteger.valueOf(4));

				BigInteger u = r.min(k.subtract(_2));
				BigInteger g = u.add(_1).multiply(k.subtract(_1)).multiply(a)
						.subtract(u.multiply(u.add(_1)).multiply(a).divide(_2));

				res = h.add(g);
				System.out.println(res.mod(resModulus));
			} else {
				BigInteger h = b.multiply(b.subtract(_1)).multiply(k).multiply(k.subtract(_1))
						.divide(BigInteger.valueOf(4));

				BigInteger g = a.subtract(b).multiply(b).multiply(k).multiply(k.subtract(_1)).divide(_2);

				BigInteger u = r.min(k.subtract(_2));
				BigInteger f = u.add(_1).multiply(k.subtract(_1)).multiply(b)
						.subtract(u.multiply(u.add(_1)).multiply(b).divide(_2));

				res = h.add(g).add(f);
				System.out.println(res.mod(resModulus));
			}
		}
		scan.close();
	}
}
