package lanqiao._2019._7_外卖店优先级_系数矩阵压缩优化迭代;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int M = scan.nextInt();
		int T = scan.nextInt();
		int[] states = new int[N + 1];
		// 稀疏矩阵的处理方式（TreeMap数组），减少遍历次数
		// 也可用数组，按（时间，id）排序订单，存储成线性结构 TODO
		TreeMap<Integer, Integer>[] orders = (TreeMap<Integer, Integer>[]) new TreeMap[N + 1];
		for (int i = 0; i < orders.length; i++) {	// 初始化，防止空指针
			orders[i] = new TreeMap<Integer, Integer>();
		}

		for (int i = 1; i <= M; i++) {
			int t = scan.nextInt();
			int id = scan.nextInt();

			Integer num = orders[id].get(t);
			if (num != null) {
				orders[id].put(t, num + 1);
			} else {
				orders[id].put(t, 1);
			}
		}

		boolean[] isPriority = new boolean[N + 1];

		for (int id = 1; id <= N; id++) {
			TreeMap<Integer, Integer> map = orders[id];
			int lastK = 0;
			for (Map.Entry<Integer, Integer> ent : map.entrySet()) {
				int k = ent.getKey();
				int v = ent.getValue();

				if (k - lastK > 1) {
					states[id] -= (k - lastK - 1);
				}
				if (states[id] < 0) {
					states[id] = 0;
				}
				if (states[id] <= 3) {
					isPriority[id] = false;
				}

				if (v > 0) {
					states[id] += 2 * v;
				} else {
					states[id]--;
				}
				if (states[id] < 0) {
					states[id] = 0;
				}
				lastK = k;

				if (states[id] > 5) {
					isPriority[id] = true;
				} else if (states[id] <= 3) {
					isPriority[id] = false;
				}
			}

			if (T > lastK) {
				states[id] -= (T - lastK);
				if (states[id] > 5) {
					isPriority[id] = true;
				} else if (states[id] <= 3) {
					isPriority[id] = false;
				}
			}
		}

		int ans = 0;
		for (int id = 1; id <= N; id++) {
			if (isPriority[id]) {
				ans++;
			}
		}

		System.out.println(ans);
		scan.close();
	}
}
