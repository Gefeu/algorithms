package lanqiao._2021._7_左孩子右兄弟_多叉树dfs;

import static java.lang.Math.max;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    static HashSet<Integer>[] children;
    static int[] childrenNum;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        childrenNum = new int[N + 1];
        children = new HashSet[N + 1];
        for (int i = 0; i < children.length; i++) {
            children[i] = new HashSet<>();
        }

        for (int i = 2; i <= N; i++) {
            int p = scan.nextInt();
            childrenNum[p]++;
            children[p].add(i);
        }
        int h = dfs(1);

        System.out.println(h);
        scan.close();
    }

    private static int dfs(int i) {
        if (children[i].size() == 0) {
            return 0;
        }

        int maxChildHeight = 0;
        for (Integer child : children[i]) {
            maxChildHeight = max(maxChildHeight, dfs(child));
        }

        return childrenNum[i] + maxChildHeight;
    }
}
