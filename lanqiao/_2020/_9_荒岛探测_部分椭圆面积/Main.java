package lanqiao._2020._9_荒岛探测_部分椭圆面积;

import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Math.*;

public class Main {
	static double xc;
	static double yc;
	static double sinTheta;
	static double cosTheta;
	static double a;
	static double b;
	static double ans = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		double xa = scan.nextInt();
		double ya = scan.nextInt();
		double xb = scan.nextInt();
		double yb = scan.nextInt();
		int L = scan.nextInt();

		a = L / 2.0;
		xc = (xa + xb) / 2.0;
		yc = (ya + yb) / 2.0;
		double distance = sqrt(pow(xa - xb, 2) + pow(ya - yb, 2));
		sinTheta = (yb - ya) / distance;
		cosTheta = (xb - xa) / distance;
		b = sqrt(pow(a, 2) - pow(distance / 2.0, 2));

		Point[] foci = { new Point(xa, ya), new Point(xb, yb) };
		Arrays.sort(foci);

		double x1 = scan.nextInt();
		double y1 = scan.nextInt();
		double x2 = scan.nextInt();
		double y2 = scan.nextInt();
		double x3 = scan.nextInt();
		double y3 = scan.nextInt();

		Point[] tri = { new Point(x1, y1), new Point(x2, y2), new Point(x3, y3) };
		Arrays.sort(tri);
		int[] ind = { 0, 1, 0, 1, 0, 2, // 前两个点的x为积分的范围，中间两个点和后面两个点分别代表一条直线
				1, 2, 1, 2, 0, 2 };

		double w = 0.0001; // 精度必须足够高
		for (int i = 0; i < ind.length; i += 6) {
			double xl = tri[ind[i]].x;
			double xr = tri[ind[i + 1]].x;
			for (double x = xl; x <= xr; x += w) {
				double y12 = getIntersecY(tri[ind[i + 2]], tri[ind[i + 3]], x);
				double y13 = getIntersecY(tri[ind[i + 4]], tri[ind[i + 5]], x);
				if (y12 < y13) {
					double t = y12;
					y12 = y13;
					y13 = t;
				}
				if (x < -a || x > a) {
					continue;
				}
				double yu = getUpperY(x);
				double yl = getLowerY(x);
				yu = min(y12, yu);
				yl = max(y13, yl);
				ans += w * (yu - yl > 0 ? yu - yl : 0); // 确保三角形和椭圆在竖直方向上有交集
			}
		}

		System.out.printf("%.2f", ans);
		scan.close();
	}

	private static double getLowerY(double x) {
		return -getUpperY(x);
	}

	// 与椭圆交点的y值
	private static double getUpperY(double x) {
		return b * sqrt(1 - pow(x, 2) / pow(a, 2));
	}

	// 与三角形交点的y值
	private static double getIntersecY(Point l, Point r, double x) {
		if (x == l.x) {
			return l.y;
		}
		if (x == r.x) {
			return r.y;
		}

		return l.y + (x - l.x) / (r.x - l.x) * (r.y - l.y);
	}

	static class Point implements Comparable<Point> {
		double x;
		double y;

		public Point(double x, double y) {
			super();
			this.x = transformX(x, y);
			this.y = transformY(x, y);
		}

		@Override
		public int compareTo(Point o) {
			return this.x - o.x > 0 ? 1 : this.x == o.x ? 0 : -1;
		}

		private static double transformX(double x, double y) {

			return cosTheta * (x - xc) + sinTheta * (y - yc);
		}

		private static double transformY(double x, double y) {

			return -sinTheta * (x - xc) + cosTheta * (y - yc);
		}

	}
}
