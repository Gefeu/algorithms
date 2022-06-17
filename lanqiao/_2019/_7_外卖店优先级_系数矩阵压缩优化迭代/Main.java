package lanqiao._2019._7_外卖店优先级_系数矩阵压缩优化迭代;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	static int[] priorities;
	static boolean[] inQueue;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int M = scan.nextInt();
		int T = scan.nextInt();
		Order[] orders = new Order[M];
		for (int i = 0; i < M; i++) {
			int time = scan.nextInt();
			int id = scan.nextInt();

			orders[i] = new Order(id, time);
		}

		// 先按店铺排序，再按时间排序
		Comparator<Order> comparator = Comparator
				.comparingInt((Order o) -> o.id).thenComparingInt(o -> o.time);
		Arrays.sort(orders, comparator);

		inQueue = new boolean[N + 1];
		priorities = new int[N + 1];

		int i = 0;
		int last = 0;
		while (i < M) {
			int y = 0;
			Order o = orders[i];

			if (o.time - last > 1) {
				priorities[o.id] = Math
						.min(priorities[o.id] - (o.time - last - 1), 0);
				checkPriority(o.id);
			}

			while (i + y < M && orders[i + y].id == o.id
					&& orders[i + y].time == o.time) {
				y++;
			}
			priorities[o.id] += 2 * y;
			checkPriority(o.id);

			if (i + y >= M) {
				break;
			}

			if (orders[i + y].id != o.id) { // 店铺改变，时间从0开始
				last = 0;
				priorities[o.id] -= (T - last);
				checkPriority(o.id);
			} else {
				last = o.time;
			}

			i += y;
		}

		int ans = 0;
		for (boolean in : inQueue) {
			if (in) {
				ans++;
			}
		}
		System.out.println(ans);

		scan.close();
	}

	private static void checkPriority(int id) {
		if (priorities[id] <= 3) {
			inQueue[id] = false;
		} else if (priorities[id] > 5) {
			inQueue[id] = true;
		}

	}
}

class Order {
	int id;
	int time;

	public Order(int id, int time) {
		super();
		this.id = id;
		this.time = time;
	}
}
