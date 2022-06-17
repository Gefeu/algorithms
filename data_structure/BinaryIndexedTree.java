package data_structure;

/**
 * 二叉索引树（树状数组），解决动态数据区间和查询问题
 * 利用前缀和思想，前缀和查询快O(1)，但是更新慢O(n)，只适合静态数据的查询
 * 点添加值，O(log(n)) ，点设置值可以转换成点添加值
 * 区间查询，O(log(n))
 * 
 * 主要思想：将区间转换成两段的差，对这两段分别二分，线段树直接对区间二分，而且支持快速区间更新
 * 结论：区间查询和更新问题优先考虑线段树
 */

public class BinaryIndexedTree {
	private int[] segments;
	private int size;

	public BinaryIndexedTree(int size) {
		segments = new int[size + 1]; // 使用数组的[1,size]
		this.size = size;
	}

	// 将i位置的数加上d
	public void add(int i, int d) {
		while (i <= this.size) {
			segments[i] += d;
			i += lowbit(i); // 往右上爬
		}
	}

	// 查询[l,r]中数的和
	public int query(int l, int r) {
		return sum(r) - sum(l - 1);
	}

	// 返回[1,r]中数的和
	private int sum(int r) {
		int result = 0;
		while (r >= 1) {
			result += segments[r];
			r -= lowbit(r); // 往左上爬
		}
		return result;
	}

	// 二进制最低位的1，例如：0b10100 -> 0b100
	private int lowbit(int n) {
		return n & -n;
	}

	public static void main(String[] args) {
		BinaryIndexedTree tree = new BinaryIndexedTree(10);
		for (int i = 1; i <= 10; i++) {
			tree.add(i, i);
		}
		System.out.println(tree.query(2, 5)); // 2+3+4+5=14
	}

}
