package lanqiao._2017._3_魔方状态;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 在此输入您的代码...
		MagicCube start = new MagicCube(
				new String[] { "oybbgb", "oygbbb", "obbogb", "obgobb", "bygbby", "bybbgy", "bbgoby", "bbbogy" });
		HashSet<String> set = new HashSet<>();
		LinkedList<MagicCube> list = new LinkedList<>();

		list.addLast(start);
		set.add(start.toString());

		while (list.size() > 0) {
			MagicCube mc = list.removeFirst();
			for (int i = 1; i <= 3; i++) { // i=4不用判断
				mc.u(i);
				if (!contains(set, mc)) {
					String s = mc.toString();
					list.addLast(new MagicCube(s)); // 需要重新创建对象
					set.add(s);
				}
				mc.u(4 - i);
			}
			for (int i = 1; i <= 3; i++) {
				mc.f(i);
				if (!contains(set, mc)) {
					String s = mc.toString();
					list.addLast(new MagicCube(s));
					set.add(s);
				}
				mc.f(4 - i);
			}
			for (int i = 1; i <= 3; i++) {
				mc.r(i);
				if (!contains(set, mc)) {
					String s = mc.toString();
					list.addLast(new MagicCube(s));
					set.add(s);
				}
				mc.r(4 - i);
			}
		}
		System.out.println(set.size());
		scan.close();
	}

	private static boolean contains(HashSet<String> set, MagicCube mc) {
		boolean r = false;

		mc.fwhole(1);
		r = containsByUwhole(set, mc);
		mc.fwhole(3);
		if (r) {
			return r;
		}

		mc.fwhole(2);
		r = containsByUwhole(set, mc);
		mc.fwhole(2);
		if (r) {
			return r;
		}

		mc.fwhole(3);
		r = containsByUwhole(set, mc);
		mc.fwhole(1);
		if (r) {
			return r;
		}

		mc.rwhole(1);
		r = containsByUwhole(set, mc);
		mc.rwhole(3);
		if (r) {
			return r;
		}

		mc.rwhole(3); // 注意不能重复
		r = containsByUwhole(set, mc);
		mc.rwhole(1);
		if (r) {
			return r;
		}

		r = containsByUwhole(set, mc);
		return r;
	}

	private static boolean containsByUwhole(HashSet<String> set, MagicCube mc) {
		boolean r = false;
		for (int i = 1; i <= 4 && !r; i++) {
			mc.uwhole(i);
			if (set.contains(mc.toString())) {
				r = true;
			}
			mc.uwhole(4 - i);
		}
		return r;
	}

}

class MagicCube {
	// front 0123, back 4567 (沿z轴旋转至背面)
	private Block[] blocks = new Block[8]; 

	public MagicCube(String[] ss) {
		init(ss);
	}

	public MagicCube(String s) {
		String[] ss = new String[8];
		for (int i = 0; i < 8; i++) {
			ss[i] = s.substring(i * 6, i * 6 + 6); // 注意分割的索引范围
		}
		init(ss);
	}

	public void init(String[] ss) {
		for (int i = 0; i < ss.length; i++) {
			this.blocks[i] = new Block(ss[i]);
		}
	}

	public void u(int times) {
		while (times-- > 0) {
			int[] indices = { 0, 5, 4, 1 }; // 注意方向
			rotateBlocks(indices, 'u');
			shiftBlocks(indices);
		}
	}

	public void uwhole(int times) {
		while (times-- > 0) {
			u();
			int[] indices = { 2, 7, 6, 3 };
			rotateBlocks(indices, 'u');
			shiftBlocks(indices);
		}
	}

	public void f(int times) {
		while (times-- > 0) {
			int[] indices = { 0, 1, 3, 2 };
			rotateBlocks(indices, 'f');
			shiftBlocks(indices);
		}
	}

	public void fwhole(int times) {
		while (times-- > 0) {
			f();
			int[] indices = { 5, 4, 6, 7 };
			rotateBlocks(indices, 'f');
			shiftBlocks(indices);
		}
	}

	public void r(int times) {
		while (times-- > 0) {
			int[] indices = { 1, 4, 6, 3 };
			rotateBlocks(indices, 'r');
			shiftBlocks(indices);
		}

	}

	public void rwhole(int times) {
		while (times-- > 0) {
			r();
			int[] indices = { 0, 5, 7, 2 };
			rotateBlocks(indices, 'r');
			shiftBlocks(indices);
		}
	}

	public void u() {
		u(1);
	}

	public void f() {
		f(1);
	}

	public void r() {
		r(1);
	}

	public void uwhole() {
		uwhole(1);
	}

	public void fwhole() {
		fwhole(1);
	}

	public void rwhole() {
		rwhole(1);
	}

	private void shiftBlocks(int[] indices) { // 注意各索引的含义
		int lastIdx = indices[indices.length - 1];
		Block t = blocks[lastIdx];
		for (int i = indices.length - 1; i >= 1; i--) {
			blocks[indices[i]] = blocks[indices[i - 1]];
		}
		blocks[indices[0]] = t;
	}

	private void rotateBlocks(int[] indices, char dir) {
		switch (dir) {
		case 'u':
			for (int idx : indices) {
				blocks[idx].u();
			}
			break;
		case 'f':
			for (int idx : indices) {
				blocks[idx].f();
			}
			break;
		case 'r':
			for (int idx : indices) {
				blocks[idx].r();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public String toString() {
		String s = "";
		for (Block b : blocks) {
			s += b.toString();
		}
		return s;
	}
}

class Block {
	// 0 front, 1 top, 2 right, 3 bottom, 4 left, 5 back
	private char[] sides;

	public Block(String state) {
		this.sides = state.toCharArray();
	}

	public void u(int times) {
		while (times-- > 0) {
			shiftSides(new int[] { 0, 4, 5, 2 });
		}
	}

	public void f(int times) {
		while (times-- > 0) {
			shiftSides(new int[] { 1, 2, 3, 4 });
		}
	}

	public void r(int times) {
		while (times-- > 0) {
			shiftSides(new int[] { 0, 1, 5, 3 });
		}
	}

	public void u() {
		u(1);
	}

	public void f() {
		f(1);
	}

	public void r() {
		r(1);
	}

	private void shiftSides(int[] indices) {
		int lastIdx = indices[indices.length - 1];
		char t = sides[lastIdx];
		for (int i = indices.length - 1; i >= 1; i--) {
			sides[indices[i]] = sides[indices[i - 1]];
		}
		sides[indices[0]] = t;
	}

	@Override
	public String toString() {
		return String.valueOf(sides);
	}
}