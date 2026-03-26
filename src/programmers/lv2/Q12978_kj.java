package programmers.lv2;

/* 프로그래머스 12978번 배달 문제

[문제 풀이]
1번 마을에서 각 마을까지 걸리는 시간을 다 구해야 하네 결국엔
다익스트라구만

*/

import java.util.*;

class Q12978_kj {

    static final int INF = Integer.MAX_VALUE;

    public int solution(int N, int[][] road, int K) {

        int[][] graph = makeGraph(N, road);
        int[] dist = dijkstra(N, 1, graph);

        int answer = 0;
        for (int d : dist) {
            if (d <= K) answer++;
        }

        return answer;
    }

    private int[][] makeGraph(int N, int[][] road) {
        int[][] graph = new int[N + 1][N + 1];
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int[] roadInfo : road) {

            int a = roadInfo[0]; // a 마을
            int b = roadInfo[1]; // b 마을
            int c = roadInfo[2]; // 도로를 지나는데 걸리는 시간

            graph[a][b] = Math.min(graph[a][b], c);
            graph[b][a] = graph[a][b]; // 양방향으로 이동 가능하니까 업데이트
        }

        return graph;
    }

    private int[] dijkstra(int N, int start, int[][] graph) {

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {

            int now = pq.poll().index;

            for (int next = 1; next < graph[now].length; next++) {

                int cost = graph[now][next];
                if (cost == INF) continue;

                if (dist[next] > dist[now] + cost) {
                    dist[next] = dist[now] + cost;
                    pq.offer(new Node(next, dist[next]));
                }
            }
        }

        return dist;
    }

    class Node {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
}
