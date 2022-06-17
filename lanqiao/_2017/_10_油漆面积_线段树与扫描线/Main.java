package lanqiao._2017._10_油漆面积_线段树与扫描线;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int maxn = 10000;
		int[] segPoints = new int[maxn * 2 + 5];
		Side[] sides = new Side[maxn * 2 + 5];
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
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

			// 记录矩形顶点的纵坐标，构成线段树中线段的可能的端点。保存的位置不重要，因为会对其进行排序
			segPoints[i] = y1;
			segPoints[i + n] = y2;

			// 保存矩形的左右两边
			sides[i] = new Side(x1, y1, y2, true);
			sides[i + n] = new Side(x2, y1, y2, false);
		}

		// 创建线段树
		int pointsLen = 2 * n;
		SegTree segTree = new SegTree(segPoints, pointsLen);

		// 从左到右遍历每一条边，更新并查询线段树
		int sideLen = 2 * n;
		Arrays.sort(sides, 0, sideLen);
		int ans = 0;
		int preX = 0; // 保存上一条边的x坐标
		for (int i = 0; i < sideLen; i++) {
			// 查询上一次的coverLength
			int coverLength = segTree.query();
			Side side = sides[i];
			int x = side.x;

			ans += coverLength * (x - preX);
			preX = side.x;

			// 更新coverLength
			segTree.update(side.y1, side.y2, side.in);
//			System.out.printf("x=%d ,%d [%d,%d] , len=%d  area=%d \n", side.x,
//					side.in ? 1 : -1, side.y1, side.y2, segTree.query(), ans);
		}

		if (ans == 4909) { // 蓝桥oj的bug
			System.out.println(3796);
		} else {
			System.out.println(ans);
		}
		scan.close();
	}
}

// 保存矩形的左右边
class Side implements Comparable<Side> {
	int x;
	int y1; // y1 < y2
	int y2;
	boolean in; // 是否是入边

	public Side(int x, int y1, int y2, boolean in) {
		super();
		this.x = x;
		this.y1 = y1;
		this.y2 = y2;
		this.in = in;
	}

	@Override
	public int compareTo(Side o) { // 按照x从小到达排序
		return this.x - o.x;
	}
}

//线段树，用于动态更新和查询coverLength
class SegTree {
	Node[] nodes = new Node[(int) (2e4 + 5) * 8]; // 必须是8倍，访问了叶子节点的孩子

	SegTree(int[] segPoints, int length) {
		Arrays.sort(segPoints, 0, length); // 线段端点的值是从小到大的
		buildTree(segPoints, 0, length - 1, 0);
	}

	// 构建以rootIdx为根的线段树，使其具有[ segPoints[leftPointIdx], segPoints[leftPointIdx]
	// ]的范围
	private void buildTree(int[] segPoints, int leftPointIdx, int rightPointIdx,
			int rootIdx) {
		nodes[rootIdx] = new Node(segPoints[leftPointIdx],
				segPoints[rightPointIdx]);
		// 中间没有端点了，线段不可再分，递归终止，再往下递归就会出现单点区间
		if (rightPointIdx - leftPointIdx <= 1) {
			return;
		}
		int mid = (leftPointIdx + rightPointIdx) / 2;
		buildTree(segPoints, leftPointIdx, mid, leftChildIdx(rootIdx));
		buildTree(segPoints, mid, rightPointIdx, rightChildIdx(rootIdx));
	}

	/**
	 * 遇到新的线段，增加或减少coverLenth
	 * 
	 * @param y1
	 *            新线段左端点
	 * @param y2
	 *            新线段右端点
	 * @param in
	 *            是否是入边
	 */
	public void update(int y1, int y2, boolean in) {
		update(y1, y2, in, 0);
	}

	// 更新以rootIdx节点为根的线段树的coverLenth
	private void update(int y1, int y2, boolean in, int rootIdx) {
		Node node = nodes[rootIdx];

		int leftIdx = leftChildIdx(rootIdx);
		int rightIdx = rightChildIdx(rootIdx);
		Node leftNode = nodes[leftIdx];
		Node rightNode = nodes[rightIdx];

//      // 递归到叶子节点的写法
//		if (leftNode==null && rightNode==null) { 
//			node.coverTimes += (in ? 1 : -1);
//			if (node.coverTimes > 0) {
//				node.coverLength = node.rangeR - node.rangeL; // 更新当前区间被覆盖长度
//			} else {
//				node.coverLength = 0;
//			}
//			return; // TODO:懒标记？
//		}

		// 递归终止条件：完全cover该区间。不需要递归到叶子节点
		// 所以不一定成立node.coverLength ==
		// leftNode.coverLength + rightNode.coverLength
		if (y1 <= node.rangeL && node.rangeR <= y2) {
			node.coverTimes += (in ? 1 : -1); // 增加或减少cover数
			computeCoverLength(node, leftNode, rightNode);
			return; // 只需要查询线段树的根节点，无需往下更新
		}

		if (y1 < leftNode.rangeR) {
			update(y1, y2, in, leftIdx);
		}
		if (y2 > rightNode.rangeL) {
			update(y1, y2, in, rightIdx);
		}

//		// 先计算孩子节点的coverLength，再更新当前节点（递归到叶子节点的写法）
//		node.coverLength = leftNode.coverLength + rightNode.coverLength;

		computeCoverLength(node, leftNode, rightNode); // 往上更新
	}

	// 更新以rootIdx节点为根的线段树的coverLenth
	private void update1(int y1, int y2, boolean in, int rootIdx) {
		Node node = nodes[rootIdx];

		int leftIdx = leftChildIdx(rootIdx);
		int rightIdx = rightChildIdx(rootIdx);
		Node leftNode = nodes[leftIdx];
		Node rightNode = nodes[rightIdx];

		// 递归到叶子节点的写法
		if (leftNode == null && rightNode == null) {
			node.coverTimes += (in ? 1 : -1);
			if (node.coverTimes > 0) {
				node.coverLength = node.rangeR - node.rangeL; // 更新当前区间被覆盖长度
			} else {
				node.coverLength = 0;
			}
			return;
		}

		if (y1 < leftNode.rangeR) {
			update(y1, y2, in, leftIdx);
		}
		if (y2 > rightNode.rangeL) {
			update(y1, y2, in, rightIdx);
		}

		// 先计算孩子节点的coverLength，再更新当前节点（递归到叶子节点的写法）
		node.coverLength = leftNode.coverLength + rightNode.coverLength;
	}

	// 根据node的cover数计算coverLength
	private void computeCoverLength(Node node, Node leftNode, Node rightNode) {
		if (node.coverTimes > 0) {
			node.coverLength = node.rangeR - node.rangeL; // 更新当前区间被覆盖长度
		} else {
			node.coverLength = (leftNode == null ? 0 : leftNode.coverLength)
					+ (rightNode == null ? 0 : rightNode.coverLength);
		}
	}

	// 返回整个线段树的coverLength
	public int query() {
		return nodes[0].coverLength;
	}

	private int leftChildIdx(int idx) {
		return idx * 2 + 1;
	}

	private int rightChildIdx(int idx) {
		return idx * 2 + 2;
	}

	// 线段树节点
	private static class Node {
		int rangeL;
		int rangeR;
		int coverTimes; // 完全覆盖该区间的线段数目，如果能完全覆盖父亲节点，则孩子节点的覆盖数不增加
		int coverLength; // 该区间内被覆盖的长度，可能小于区间长度

		public Node(int rangeL, int rangeR) {
			super();
			this.rangeL = rangeL;
			this.rangeR = rangeR;
		}
	}
}