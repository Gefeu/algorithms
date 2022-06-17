package lanqiao._2019._8_修改数组_线段的并查集;

import java.util.Scanner;

public class Main1 {
	static Range rangeHead = new Range(0, 0);

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		for (int i = 0; i < N; i++) {
			System.out.print(updateRanges(scan.nextInt()) + " ");
		}

		scan.close();
	}

	private static int updateRanges(int num) {
		int res = num;
		Range last = rangeHead;
		Range range = rangeHead.next;

		while (range != null) {
			Range next = range.next;
			if (range.left <= num && num <= range.right + 1) {
				res = ++range.right;
				if (next != null && next.left == range.right + 1) {
					range.right = next.right;
					range.next = next.next;
				}
				break;
			} else if (num == range.left - 1) {
				res = --range.left;
				break;
			} else if (num < range.left) {
				Range node = new Range(num, num, range);
				last.next = node;
				break;
			}
			last = range;
			range = next;
		}

		// 还是能变成O(n^2)的复杂度，比如 1 3 5 7 9...
		if (range == null) {
			last.next = new Range(num, num);
		}
		return res;
	}

	static class Range {
		int left;
		int right;
		Range next;

		public Range(int left, int right, Range next) {
			this.left = left;
			this.right = right;
			this.next = next;
		}

		public Range(int left, int right) {
			this(left, right, null);
		}
	}
}
