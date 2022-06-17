package data_structure.segment_tree;

import static java.lang.Math.min;

/**
 * 数组实现的递归线段树，代码复杂，不推荐使用
 *
 */
public class SegmentTreeByArray {
	private int[] nodes;
	private int size;

	private static final int INF = Integer.MAX_VALUE;
	private static int[] tempData;

	/*
	 * 线段树是平衡树，data数组的每个元素都将出现在叶子节点上，故倒数第二层节点个数 <= n
	 * 故节点个数 <= 倒数第一层个数*2 <= 4*n
	 */
	public SegmentTreeByArray(int[] data) {
		tempData = data;
		size = data.length;
		nodes = new int[4 * size + 5];
		buildTree(0, 0, size - 1);
		tempData = null;
	}

	private static int ql; // 查询左端点
	private static int qr; // 查询右端点

	// 查询[l,r]中数的最小值
	public int query(int l, int r) {
		ql = l;
		qr = r;
		return query(0, 0, size - 1);
	}

	private int query(int i, int l, int r) {
		if (ql >= l && r <= qr)
			return nodes[i];

		int m = l + (r - l) / 2;

		int v = INF;
		if (ql <= m)
			v = min(v, query(left(i), l, m));

		if (qr >= m + 1)
			v = min(v, query(right(i), m + 1, r));

		return v;
	}

	private static int updateIndex; // 更新的索引
	private static int newValue; // 更新的值

	// 更新data[i]=v
	public void update(int i, int v) {
		updateIndex = i;
		newValue = v;
		update(0, 0, size - 1);
	}

	private void update(int i, int l, int r) {
		if (l == r)
			nodes[updateIndex] = newValue;

		int m = l + (r - l) / 2;

		if (updateIndex <= m)
			update(left(i), l, m);
		else
			update(right(i), m + 1, r);

		nodes[i] = min(nodes[left(i)], nodes[right(i)]);
	}

	private void buildTree(int i, int l, int r) {
		if (l == r) {
			nodes[i] = tempData[l];
			return;
		}

		int m = l + (r - l) / 2;

		buildTree(left(i), l, m);
		buildTree(right(i), m + 1, r);

		nodes[i] = min(nodes[left(i)], nodes[right(i)]);
	}

	// 返回左孩子的索引
	private int left(int i) {
		return 2 * i;
	}

	// 返回右孩子的索引
	private int right(int i) {
		return 2 * i + 1;
	}
}