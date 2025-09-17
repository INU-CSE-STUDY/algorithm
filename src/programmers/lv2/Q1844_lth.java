package programmers.lv2;

/* 프로그래머스 1844번 게임 맵 최단거리 문제

[문제 풀이]
그냥 bfs로 풀면 된다
좌표와 거리를 담을 Point 클래스를 만들고 큐에 넣으면서 탐색
visited 배열을 만들어 방문한 곳은 다시 안가도록 처리
*/  


import java.util.*;

class Q1844_lth {
    public int solution(int[][] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;
        
        answer = dfs(maps, n, m);    
        
        return answer;
    }

    static class Point {
        int i;
        int j;
        int d;
        public Point(int i, int j, int d) {
            this.i = i;
            this.j = j;
            this.d = d;
        }
    }
    
    private int dfs(int[][] maps, int n, int m){
        boolean[][] visited = new boolean[n][m];
        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};
        
        Deque<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0, 1));
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            if (p.i == n - 1 && p.j == m - 1) return p.d;

            for (int i = 0; i < 4; i++) {
                int ni = p.i + di[i];
                int nj = p.j + dj[i];
                if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                    if (maps[ni][nj] == 1 && !visited[ni][nj]) {
                        q.offer(new Point(ni, nj, p.d+1));
                        visited[ni][nj] = true;
                    }
                }
            }
        }
        return -1;
    }
}