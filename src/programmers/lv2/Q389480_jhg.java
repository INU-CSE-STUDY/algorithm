package programmers.lv2;

import java.util.Arrays;

/**
 * 풀이
 * 1. 완전 탐색은 아니다.
 * -> 2^40이라 연산이 약 1조? 일거 같아서 시간 초과 뜰 거 같았다/
 * 2. 그리디는 아닌거 같다.
 * -> 최소해를 찾을 가능성이 없다.. A랑 B의 관계가 없다...
 * 3. 그러면 탐색 OR DP라는 건데 탐색을 제외 함.
 * -> 사실 탐색도 가능할 거 같은데? 가지치기를 열심히 해야할 거 같아서 일단 배제를 함.
 * 4. DP로 접근해 보았다.
 * -> B가 남길 수 있는 흔적을 계산하면서 A가 남기는 최소한의 흔적을 찾으면 될 거 같으넫...
 */
public class Q389480_jhg {
    static final int INF = 2 << 20;
    static int[][] dp = new int[41][130];
    static int answer = INF;


    static {
        for(int i = 0; i < 41; i++){
            Arrays.fill(dp[i], INF);
        }
    }

    public int solution(int[][] info, final int n, final int m) {
        final int SIZE = info.length;

        dp[0][0] = 0;

        for(int i = 1; i <= SIZE; i++){
            int a = info[i - 1][0];
            int b = info[i - 1][1];
            for(int j = 0; j < m; j++){

                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);

                if(j + b < m){
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
                }
            }
        }

        answer = Arrays.stream(dp[SIZE])
                .min()
                .orElse(INF);

        return answer >= n ? -1 : answer;
    }
}
