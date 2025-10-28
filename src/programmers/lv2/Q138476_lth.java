package programmers.lv2;

/* 프로그래머스 138476번 귤 고르기 문제

[문제 풀이]
hashmap으로 개수를 세서 개수가 큰 순서대로 pq에 넣어
k가 될때까지 빼기

*/


import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        for (Integer size : tangerine) {
            // 이미 있는 key라면 value를 가져오고, 없다면 0을 가져와서 1을 더함
            counts.put(size, counts.getOrDefault(size, 0) + 1);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int count : counts.values()) {
            pq.offer(count);
        }
        
        while(k > 0 && !pq.isEmpty()){
            int cnt = pq.poll();
            k -= cnt;
            answer++;
        }
        
        return answer;
    }
}