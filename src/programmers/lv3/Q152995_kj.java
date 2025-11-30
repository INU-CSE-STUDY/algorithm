package programmers.lv3;

/* 프로그래머스 152995번 인사고과 문제

[문제 풀이]
문제 내용 자체는 쉬움. 인센티브 못 받는 사람 거르고, 받을 수 있는 사람 중에 완호의 등수를 구하는 문제

인센티브 못 받는 사람 -> 이거 거르는 방법을 잘 생각하는게 문제였다...
솔직히 누가봐도 완전탐색하면 안되는 문제지만 나는 결국 완전탐색을 해버렸고 시간초과를 맛보았다.

조금의 도움을 받아 알게된 사실은,,
1. 당연히 완전탐색하면 시간초과가 난다. (길이 10만이라..)
2. 완탐을 하지 않고도, 인센티브를 받지 못하는 사람을 거르는 방법은 정렬을 잘해보기이다!
   > 근무 태도 점수(얘가 앞에 있어서 그냥 얘 기준으로 정렬함)를 내림차순으로 정렬 + 동료 평가 점수를 오름차순으로 정렬
     이렇게 하면 i < j 일 때, j의 근무 태도 점수는 i의 근무 태도 점수와 같거나 낮음
     - 근무 태도 점수가 같은 경우는 동료 평가 점수가 오름차순이라 무조건 인센티브 만족
     - 근무 태도 점수가 낮아지면 동료 평가 점수의 max 값 기준으로 인센티브 여부 확인할 수 있기 때문에 간단하게 쳐내기 가능
   - 이렇게 해서 인센티브 받을 수 있는 사원은 리스트에 따로 저장
3. 리스트를 점수 합으로 내림차순해서 완호 점수보다 높은 사람 한명씩 세다가, 완호랑 같은 점수 나오면 동석차니까 그대로 반환하기!


*/

import java.util.*;

class Q152995_kj {
    public int solution(int[][] scores) {
        int[] targetScore = scores[0];
        int targetScoreSum = targetScore[0] + targetScore[1];

        Arrays.sort(scores, Comparator.comparingInt((int[] score) -> score[0]).reversed()
                .thenComparingInt((int[] score) -> score[1]));

        List<int[]> insentiveList = new ArrayList<>();
        int maxScore = 0;
        for (int i = 0; i < scores.length; i++) {

            int[] score = scores[i];

            if (i == 0) {
                insentiveList.add(score);
                maxScore = score[1];
                continue;
            }

            if (score[1] < maxScore) {

                if (Arrays.equals(score, targetScore)) {
                    // 현재 근무 태도 점수와 동료 평가 점수가 완호의 점수와 같은 경우 == 완호 역시 인센티브를 받지 못한다는 뜻
                    return -1;
                }

                continue;
            }

            maxScore = Math.max(maxScore, score[1]);
            insentiveList.add(score);
        }

        int answer = 1;
        insentiveList.sort(Comparator.comparingInt((int[] score) -> score[0] + score[1]).reversed());
        for (int[] score : insentiveList) {
            if (score[0] + score[1] > targetScoreSum) answer++;
            else break;
        }

        return answer;
    }
}
