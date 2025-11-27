package programmers.lv2;

/* 프로그래머스 81302번 거리두기 확인하기 문제

[문제 풀이]
2칸까지만 DFS로 탐색하면서 거리두기 위반 여부 체크
1. 응시자 위치(P)를 찾으면, 그 위치에서 상하좌우로 DFS 탐색 시작
2. 깊이가 2를 넘지 않는 선에서 탐색하면서 다른 응시자 위치(P)를 찾으면 거리두기 위반 여부 체크
  1) 맨해튼 거리가 1이면 무조건 위반
  2) 맨해튼 거리가 2이면 파티션(X)으로 막혀있는지 체크
     - 직선인 경우 중간에 파티션이 있는지
     - 대각선인 경우 둘 다 파티션이 있는지

*/

import java.util.*;

class Q81302_lth {

    // 상하좌우
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < 5; i++) {
            char[][] map = new char[5][5];

            for (int j = 0; j < 5; j++) {
                map[j] = places[i][j].toCharArray();
            }

            // 거리두기 위반 여부 체크
            if (isValid(map)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    // 대기실 검사
    boolean isValid(char[][] map) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (map[x][y] == 'P') {
                    boolean[][] visited = new boolean[5][5];
                    // DFS 깊이 2까지 탐색
                    if (!dfs(x, y, x, y, 0, map, visited)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // DFS
    boolean dfs(int sx, int sy, int x, int y, int depth, char[][] map, boolean[][] visited) {
        if (depth > 2) return true;  // 2까지만 탐색
        visited[x][y] = true;
        // depth >= 1 이고 만약 P를 찾았다면 규칙 체크
        if (depth > 0 && map[x][y] == 'P') {
            if (!check(sx, sy, x, y, map)) {
                return false; // 거리두기 위반
            }
        }

        // 계속 탐색
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
            if (visited[nx][ny]) continue;

            // X는 막힌 벽이므로 더 탐색 X
            if (map[nx][ny] == 'X') continue;

            if (!dfs(sx, sy, nx, ny, depth + 1, map, visited)) {
                return false;
            }
        }

        return true;
    }

    // 두 P(sx,sy)와 (x,y) 사이 거리 규칙 체크
    boolean check(int sx, int sy, int x, int y, char[][] map) {
        int dist = Math.abs(sx - x) + Math.abs(sy - y);

        // 거리 1이면 무조건 위반
        if (dist == 1) return false;

        // 거리 2일 때
        if (dist == 2) {

            // 직선
            if (sx == x) {
                int mid = (sy + y) / 2;
                return map[sx][mid] == 'X';
            }
            if (sy == y) {
                int mid = (sx + x) / 2;
                return map[mid][sy] == 'X';
            }

            // 대각선일 경우 (둘 중 하나라도 X가 아니면 위반)
            return map[sx][y] == 'X' && map[x][sy] == 'X';
        }

        return true;
    }
}
