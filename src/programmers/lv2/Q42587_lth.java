package programmers.lv2;

/* 프로그래머스 42587번 프로세스 문제

[문제 풀이]
큐에 우선순위와 인덱스를 같이 넣고
꺼낸 값보다 큰 우선순위가 있는지 확인하면서 
기존의 인덱스값이 가장 큰 우선순위일때까지 실행행
*/

import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < priorities.length; i++) {
            q.offer(new int[]{priorities[i], i}); // 우선순위, 원래 인덱스
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curPri = cur[0];
            int curIdx = cur[1];

            // 큐 안에 curPri보다 큰 우선순위가 있는지 확인
            boolean hasHigher = false;
            for (int[] x : q) {
                if (x[0] > curPri) {
                    hasHigher = true;
                    break;
                }
            }

            if (hasHigher) {
                // 더 큰 게 있으면 다시 뒤로
                q.offer(cur);
            } else {
                // 출력
                answer++;
                if (curIdx == location) {
                    return answer;
                }
            }
        }

        return answer;
    }
}