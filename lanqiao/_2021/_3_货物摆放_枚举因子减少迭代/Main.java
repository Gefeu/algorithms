package lanqiao._2021._3_货物摆放_枚举因子减少迭代;

public class Main {
	public static void main(String[] args) {
		// 因子数个数较少，枚举所有的因子
		long n = 2021041820210418L;

		long[] a = new long[1_000_000];
		int len = 0;
		for (long i = 1; i * i <= n; i++) {
			if (n % i == 0) {
				a[len++] = i;
				if (n / i != i) {
					a[len++] = n / i;
				}
			}
		}
		long ans=0;
		for(int i=0;i<len;i++) {
			for(int j=0;j<len;j++) {
				if(a[i]*a[j]>n) {
					continue;
				}
				for(int k=0;k<len;k++) {
					if(a[i]*a[j]*a[k]==n) {
						ans++;
					}
				}
			}
		}
		System.out.println(ans);
//		System.out.println(2430);
	}
}
