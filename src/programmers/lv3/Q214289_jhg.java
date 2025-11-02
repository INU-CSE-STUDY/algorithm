package programmers.lv3;

import java.util.Arrays;

public class Q214289_jhg {

    private static final int TEMP_TEMPERATURE = 10;
    private static final int MAX_TEMPERATURE = 50;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = Integer.MAX_VALUE;

        temperature += TEMP_TEMPERATURE;
        t1 += TEMP_TEMPERATURE;
        t2 += TEMP_TEMPERATURE;

        int[][] dp = new int[1002][51];

        for (int i = 0; i < 1002; i++) {
            Arrays.fill(dp[i], 1<<30);
        }

        dp[0][temperature] = 0;

        for(int i = 0; i < onboard.length - 1; i++){
            for(int j = 0; j <= 50; j++){
                if(onboard[i] == 1 && (j < t1 || t2 < j)){
                    continue;
                }

                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + b);
                if(j >= 1) {
                    dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j] + a);
                }
                if(j < 50) {
                    dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j] + a);
                }
                if(temperature == j){
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]);
                }
                if(temperature > j && j < 50){
                    dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]);
                }
                if(temperature < j){
                    dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]);
                }
            }
        }
        
        int last = onboard.length - 1;

        for (int i = 0; i <= MAX_TEMPERATURE; i++) {
            if (onboard[last] == 1 && (i < t1 || i > t2)) {
                continue;
            }

            answer = Math.min(answer, dp[last][i]);
        }

        return answer;
    }
}
