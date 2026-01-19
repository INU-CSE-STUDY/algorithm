package programmers.lv3;

/* 프로그래머스 136797번 숫자 타자 대회 문제

[문제 풀이]
처음에 그리디로 풀었지만 이번 선택은 맞지만 다음 선택에 영향을 미치기 때문에 dp로 품
dp배열을 2개 만든 이유는 매 문자마다 만들면 성능이 떨어지기에 기존 배열을 스왑하고 초기화함
각 문자에서 가능한 선택은 5가지로 도착할 수 없음, 이미 왼손이 도착한 곳, 이미 오른손이 도착한 곳, 왼손으로 이동, 오른손으로 이동
각 선택마다 비용을 계산하여 최소값을 갱신해줌
*/


import java.util.*;

class Q136797_lth {
    static final int INF = 1_000_000_000;

    public int solution(String numbers) {
        // 0~9 이동/누르기 비용 미리 계산
        int[][] cost = buildCost();

        // dp[l][r] = 현재까지 최소 비용 (왼손 l, 오른손 r)
        // 2개를 만드는 이유는 매 문자마다 만들면 성능이 떨어져서 새로 만들지 않고 이전비용을 기억한채로 새로운 초기화된 배열에 계산을 해야해서
        int[][] dp = new int[10][10];
        int[][] ndp = new int[10][10];

        // 배열초기화
        fill(dp, INF);
        dp[4][6] = 0; // 시작: 왼손 4, 오른손 6

        for (int idx = 0; idx < numbers.length(); idx++) {
            int x = numbers.charAt(idx) - '0';
            fill(ndp, INF);

            for (int l = 0; l < 10; l++) {
                int[] dpl = dp[l];
                for (int r = 0; r < 10; r++) {
                    int cur = dpl[r];
                    // 가능하지 않음
                    if (cur == INF) continue;

                    // 1) 이미 왼손이 x 위면 왼손으로만 누르기
                    if (x == l) {
                        int v = cur + cost[l][x];
                        if (v < ndp[l][r]) ndp[l][r] = v;
                        continue;
                    }

                    // 2) 이미 오른손이 x 위면 오른손으로만 누르기
                    if (x == r) {
                        int v = cur + cost[r][x];
                        if (v < ndp[l][r]) ndp[l][r] = v;
                        continue;
                    }

                    // 3) 왼손으로 누르기
                    if (x != r) {
                        int v = cur + cost[l][x];
                        if (v < ndp[x][r]) ndp[x][r] = v;
                    }

                    // 4) 오른손으로 누르기
                    if (x != l) {
                        int v = cur + cost[r][x];
                        if (v < ndp[l][x]) ndp[l][x] = v;
                    }
                }
            }

            // swap
            int[][] tmp = dp;
            dp = ndp;
            ndp = tmp;
        }

        int answer = INF;
        // 최소값 찾기
        for (int l = 0; l < 10; l++) {
            for (int r = 0; r < 10; r++) {
                answer = Math.min(answer, dp[l][r]);
            }
        }
        return answer;
    }

    // dp 배열 INF로 채우기
    private void fill(int[][] a, int val) {
        for (int i = 0; i < 10; i++) Arrays.fill(a[i], val);
    }

    // 비용 테이블 (제자리=1, 상하좌우=2, 대각선=3)
    private int[][] buildCost() {
        int[][] cost = new int[10][10];
        for (int s = 0; s < 10; s++) {
            for (int e = 0; e < 10; e++) {
                cost[s][e] = calcCost(s, e);
            }
        }
        return cost;
    }

    // 비용 계산
    private int calcCost(int s, int e) {
        if (s == e) return 1; // 제자리 누르기

        int r1, c1;
        if (s == 0) { r1 = 3; c1 = 1; }
        else { r1 = (s - 1) / 3; c1 = (s - 1) % 3; }

        int r2, c2;
        if (e == 0) { r2 = 3; c2 = 1; }
        else { r2 = (e - 1) / 3; c2 = (e - 1) % 3; }

        int dr = Math.abs(r1 - r2);
        int dc = Math.abs(c1 - c2);

        int diag = Math.min(dr, dc);
        int straight = Math.abs(dr - dc);

        return diag * 3 + straight * 2; // 인접 규칙 비용
    }
}
