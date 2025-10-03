package programmers.lv2;

/* 프로그래머스 154538번 숫자 변환하기 문제

[문제 풀이]
문제 보자마자 든 생각은.. DP로 풀어보고 싶다는 생각..
크기가 y + 1인 배열을 만들고 차근차근 연산하면서 최소횟수 저장하기
y를 만들 수 있다면 memo[y]에 어떤 값이 있을거고, y 못만들면 초기값이 있을테니 이걸로 -1 리턴하면 됨

*/

import java.util.*;

class Q154538_kj {
    public int solution(int x, int y, int n) {

        int[] memo = new int[y + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);

        memo[x] = 0;
        for (int i = x; i <= y; i++) {
            // 연산을 통해 도달할 수 있는 값이 아니면 넘어가기
            if (memo[i] == Integer.MAX_VALUE) continue;

            // i + n / i * 2 / i * 3 이 y보다 작을 때 최소 연산횟수 저장
            if (i + n <= y) {
                memo[i + n] = Math.min(memo[i + n], memo[i] + 1);
            }

            if (i * 2 <= y) {
                memo[i * 2] = Math.min(memo[i * 2], memo[i] + 1);
            }

            if (i * 3 <= y) {
                memo[i * 3] = Math.min(memo[i * 3], memo[i] + 1);
            }
        }

        return memo[y] == Integer.MAX_VALUE ? -1 : memo[y];
    }
}
