package lanqiao._2017._7_正则问题;

import java.util.Scanner;

public class Main {
	static char[] s;
	static int len;
	static int pos;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		s = scan.nextLine().toCharArray();
		len = s.length;
		int ans = getMaxLen();
		System.out.println(ans);
		scan.close();
	}

	private static int getMaxLen() {
		int maxLen = 0;
		int tempLen = 0;
		while (pos < len) {
			if (s[pos] == 'x') {
				tempLen++;
				pos++;
			} else if (s[pos] == '(') {
				pos++;
				tempLen += getMaxLenInParentheses();
			} else if (s[pos] == '|') {
				maxLen = Math.max(tempLen, maxLen);
				tempLen = 0;
				pos++;
			}
		}
		maxLen = Math.max(tempLen, maxLen);
		return maxLen;
	}

	private static int getMaxLenInParentheses() {
		int maxLen = 0;
		int tempLen = 0;
		while (pos < len) {
			if (s[pos] == 'x') {
				tempLen++;
				pos++;
			} else if (s[pos] == '(') {
				pos++;
				tempLen += getMaxLenInParentheses();
			} else if (s[pos] == '|') {
				maxLen = Math.max(tempLen, maxLen);
				tempLen = 0;
				pos++;
			} else if (s[pos] == ')') {
				maxLen = Math.max(tempLen, maxLen);
				pos++;
				return maxLen;
			}
		}
		return maxLen;
	}
}
