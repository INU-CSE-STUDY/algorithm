package programmers.lv2;

/* 프로그래머스 42626번 더 맵게

[문제 풀이]
pq로 작은 순으로 해서 k 이상이 될때까지 시행 2개 이하가 남으면 종료
1개 남기전에 k 이상이거나 1개 남았을때 k 이상이면 성공 아니면 -1
*/

import java.util.*;

class Q42626_lth {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville){
            pq.offer(s);    
        } 
        
        while (pq.size() >= 2 && pq.peek() < K) {
            int a = pq.poll();      // 가장 작은 값
            int b = pq.poll();      // 두 번째로 작은 값
            pq.offer(a + b * 2);
            answer++;
        }

        // 남은 최솟값이 K 이상이면 성공 아니면 실패(-1)
        return (pq.peek() != null && pq.peek() >= K) ? answer : -1;
    }
}