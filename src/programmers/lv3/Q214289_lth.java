package programmers.lv3;

/* 프로그래머스 214289번 에어컨 문제

[문제 풀이]
pq없이 queue를 이용한 bfs로 풀었지만 시간초과가 나서 pq로 바꿔서 다익스트라로 풀었다
-> a, b, 0의 비용이 다르기 때문에 pq로 최소비용을 우선으로 탐색해야 한다.
*/

import java.util.*;

class Q214289_lth {
    // 우선순위 큐를 이용한 다익스트라 풀이
    static class State {
        int time;
        int temp;
        int cost;
        // time: 몇 분째인지, temp: 현재 온도, cost: 지금까지 소비한 전력량
        State(int time, int temp, int cost) {
            this.time = time;
            this.temp = temp;
            this.cost = cost;
        }
    }

    static final int INF = 1_000_000_000;

    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int n = onboard.length;
        int offset = 10;
        int maxTemp = 60;

        // dist[time][temp] = time 분에 temp 온도가 되는데 필요한 최소 전력량
        int[][] dist = new int[n][maxTemp + 1];
        // 배열 초기화
        for (int[] row : dist) Arrays.fill(row, INF);

        // 비용(cost)이 작은 순서(오름차순)로 정렬
        PriorityQueue<State> pq = new PriorityQueue<>((s1, s2) -> s1.cost - s2.cost);

        // 초기 상태 설정
        pq.offer(new State(0, temperature, 0));
        // 시작 온도 = 실외 온도
        dist[0][temperature + offset] = 0;

        // 다익스트라 알고리즘
        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int time = cur.time;
            int temp = cur.temp;
            int cost = cur.cost;

            // 마지막 시간인 경우 종료
            if (time == n - 1) continue;
            // 이미 더 적은 비용으로 도달한 상태인 경우 무시
            if (dist[time][temp + offset] < cost) continue;

            // 다음 시간
            int nextTime = time + 1;

            // 다음 가능한 온도 상태 (유지, +1, -1, 끔)
            int[][] moves = {
                {temp, cost + b},
                {temp + 1, cost + a},
                {temp - 1, cost + a},
                {natural(temp, temperature), cost}
            };

            for (int[] move : moves) {
                int nextTemp = move[0];
                int nextCost = move[1];
                
                // 온도 범위 벗어나거나 승객 탑승 중 쾌적 온도 범위 벗어나면 무시
                if (nextTemp < -10 || nextTemp > 50) continue;
                // 승객 탑승 중 쾌적 온도 범위 벗어나면 무시
                if (onboard[nextTime] == 1 && (nextTemp < t1 || nextTemp > t2)) continue;

                // 상태 업데이트
                int idx = nextTemp + offset;
                // 더 적은 비용으로 도달한 경우에만 갱신
                if (nextCost < dist[nextTime][idx]) {
                    dist[nextTime][idx] = nextCost;
                    pq.offer(new State(nextTime, nextTemp, nextCost));
                }
            }
        }

        // 마지막 시간에 도달할 수 있는 최소 전력량 계산
        int answer = INF;
        for (int t = 0; t <= maxTemp; t++) {
            answer = Math.min(answer, dist[n - 1][t]);
        }
        return answer;
    }

    // 에어컨을 끈 상태에서 자연스럽게 변하는 온도 계산
    private int natural(int temp, int outdoor) {
        if (temp < outdoor) return temp + 1;
        if (temp > outdoor) return temp - 1;
        return temp;
    }

}
