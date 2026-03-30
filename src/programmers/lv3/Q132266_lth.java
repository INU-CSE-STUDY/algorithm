package programmers.lv3;

/* 프로그래머스 132266번 부대 복귀 문제

[문제 풀이]
각 노드들이 간선으로 연결되어 있으니 리스트로 해줘야하는데 인접리스트를 사용해서 연결관계를 저장하고
양방향이라 1,2라면 1->2와 2->1을 모두 저장을 해야한다. 배열을 만들어서 목적지와의 거리를 저장하게 하고
마지막 시작지점부터 큐가 빌때까지 bfs를 해준다음 sources에 있는 노드의 거리를 반환한다 

*/

import java.util.*;

class Q132266_lth {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        List<List<Integer>> graph = new ArrayList<>();

        for(int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        for(int[] r : roads){
            graph.get(r[0]).add(r[1]);
            graph.get(r[1]).add(r[0]);
        }
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(destination);
        // 시작지점 0 초기화
        dist[destination] = 0;
        
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next : graph.get(cur)){
                if(dist[next] == -1){
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                }
            }
        }
        
        for(int i = 0; i < sources.length; i++){
            answer[i] = dist[sources[i]];
        }
        
        return answer;
    }
}