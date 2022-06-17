package data_structure;

import static java.lang.Math.log;
import static java.lang.Math.min;

/**
 * 解决RangeMinimumQuery的静态数据查询问题
 * 区间最小值查询O(1)
 */
public class SparseTable {
	private int[][] table;

	public SparseTable(int[] data) {
		int n = data.length;
		int m = (int) (log(n) / log(2));
		table = new int[n][m + 1]; // 0次幂到m次幂

		// 初始每个长度为1的区间的最小值
		for (int i = 0; i < n; i++) {
			table[i][0] = data[i];
		}

		// 长度2^p 利用 长度2^(p-1) 的最小值
		for (int p = 1; p <= m; p++) {
			int len = 1 << p; // 计算长度为len的区间的最小值
			for (int i = 0; i + len - 1 < n; i++) { // 每个区间的的右端点都不越界
				table[i][p] = min(table[i][p - 1], table[i + len / 2][p - 1]);
			}
		}
	}

	// 查询[l,r]的最小值
	public int query(int l, int r) {
		int p = (int) (log(r - l + 1) / log(2));
		int len = 1 << p;
		return min(table[l][p], table[r - len + 1][p]);
	}

	public static void main(String[] args) {
		int[] a = {3, 2, 1, 4, 5, 6};
		SparseTable st = new SparseTable(a);
		int r = st.query(0, 1);
		System.out.println(r); // 2
		r = st.query(1, 4);
		System.out.println(r); // 1
	}
}
