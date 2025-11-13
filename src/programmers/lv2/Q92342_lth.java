package programmers.lv2;

/* 프로그래머스 92342번 양궁대회 문제

[문제 풀이]
화살 한 발로 얻을 수 있는 점수를 구해서 pq에 넣고 가장 큰 점수부터 라이언이 가져가는 방식으로 구현
그리디방식으로 접근했지만 테케에서 틀려서 완탐을 해야한다는 것을 찾음 
저번 3레벨은 완탐으로 풀었지만 그리디가 맞는 문제였지만 이번에는 아니었다
-> 백트래킹이 어려워서 진님의 코드를 참고하였습니다
*/

import java.util.*;

class Solution {

    final int LENGTH = 11;
    int[] apeach;
    int[] ryan;
    int[] best;
    int maxDiff = -1;

    public int[] solution(int n, int[] info) {

        apeach = info;
        ryan = new int[LENGTH];
        best = new int[LENGTH];

        dfs(0, n);

        // 이길 수 없다면 -1
        if (maxDiff <= 0) return new int[]{ -1 };
        return best;
    }

    void dfs(int idx, int remain) {
        // 마지막 점수까지 다 봤다면
        if (idx == LENGTH) {

            // 남은 화살을 0점에 몰아준다
            ryan[10] += remain;

            // 점수 차이 계산
            int diff = calcScoreDiff();

            if (diff > 0) {
                // 점수 차가 더 크면 무조건 갱신
                // clone을 안 하면 누적이 되어서 안됨 매 재귀마다 새로 만들어줘야함
                if (diff > maxDiff) {
                    maxDiff = diff;
                    best = ryan.clone();
                }
                // 점수 차가 같으면 낮은 점수 많이 맞힌 쪽을 선택
                else if (diff == maxDiff && isBetter()) {
                    best = ryan.clone();
                }
            }

            // 복구
            ryan[10] -= remain;

            return;
        }

        int need = apeach[idx] + 1;

        // 인덱스 점수를 이길경우
        if (remain >= need) {
            ryan[idx] = need;
            dfs(idx + 1, remain - need);
            ryan[idx] = 0;
        }

        // 인덱스 점수를 포기할 경우
        dfs(idx + 1, remain);
    }

    // 점수 차이 계산
    int calcScoreDiff() {
        int r = 0, a = 0;

        for (int i = 0; i < LENGTH; i++) {
            if (ryan[i] == 0 && apeach[i] == 0) continue;
            if (ryan[i] > apeach[i]) r += (10 - i);
            else a += (10 - i);
        }

        return r - a;
    }

    // 낮은 점수(인덱스 큰 곳)에 더 많이 맞힌 경우가 더 좋은 배열
    boolean isBetter() {
        for (int i = LENGTH - 1; i >= 0; i--) {
            if (ryan[i] > best[i]) return true;
            if (ryan[i] < best[i]) return false;
        }
        return false;
    }
}
