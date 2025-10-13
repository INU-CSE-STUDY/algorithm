package programmers.lv2;

/* 프로그래머스 12905번 가장 큰 정사각형 찾기 문제

[문제 풀이]
f(i,j) = min(f(i-1,j), f(i,j-1), f(i-1,j-1)) + 1
라는 점화식을 구현하는 문제이다
처음에는 그냥 1을 발견하면 최대길이를 구하고 정사각형인지 확인하는 방법으로 접근했는데
시간초과가 나서 질문하기를 보고 dp임을 깨달았다
*/

class Q12905_lth
{
    public int solution(int [][]board)
    {
        int answer = 0;
        int n = board.length + 1;
        int m = board[0].length + 1;
        int[][] dp = new int[n][m];
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(board[i-1][j-1] == 1){
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                    if (dp[i][j] > answer){
                        answer = dp[i][j];
                    }
                }
            }
        }

        return answer*answer;
    }
}