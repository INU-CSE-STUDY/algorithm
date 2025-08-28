package programmers.lv2;

/* 프로그래머스 389480번 완전범죄 문제

[문제 풀이]
A 도둑이 남긴 흔적의 누적 개수의 최솟값을 구하는 문제
=> B 도둑이 최대한 많이 훔치면서, A 흔적 개수의 합이 최소여야 함
반복문으로 하나씩 다 돌면서 B가 훔칠 수 있는지 없는지 확인하기..?
=> 무조건 시간 초과 날 듯...

완전탐색이 시간 초과나니까 DP로 해보기?
총 두 가지 경우가 있음
- A 도둑이 훔치는 경우
- B 도둑이 훔치는 경우

각 도둑이 훔칠 때 자기 자신의 흔적 값은 증가하지만, 다른 도둑의 흔적 값은 증가하지 않음
(= A가 훔치면 A만, B가 훔치면 B만 증가)
- 여기서 중요한 건, B가 훔칠 수 있는 상황이면 무조건 B가 훔치는게 낫다는 거임..!
=> 그렇다면 dp 배열을 2차원 배열로 해서, 각 물건을 훔쳤을 경우에 a의 흔적값 저장
∴ a 흔적값은 dp[i - 1][j] + a 와 같이 업데이트,
  b 흔적값은 dp[i][j + b]와 같이 업데이트해서 b가 훔칠 수 있는 경우 a의 흔적값에 영향가지 않게 저장
  => dp 배열을 [info.length][m] 처럼 정의해서 j + b < m인 상황에서만 저장할 수 있게 관리하기!
*/

class Q389480_kj {
    static int INF = Integer.MAX_VALUE;
    public int solution(int[][] info, int n, int m) {

        // dp 배열 초기화
        int[][] dp = new int[info.length][m];
        for (int i = 0; i < info.length; i++) {
            for (int j = 0; j < m; j++) {
                // 최솟값을 구해야 하므로, 도달 불가능한 곳은 최댓값으로 채우기
                dp[i][j] = INF;
            }
        }

        // 가장 첫 물건을 누가 훔쳐갈 지 골라야 함
        int a = info[0][0];
        int b = info[0][1];

        // a가 훔치는 경우
        if (a < n) {
            dp[0][0] = a;
        }

        // b가 훔치는 경우(a의 흔적값은 0)
        if (b < m) {
            dp[0][b] = 0;
        }

        for (int i = 1; i < info.length; i++) {
            for (int j = 0; j < m; j++) {
                a = info[i][0];
                b = info[i][1];

                // 불가능한 경우는 계산하지 않음
                if (dp[i - 1][j] == INF) continue;

                // a가 물건을 훔치는 경우
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);

                // b가 물건을 훔칠 수 있는 경우
                if (j + b < m) {
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
                }
            }
        }

        int answer = INF;
        for (int i = 0; i < m; i++) {
            answer = Math.min(answer, dp[info.length - 1][i]);
        }

        return (answer < n) ? answer : -1;
    }
}