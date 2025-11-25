package programmers.lv2;

/* 프로그래머스 86052번 빛의 경로 사이클 문제

[문제 풀이]
사이클을 찾으려면 방문뿐만 아니라 방향까지 고려해야해서 visited를 3차원 배열로 선언해야하고
각 위치에서 4방향으로 빛이 들어오는 경우를 모두 탐색해야 한다.
이동 시 벽 밖으로 나가면 반대편에서 다시 들어오도록 모듈러 연산을 사용한다.
현재 위치의 문자에 따라 방향을 바꿔주도록 각 경우를 나누어 처리한다.

*/

import java.util.*;

class Q86052_lth {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    boolean[][][] visited;
    char[][] map;
    List<Integer> cycleLength;

    public int[] solution(String[] grid) {
        initMap(grid);
        cycleLength = new ArrayList<>();

        int n = map.length;
        int m = map[0].length;

        // 모든 (x, y, dir) 에 대해 사이클 탐색
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                for (int dir = 0; dir < 4; dir++) {
                    if (visited[x][y][dir]) continue; // 이미 사이클에 포함됨
                    int len = traceCycle(x, y, dir);
                    cycleLength.add(len);
                }
            }
        }

        // 이런 것도 있구나
        return cycleLength.stream()
                .mapToInt(Integer::intValue)
                .sorted()
                .toArray();
    }

    // 사이클 길이 측정
    private int traceCycle(int x, int y, int dir) {
        int length = 0;
        int n = map.length;
        int m = map[0].length;

        while (!visited[x][y][dir]) {
            visited[x][y][dir] = true;
            length++;
            
            // 현재 칸을 확인해 방향 회전
            dir = nextDirection(map[x][y], dir);
            
            // 다음 칸으로 이동 n을 더해줌으로써 음수 인덱스 방지
            // (현재 위치 + 이동량 + 음수 방지) % 격자 크기
            x = (x + dx[dir] + n) % n;
            y = (y + dy[dir] + m) % m;
        }

        return length;
    }

    // 회전 계산
    private int nextDirection(char ch, int dir) {
        if (ch == 'S') return dir;
        if (ch == 'L') { // 좌회전
            if (dir == 0) return 2; // 위 -> 왼
            if (dir == 1) return 3; // 아 -> 오
            if (dir == 2) return 1; // 왼 -> 아
            return 0;              // 오 -> 위
        } else { // 우회전
            if (dir == 0) return 3; // 위 -> 오
            if (dir == 1) return 2; // 아 -> 왼
            if (dir == 2) return 0; // 왼 -> 위
            return 1;              // 오 -> 아
        }
    }

    // 맵 초기화
    private void initMap(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();

        map = new char[n][m];
        visited = new boolean[n][m][4];

        for (int i = 0; i < n; i++) {
            map[i] = grid[i].toCharArray();
        }
    }
}
