package programmers.lv3;


// 보자마자 DP라는 걸 알았다.
public class Q258705_jhg {
    private static final int MOD = 10007;

    public int solution(int n, int[] tops) {
        int[] dp = new int[n + 2];

        dp[1] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i+1] = ((tops[i-1] == 1 ? 4 : 3) * dp[i] - dp[i - 1]) % MOD;
        }

        return dp[n+1];
    }
}
