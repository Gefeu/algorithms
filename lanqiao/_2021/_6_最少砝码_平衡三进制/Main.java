package lanqiao._2021._6_最少砝码_平衡三进制;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		System.out.println(balanceLength(n));
		scan.close();
	}

	private static int balanceLength(int n) {
		char[] arr = Integer.toString(n, 3).toCharArray();
		boolean add = false;
		for (int i = arr.length - 1; i >= 0; i--) {
			if (add) {
				if (arr[i] + 1 == '3')
					continue;
				else {
					arr[i] = (char) (arr[i] + 1);
					add = false;
				}
			}
			if (arr[i] == '2') {
				add = true;
			}
		}
		return add ? arr.length + 1 : arr.length;
	}
}
