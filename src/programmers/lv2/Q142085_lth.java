package programmers.lv2;

/* 프로그래머스 142085번 디펜스 게임

[문제 풀이]
k+1개까지는 무조건 갈수 있으니 우선순위큐에 K+1까지 넣음
가장 낮은 숫자를 꺼내고 n에서 뺀 뒤 n을 다 쓰면 종료
*/

import java.util.*;

class Q142085_lth {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 작은 적부터 정렬

        for (int i = 0; i < enemy.length; i++) {
            pq.offer(enemy[i]);

            // 적이 k+1개 이상이면 → 가장 작은 적부터 병력 소모
            if (pq.size() > k) {
                n -= pq.poll();
            }

            // 병력이 바닥나면 게임 종료
            if (n < 0) {
                return i;
            }
        }

        // 모두 방어 성공
        return enemy.length;
    }
}