package lanqiao.test;

import java.util.*;

public class Main5 {
    private static int n;

    private static long C(long a, long b) {
        long res = 1;
        for (long i = a, j = 1; j <= b; i--, j++) {
            res = res * i / j;
            if (res > n) {
                return res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        n = cin.nextInt();
        for (int k = 16; k >= 0; k--) {
            int l = 2 * k, r = Math.max(n, l), res = -1;
            while (l <= r) {
                int mid = l + r >> 1;
                if (C(mid, k) >= n) {
                    res = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            if (C(res, k) == n) {
                System.out.println((long) (res + 1) * res / 2 + k + 1);
                break;
            }
        }
        cin.close();
    }
}