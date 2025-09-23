package programmers.lv2;

/* 프로그래머스 169199번 리코쳇 로봇 문제

[문제 풀이]
저번에 풀었던 bfs 문제 그대로 객체로 만든 후 횟수세기, D를 만나거나 주어진 배열의 크기를 넘어가면 멈춤
G를 만날때까지 반복
*/

import java.util.*;

class Q169199_lth {
    public int solution(String[] board) {
        int answer = 0;
        answer = bfs(board);
        
        return answer;
    }
    
    class Route{
        int count;
        int x;
        int y;
        public Route(int x, int y, int count){
            this.count = count;
            this.x = x;
            this.y = y;
        }
    }
    
    private int bfs(String[] board){
        int n = board.length;
        int m = board[0].length();
        
        // 시작 지점 찾기
        int sx = -1, sy = -1;
        for (int i = 0; i < n; i++) {
            int idx = board[i].indexOf('R');
            if (idx != -1) {
                sx = i; sy = idx;
                break;
            }
        }
        

        boolean[][] visited = new boolean[n][m];
        Queue<Route> q = new ArrayDeque<>();
        q.offer(new Route(sx, sy, 0));
        visited[sx][sy] = true;


        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // BFS 탐색
        while (!q.isEmpty()) {
            Route cur = q.poll();
            // 목표 지점(G) 도달
            if (board[cur.x].charAt(cur.y) == 'G'){
               return cur.count; 
            } 

            for (int d = 0; d < 4; d++) {
                int nx = cur.x;
                int ny = cur.y;

                // 배열 밖이거나 'D'를 만나기 전까지 계속 이동
                while (true) {
                    int tx = nx + dx[d];
                    int ty = ny + dy[d];
                    if (tx < 0 || tx >= n || ty < 0 || ty >= m) break;      // 배열 밖
                    if (board[tx].charAt(ty) == 'D') break;            // 'D'를 만남
                    nx = tx; ny = ty;
                }

                // 멈춘 칸이 새롭다면 큐에 추가
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Route(nx, ny, cur.count + 1));
                }
            }
        }
        return -1;
    }
}