package lanqiao._2017._9_分巧克力_二分搜索;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int n, k;
		int[] h = new int[100000];
		int[] w = new int[100000];
		Scanner scan = new Scanner(System.in);
		// 在此输入您的代码...
		n = scan.nextInt();
		k = scan.nextInt();
		for (int i = 0; i < n; ++i) {
			h[i] = scan.nextInt();
			w[i] = scan.nextInt();
		}

		int l = 1, r = 100000, ans = 0;
		while (l <= r) {
			int mid = (l + r) / 2;
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				cnt += (w[i] / mid) * (h[i] / mid);
			}
			if (cnt >= k) {
				ans = mid; // 保存目前的最优解，无法判断是否是最终的解
				l = mid + 1;
			} else {
				r = mid - 1;
			}

		}
		System.out.println(ans);

		scan.close();
	}
}
