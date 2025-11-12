package programmers.lv2;

/* 프로그래머스 92342번 양궁대회 문제

[문제 풀이]

info = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0] 순서로 되어있는 배열 (= i번째 원소는 과녁의 10 - i 점을 맞힌 화살 개수)

백트래킹으로 앞에서부터 승리한다는 가정으로 쏴보기
(ex) 어피치가 10점 두 발을 쐈다면 라이언은 3발 쏘고, 백트래킹 진행
     그 다음은 라이언이 10점 0개 쐈다치고 9점부터 다시 이기는 방향으로 백트래킹 진행
=> 이렇게 하면, 별 다른 연산 없이 낮은 점수를 더 많이 맞힌 경우가 자연스럽게 갱신됨

라이언이 어떻게 화살을 쏘든 어피치의 점수보다 낮거나 같으면 maxScoreDiff가 갱신되지 않고 0인채로 백트래킹이 끝남
=> maxScoreDiff가 0이면 new int[]{ -1 }, 아니라면 갱신된 ryan 배열 반환
*/

import java.util.*;

class Q92342_kj {
    final int length = 11;
    int[] apeach, ryan, answer;
    int maxScoreDiff = 0;

    public int[] solution(int n, int[] info) {
        apeach = info;
        ryan = new int[length];
        answer = new int[length];

        backtrack(0, n);

        return maxScoreDiff > 0 ? answer : new int[]{ -1 };
    }

    private void backtrack(int index, int arrowCnt) {
        if (index == length) {
            ryan[10] = arrowCnt; // 가장 낮은 점수를 더 많이 맞힌 경우가 정답이므로, 0점에 남은 화살 몰아주기

            int nowScoreDiff = getScoreDiff(apeach, ryan);

            if (nowScoreDiff > 0) {
                // 점수 차이가 양수이면서(라이언이 이긴 경우), 이전보다 더 큰 차이로 이겼을 경우 정답 배열 업데이트

                if (nowScoreDiff > maxScoreDiff) {
                    maxScoreDiff = nowScoreDiff;
                    answer = ryan.clone();
                } else if (nowScoreDiff == maxScoreDiff) {
                    for (int i = length - 1; i >= 0; i--) {
                        if (ryan[i] < answer[i]) {
                            // 기존 배열이 더 낮은 점수를 많이 맞췄을 경우 그냥 종료
                            break;
                        } else if (ryan[i] > answer[i]) {
                            // 새로운 배열이 더 낮은 점수를 많이 맞췄을 경우 업데이트
                            answer = ryan.clone();
                            break;
                        }
                    }
                }
            }

            ryan[10] = 0;
            return;
        }

        int arrowToWin = apeach[index] + 1;

        if (arrowCnt >= arrowToWin) {
            // 화살을 쏠 수 있는 경우는 화살을 쏴서 이겨서 백트래킹 진행하기
            ryan[index] = arrowToWin;
            backtrack(index + 1, arrowCnt - arrowToWin);
            ryan[index] = 0;
        }

        backtrack(index + 1, arrowCnt);
    }

    private int getScoreDiff(int[] apeach, int[] ryan) {

        int apeachScore = 0;
        int ryanScore = 0;

        for (int i = 0; i < length; i++) {

            int apeachArrow = apeach[i];
            int ryanArrow = ryan[i];

            // 라이언과 어피치 모두 k점에 화살을 맞히지 못한 경우는 점수를 가져가지 않음
            if (apeachArrow == 0 && ryanArrow == 0) continue;

            if (apeachArrow < ryanArrow) {
                // 어피치의 화살 개수 < 라이언의 화살 개수
                ryanScore += (10 - i);
            } else {
                // 어피치의 화살 개수 >= 라이언의 화살 개수
                apeachScore += (10 - i);
            }
        }

        return ryanScore - apeachScore; // 라이언과 어피치의 점수 차이 반환
    }
}