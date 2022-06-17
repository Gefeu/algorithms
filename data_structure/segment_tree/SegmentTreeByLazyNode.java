package data_structure.segment_tree;

import static java.lang.Math.*;

/**
 * 线段树，快速更新和查询区间信息
 * 区间查询 O(log(n))
 * 区间更新 O(log(n))
 */

public class SegmentTreeByLazyNode {
	class Node {
		int l, r;
		int v;
		int lazy; // 自顶向下的操作才适用
		Node lchild, rchild;

		public Node(int l, int r) {
			super();
			this.l = l;
			this.r = r;
		}

	}
	
	Node root;

	int[] data;

	public SegmentTreeByLazyNode(int[] data) {
		this.data = data;
		root = new Node(0, data.length - 1);
		buildTree(root);
	}

	private int buildTree(Node root) {
		int l = root.l, r = root.r;
		if (l == r) {
			root.v = data[l];
			return root.v;
		}

		int m = l + (r - l) / 2;
		root.lchild = new Node(l, m);
		root.rchild = new Node(m + 1, r);

		root.v = buildTree(root.lchild) + buildTree(root.rchild);
		return root.v;
	}


	private int query(Node root, int l, int r) {
		if (root == null || l > root.r || r < root.l)
			return 0;

		clearLazy(root);

		if (l <= root.l && root.r <= r)
			return root.v;

		// if语句尽量写在递归终止时
		return query(root.lchild, l, r) + query(root.rchild, l, r);
	}

	
	private void addRange(Node root, int l, int r, int diff) {
		if (root == null || l > root.r || r < root.l)
			return;

		clearLazy(root);

		if (l <= root.l && root.r <= r) {
			root.v += diff * (root.r - root.l + 1);
			setLazy(root, diff);
			return;
		}

		// 自顶向下更新
		root.v += diff * (min(r, root.r) - max(l, root.l));
		addRange(root.lchild, l, r, diff);
		addRange(root.rchild, l, r, diff);
	}

	private void clearLazy(Node root) {
		if (root.lazy == 0)
			return;

		root.v += root.lazy * (root.r - root.l + 1);
		setLazy(root, root.lazy);
		root.lazy = 0;

	}

	void setLazy(Node root, int diff) {
		if (root.l >= root.r || diff == 0)
			return;
		root.lchild.lazy += diff;
		root.rchild.lazy += diff;
	}

	// 更新data[i]=v
	public void set(int i, int v) {
		// 自底向上更新
	}

	// 更新data[l..r]=v
	public void setRange(int l, int r, int v) {
		// 如果add和set操作都存在
		// lazySet操作覆盖之前的所有lazy操作
		// lazyAdd必须在lazySet完成后
	}
}
