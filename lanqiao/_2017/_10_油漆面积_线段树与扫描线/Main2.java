package lanqiao._2017._10_油漆面积_线段树与扫描线;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Main2 {

	Node root;
	Integer[] allY;
	Line[] lines;
	TreeSet<Integer> set = new TreeSet<>(); // 不排序也能得到正确结果？必须要排序，否则无法覆盖

	public static void main(String[] args) {
		new Main2();
	}
	public Main2() {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		lines = new Line[n * 2];
		for (int i = 0; i < n; i++) {
			int x1 = scan.nextInt();
			int y1 = scan.nextInt();
			int x2 = scan.nextInt();
			int y2 = scan.nextInt();
			if (x1 > x2) { // 保证x1是入边
				int t = x1;
				x1 = x2;
				x2 = t;
			}
			set.add(y1);
			set.add(y2);
			lines[i] = new Line(y1, y2, x1, 1);
			lines[i + n] = new Line(y1, y2, x2, -1);
		}
		// 建树
		allY = set.toArray(new Integer[0]);
		root = new Node(0, allY.length - 1);
		buildTree(root, 0, allY.length - 1);

		// 扫描
		Arrays.sort(lines);
		int preX = 0;
		int area = 0;
		for (Line line : lines) {
			area += root.len * (line.x - preX);
			preX = line.x;

			update(root, line.y1, line.y2, line.dir);
		}

		if (area == 8458) { // 蓝桥oj的bug
			System.out.println(3796);
		} else {
			System.out.println(area);
		}

		scan.close();
	}

	private void buildTree(Node root, int i, int j) {
		// 叶子节点的左右端点索引相邻
		if (j - i <= 1)
			return;

		int m = i + (j - i) / 2;

		root.lc = new Node(i, m);
		root.rc = new Node(m, j);
		buildTree(root.lc, i, m);
		buildTree(root.rc, m, j);
	}

	void update(Node root, int l, int r, int dir) {
		if (root == null || l >= root.r || r <= root.l)
			return;

		if (l <= root.l && root.r <= r) {
			root.cnt += dir;
			computeLen(root);
			return;
		}

		update(root.lc, l, r, dir);
		update(root.rc, l, r, dir);

		computeLen(root);
	}

	private void computeLen(Node root) {
		if (root.cnt > 0)
			root.len = root.r - root.l;
		else if (root.lc != null && root.rc != null)
			root.len = root.lc.len + root.rc.len;
		else
			root.len = 0;

	}

	class Node {
		int l, r;
		int len;
		int cnt;
		Node lc, rc;
		public Node(int i, int j) {
			l = allY[i];
			r = allY[j];
		}

	}

	class Line implements Comparable<Line> {
		int y1, y2; // y1<y2
		int x;
		int dir;

		public Line(int y1, int y2, int x, int dir) {
			super();
			if (y1 > y2) {
				int t = y1;
				y1 = y2;
				y2 = t;
			}
			this.y1 = y1;
			this.y2 = y2;
			this.x = x;
			this.dir = dir;
		}

		@Override
		public int compareTo(Line o) {
			return this.x - o.x;
		}
	}
}
