package programmers.lv2;

import java.util.*;

public class Q154538_jhg {

    static final int MAX = Integer.MAX_VALUE;

    public int solution(int x, int y, int n) {
        int[] dp = new int[3000001];
        Arrays.fill(dp, MAX);

        dp[x] = 0;

        for (int i = x; i <= y; i++) {
            if (dp[i] < Integer.MAX_VALUE) {
                dp[i + n] = Math.min(dp[i + n], dp[i] + 1);
                dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
                dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
            }
        }

        return dp[y] == MAX ? -1 : dp[y];
    }
}
