package programmers.lv2;

/* 프로그래머스 131701번 연속 부분 수열 합의 개수 문제

[문제 풀이]
크기가 작아서 완전탐색이 무조건 가능해보인다. (물론 누적합 코드를 구할 줄 알아야겠죠..?)
- 그냥 특정 지점부터 1, 2, ..., 배열의 최대길이까지 합을 구해서 다 저장하면 됨 (set에 저장해서 중복 체크)

*/

import java.util.*;

class Q131701_kj {
    public int solution(int[] elements) {

        Set<Integer> answer = new HashSet<>();

        for (int start = 0; start < elements.length; start++) {
            int sum = 0;

            // 각 위치별로 길이가 1 ~ 배열의 길이인 연속 부분 순열을 구해서 set에 저장
            for (int next = 0; next < elements.length; next++) {
                sum += elements[(start + next) % elements.length];
                answer.add(sum);
            }
        }

        return answer.size();
    }
}