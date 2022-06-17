package lanqiao._2019._5_RSA解密_扩展欧几里得_快速乘_快速幂;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		long C = 20190324;
		long d = 212353;
		long n = 1001733993063167141L;
		long[] solution = new long[2];
		long p = 0, q = 0;

		/*
		 * for (long i = 3; i * i <= n; i += 2) { if (n % i != 0) { continue; } p = i; q
		 * = n / i; if (isPrime(p) && isPrime(q)) { break; } } System.out.println(p +
		 * " " + q);
		 */

		p = 891234941;
		q = 1123984201;

		long e = 0;
		// 以下步骤 都能调用BigInteger方法一步解决

		/*
		 * long m = (p - 1) * (q - 1); extgcd(d, m, 1, solution); e = solution[0]; e =
		 * (e % m + m) % m; System.out.println(e);
		 */

		e = 823816093931522017L;

		long res = qpow(C, e, n);
		System.out.println(res);
		
		/*
		 * // 通过方法判断是否是质数 boolean
		 * isp=BigInteger.valueOf(137).isProbablePrime(Integer.MAX_VALUE);
		 * System.out.println(isp);
		 */
		
		scan.close();
	}

	static boolean isPrime(long n) {
		long m = (long) Math.sqrt(n);
		if (n % 2 == 0) {
			return false;
		}
		for (int i = 3; i <= m; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	// 无解抛出异常
	static long extgcd(long a, long b, long c, long[] solution) throws Exception {
		if (b == 0) {
			if (c % a != 0) {
				throw new Exception("无解");
			}
			solution[0] = c / a;
			solution[1] = 0;
			return a;
		}
		long d = extgcd(b, a % b, c, solution);
		long x1 = solution[0];
		long y1 = solution[1];
		solution[0] = y1;
		solution[1] = x1 - a / b * y1;
		return d;
	}

	// 快速幂 (减少乘法次数，且通过快速乘防止溢出)
	static long qpow(long n, long e, long modulus) {
		long res = 1;
		while (e > 0) {
			if ((e & 1) == 1) {
				res = qmul(res, n, modulus);
			}
			n = qmul(n, n, modulus);
			e >>= 1;
		}
		return res;
	}

	// 快速乘 (防止溢出，只要2倍的modulus不溢出)
	static long qmul(long a, long b, long modulus) {
		long res = 0;
		while (b > 0) {
			if ((b & 1) == 1) {
				res = (res + a) % modulus;
			}
			a = (a << 1) % modulus;
			b >>= 1;
		}
		return res;
	}
}
