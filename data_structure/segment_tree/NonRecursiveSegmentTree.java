package data_structure.segment_tree;

import java.util.Arrays;

/**
 * 非递归的线段树
 * 区间查询 O(log(n))
 * 点更新 O(log(n))
 * 不适用区间更新
 */
public class NonRecursiveSegmentTree {
	public static void main(String[] args) {
		for (int n = 1; n <= 16; n++) {
			int[] tree = new int[n * 2];
			for (int i = 0; i < n; i++) {
				tree[n + i] = 1;
			}
			for (int i = n - 1; i >= 1; i--) {
				tree[i] = tree[i << 1] + tree[i << 1 | 1];
			}

			// 测试正确性
			System.out.println(Arrays.toString(tree));
			System.out.printf("[%d, %d]: %d\n", 0, n / 2,
					query(tree, 0, n / 2));
			set(tree, 0, 3);
			System.out.printf("修改第0个元素为3后，[%d, %d]: %d\n", 0, n / 2,
					query(tree, 0, n / 2));
			System.out.println();
		}
	}

	static int query(int[] tree, int l, int r) {
		int n = tree.length / 2;
		int res = 0;
		for (l += n, r += n; l <= r; l >>= 1, r >>= 1) {
			if (l % 2 == 1)
				res += tree[l++];
			if (r % 2 == 0)
				res += tree[r--];
		}
		return res;
	}

	// 更新date[i]=v
	static void set(int[] tree, int i, int v) {
		int n = tree.length / 2;
		tree[n + i] = v;
		for (i = n + i >> 1; i >= 1; i >>= 1) {
			tree[i] = tree[i << 1] + tree[i << 1 | 1];
		}
	}
}