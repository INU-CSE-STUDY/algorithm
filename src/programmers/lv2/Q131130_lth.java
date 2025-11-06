package programmers.lv2;

/* 프로그래머스 131130번 혼자 놀기의 달인 - 문제 번호가 1130이다 감동적인 번호

[문제 풀이]
카드의 범위만큼 visited를 set으로 관리하며 방문하지 않은 index부터 calculate 메서드로 그룹의 크기를 구하여 pq에 넣음
pq에서 가장 큰 2개를 뽑아 곱하여 반환
-> 가장 큰 2개를 뽑는 것을 list로 관리하여 sort할 수도 있지만 pq는 sort가 이미 되어있기에 그냥 pq를 사용

*/

import java.util.*;

class Solution {
    Set<Integer> visited = new HashSet<>();

    public int solution(int[] cards) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < cards.length; i++) {
            if (!visited.contains(i)) {
                pq.offer(calculate(i, cards));
            }
        }

        if (pq.size() < 2) return 0; // 그룹이 하나뿐이면 0
        return pq.poll() * pq.poll(); // 가장 큰 2개 곱
    }

    private int calculate(int start, int[] cards) {
        int size = 0;
        int idx = start;

        while (!visited.contains(idx)) {
            visited.add(idx);
            idx = cards[idx] - 1;
            size++;
        }

        return size;
    }
}
