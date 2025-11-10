package programmers.lv3;

/* 프로그래머스 214288번 상담원 인원 문제

[문제 풀이]
그리디로 풀어봄
*/

import java.util.*;

class Q214288_2_lth {
    public int solution(int k, int n, int[][] reqs) {
        int remain = n - k;
        List<int[]>[] byType = new ArrayList[k];

        // 유형별로 요청 분류
        for (int i = 0; i < k; i++){
            byType[i] = new ArrayList<>();  
        } 
        for (int[] r : reqs) {
            int start = r[0];
            int duration = r[1];
            int type = r[2] - 1;
            byType[type].add(new int[]{start, duration});
        }
        for (int i = 0; i < k; i++){
            byType[i].sort(Comparator.comparingInt(a -> a[0]));  
        } 

        int[] alloc = new int[k];
        Arrays.fill(alloc, 1); // 각 유형 최소 1명

        // 그리디 반복
        while (remain > 0) {
            int maxGain = 0;
            int bestType = -1;

            // 각 유형별로 멘토 1명 추가시 이득 계산
            for (int i = 0; i < k; i++) {
                int curWait = waitTime(byType[i], alloc[i]);
                int nextWait = waitTime(byType[i], alloc[i] + 1);
                int gain = curWait - nextWait;

                if (gain > maxGain) {
                    maxGain = gain;
                    bestType = i;
                }
            }

            // 가장 이득이 큰 유형에 멘토 1명 추가
            alloc[bestType]++;
            remain--;
        }

        // 최종 총 대기시간 계산
        int totalWait = 0;
        for (int i = 0; i < k; i++) {
            totalWait += waitTime(byType[i], alloc[i]);
        }

        return totalWait;
    }

    // 특정 유형의 총 대기시간 계산
    private int waitTime(List<int[]> requests, int num) {
        if (requests.isEmpty()) return 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < num; i++) pq.offer(0);

        int wait = 0;
        for (int[] r : requests) {
            int start = r[0], duration = r[1];
            int end = pq.poll();

            if (end <= start) {
                pq.offer(start + duration);
            } else {
                wait += (end - start);
                pq.offer(end + duration);
            }
        }
        return wait;
    }
}
