package programmers.lv2;

/* 프로그래머스 42583번 다리를 지나는 트럭 문제

[문제 풀이]
큐에 무게와 나가는 시각을 저장하면서 한 개를 올릴때마다 1초를 보낸다
트럭이 더 이상 못 올라갈 경우 큐에서 트럭을 빼며 나가는 트럭의 시각으로 점프
전부 큐에 올라간다면 큐를 전부 비우며 마지막 나가는 놈의 시각이 정답

*/

import java.util.*;

class Q42583_lth {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int total = 0;   // 다리 위 총 무게
        int time = 1;    // 현재 시각

        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < truck_weights.length; i++) {
            int tw = truck_weights[i];

            // 현재 time 기준으로 이미 나간 트럭 제거
            while (!q.isEmpty() && q.peek()[1] <= time) {
                int[] cur = q.poll();
                total -= cur[0];
            }

            // 못 올리면 가장 먼저 나가는 트럭 시각으로 time 점프 후 제거 반복
            while (total + tw > weight) {
                int[] cur = q.poll();
                time = cur[1];                     // 그 트럭이 나가는 시각으로 점프
                total -= cur[0];
            }

            q.offer(new int[]{tw, time + bridge_length});
            total += tw;
            // 같은 시간에 1대만 가능하기에 time++
            time++;
        }

        // 마지막 트럭이 나가는 시간 = 큐의 마지막 exitTime
        int lastExit = time;
        while (!q.isEmpty()) {
            lastExit = Math.max(lastExit, q.poll()[1]);
        }
        return lastExit;
    }
}