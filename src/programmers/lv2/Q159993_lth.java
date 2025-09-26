package programmers.lv2;

/* 프로그래머스 159993번 미로 탈출 문제

[문제 풀이]
최단 시간 = BFS
bfs를 두 번 돌려서 시작 지점 -> 레버, 레버 -> 출구의 최단 시간을 구함
두 번 구하는 것이기에 visted 배열을 초기화해줘야 함
구한 두 값이 -1이 아니면 더해서 반환, 하나라도 -1이면 -1 반환

시작 지점의 point 객체를 먼저 찾고 그 객체를 bfs의 변수로 넘겨주고
레버의 point 객체를 찾고 그 객체를 bfs의 변수로 넘겨주면 bfs에서 한 번더 레버의 위치를 안 찾아도 되지만
코드가 더 길어지기에 그냥 bfs에서 시작 지점 찾는 걸로 통일
*/

import java.util.*;

class Q159993_lth {
    public int solution(String[] maps) {
        int answer = 0;
        int l = bfs(maps, 'S', 'L');
        int e = bfs(maps, 'L','E');
        answer += l;
        answer += e;
        if(l == -1 || e == -1){
            answer = -1;
        }
        
        return answer;
    }
    
    class Point{
        int x;
        int y;
        int t;
        public Point(int x, int y, int t){
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
    
    private int bfs(String[] maps, char start, char goal){
        int n = maps.length;
        int m = maps[0].length();
        
        // 시작 지점 찾기
        int sx = -1, sy = -1;
        for (int i = 0; i < n; i++) {
            int idx = maps[i].indexOf(start);
            if (idx != -1) {
                sx = i; sy = idx;
                break;
            }
        }
        
        boolean[][] visited = new boolean[n][m];
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(sx, sy, 0));
        visited[sx][sy] = true;
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        // BFS 탐색
        while (!q.isEmpty()) {
            Point cur = q.poll();
            // 레버 도달
            if (maps[cur.x].charAt(cur.y) == goal){
               return cur.t; 
            } 

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m){
                    if (!(maps[nx].charAt(ny) == 'X') && !visited[nx][ny]){
                        q.offer(new Point(nx, ny, cur.t+1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }
}