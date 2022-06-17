package lanqiao._2021._9_双向排序_线段树;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.Scanner;

public class Main1 {
	class Node {
		int l, r;
		int dSum; // 降序元素个数
		int lazy;
		Node lchild, rchild;

		public Node(int l, int r) {
			super();
			this.l = l;
			this.r = r;
		}

		public int getAvailDes() {
			return r - l + 1 - dSum - lazy;
		}

		public int getDes() {
			return dSum + lazy;
		}
	}

	Node root;

	public static void main(String[] args) {
		new Main1();
	}

	public Main1() {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();

		// 建树
		root = new Node(1, n);
		buildTree(root);

		for (int i = 0; i < m; i++) {
			int op = scan.nextInt();
			int pos = scan.nextInt();
			if (op == 0) { // 添加降序元素
				int cnt = max(0, pos - root.dSum);
				addDescent(root, cnt);
			} else { // 添加升序元素
				int cnt = (n - pos + 1) - (n - root.dSum);
				cnt = max(0, cnt);
				addAscent(root, cnt);
			}
		}

		boolean[] descent = new boolean[n + 1];
		for (int i = n; i >= 1; i--) {
			descent[i] = query(root, i) == 1;
			if (descent[i])
				System.out.print(i + " ");
		}

		for (int i = 1; i <= n; i++) {
			if (!descent[i])
				System.out.print(i + " ");
		}

		scan.close();
	}
	private int query(Node root, int i) {
		clearLazy(root);

		if (root.l == root.r)
			return root.dSum;

		int m = root.l + (root.r - root.l) / 2;
		if (i <= m)
			return query(root.lchild, i);
		else
			return query(root.rchild, i);
	}

	private void addAscent(Node root, int cnt) {
		if (cnt == 0)
			return;

		// 消除当前的lazy值
		clearLazy(root);

		// 消除descent
		if (root.dSum == cnt) {
			root.dSum = 0;
			setLazy(root, -cnt);
			return;
		}

		root.dSum -= cnt;
		int dCnt = root.lchild.getDes();
		addAscent(root.lchild, min(cnt, dCnt));
		addAscent(root.rchild, max(cnt - dCnt, 0));
	}

	private void addDescent(Node root, int cnt) {
		if (cnt == 0)
			return;

		// 消除当前的lazy值
		clearLazy(root);

		if (root.getAvailDes() == cnt) {
			root.dSum = root.r - root.l + 1;
			setLazy(root, cnt);
			return;
		}

		// 自顶向下更新
		root.dSum += cnt;
		int availDes = root.lchild.getAvailDes();
		addDescent(root.lchild, min(cnt, availDes));
		addDescent(root.rchild, max(cnt - availDes, 0));
	}

	private void clearLazy(Node root) {
		if (root.lazy == 0)
			return;

		root.dSum += root.lazy;
		setLazy(root, root.lazy);
		root.lazy = 0;
	}

	// 设置左右孩子的lazy标记
	private void setLazy(Node root, int cnt) {
		if (cnt == 0 || root.l >= root.r)
			return;

		if (cnt > 0) {
			int availDes = root.lchild.getAvailDes();
			root.lchild.lazy += min(cnt, availDes);
			root.rchild.lazy += max(cnt - availDes, 0);
		} else {
			cnt = -cnt;
			int dCnt = root.lchild.getDes();
			root.lchild.lazy -= min(cnt, dCnt);
			root.rchild.lazy -= max(cnt - dCnt, 0);
		}
	}

	private void buildTree(Node root) {
		int l = root.l, r = root.r;
		if (l == r)
			return;

		int m = l + (r - l) / 2;

		root.lchild = new Node(l, m);
		root.rchild = new Node(m + 1, r);
		buildTree(root.lchild);
		buildTree(root.rchild);
	}

}
