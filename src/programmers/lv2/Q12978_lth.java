package programmers.lv2;

/* 프로그래머스 12978번 배달 문제

[문제 풀이]
경로를 인접리스트로 저장하는데 양쪽 모두 움직일수 있으니 a->b, b->a를 모두 저장하고
경로마다 비용이 담긴 배열을 만들어서 최댓값으로 초기화 시킨다. 우선순위큐에 1을 넣고 갈수 있는 경로를 뽑아
그 경로로 가는 비용이 기존 비용보다 작다면 교체후 큐에 그 노드를 넣는다
큐가 빌때까지 반복한후 k보다 낮은 비용인 경로의 수를 반환

*/

import java.util.*;

class Q12978_lth {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        // 인접 리스트 생성
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // road -> 인접 리스트 변환
        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int edgeCost = r[2];

            graph.get(a).add(new int[]{b, edgeCost});
            graph.get(b).add(new int[]{a, edgeCost});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{1, 0});
        
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int curNode = temp[0];
            int curCost = temp[1];
            
            if (curCost > dist[curNode]) continue;
            
            for (int[] next : graph.get(curNode)) {
                int nextNode = next[0];
                int nextCost = next[1];
                
                if (dist[nextNode] > curCost + nextCost) {
                    dist[nextNode] = curCost + nextCost;
                    pq.offer(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) answer++;
        }

        return answer;
    }
}