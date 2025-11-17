
package programmers.lv3;

/* 프로그래머스 214288번 상담원 인원 문제

[문제 풀이]
레벨2에서 푸는것처럼 구해보고 1,2,5,2,2,4,2,2,4....를 찾은뒤 125는 직접 넣어놓고
나머지는 반복문으로 돌렸다 문제 자체는 어렵지 않지만 반복되는 수열을 찾는게 어려운 문제다
*/

class Q181186_lth {
    static final int MOD = 1000000007;

    public int solution(int n) {
        if (n <= 3) {
            int[] answer = {0, 1, 3, 10};
            return answer[n];
        }

        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;

        for (int i = 4; i <= n; i++) {
            long tmp = 0;

            tmp += dp[i - 1] * 1;
            tmp += dp[i - 2] * 2;
            tmp += dp[i - 3] * 5;

            for (int j = 4; j < i; j++) {
                if (j % 3 == 0)
                    tmp += dp[i - j] * 4;
                else
                    tmp += dp[i - j] * 2;
            }

            // j = i → dp[0] = 1
            if (i % 3 == 0)
                tmp += 4;
            else
                tmp += 2;

            dp[i] = tmp % MOD;
        }

        return (int) dp[n];
    }
}
