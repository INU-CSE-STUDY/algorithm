package programmers.lv2;

/* 프로그래머스 159993번 미로 탈출 문제

[문제 풀이]
최대한 빠르게 미로를 빠져나가기 -> BFS

[시작 지점 -> (출구) -> 레버 -> 출구] 순으로 방문
1. [시작 지점 -> 레버]로 가는 최단거리 구하기
2. [레버 -> 출구]로 가는 최단거리 구하기
   => 두 가지 경우 모두 최단거리여야 하니까, 각각 구할 때 visited 배열을 초기화해줘야 함
*/

import java.util.*;

class Q159993_kj {
    static int n, m; // 미로의 가로, 세로 길이
    static char[][] maze; // 미로 정보를 담은 char 배열
    static boolean[][] visited; // 방문 정보를 관리하는 visited 배열

    static Point start; // 시작 지점 좌표
    static Point lever; // 레버 좌표
    static Point exit; // 출구 좌표

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public int solution(String[] maps) {
        initMaze(maps);

        return bfs();
    }

    private void initMaze(String[] maps) {
        n = maps.length;
        m = maps[0].length();

        maze = new char[n + 2][m + 2];

        for (int i = 0; i < n; i++) {
            String line = maps[i];

            for (int j = 0; j < m; j++) {
                char c = line.charAt(j);

                maze[i + 1][j + 1] = c;
                if (c == 'S') {
                    start = new Point(i + 1, j + 1, 0);
                } else if (c == 'E') {
                    exit = new Point(i + 1, j + 1, 0);
                } else if (c == 'L') {
                    lever = new Point(i + 1, j + 1, 0);
                }
            }
        }
    }

    private int bfs() {
        int distance = 0;

        // 시작 지점에서 레버까지 가는 최단거리 구하기
        Queue<Point> startToLeverQueue = new LinkedList<>();
        startToLeverQueue.offer(start);
        visited = new boolean[n + 2][m + 2];

        boolean isPossible = false;
        while (!startToLeverQueue.isEmpty()) {
            Point now = startToLeverQueue.poll();
            visited[now.x][now.y] = true;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                Point next = new Point(nextX, nextY, now.distance);

                if (next.isPossiblePosition(maze) && !visited[next.x][next.y]) {
                    visited[next.x][next.y] = true;
                    startToLeverQueue.add(next);
                    next.distance++;
                }

                if (next.isLeverPosition(lever)) {
                    isPossible = true;
                    distance += next.distance;
                    break;
                }
            }

            if (isPossible) break;
        }

        // 시작 지점에서 레버로 도달할 수 없는 경우 -1 반환
        if (!isPossible) return -1;

        // 레버에서 출구까지 가는 최단거리 구하기
        Queue<Point> leverToExitQueue = new LinkedList<>();
        leverToExitQueue.offer(lever);
        visited = new boolean[n + 2][m + 2];

        isPossible = false;
        while (!leverToExitQueue.isEmpty()) {
            Point now = leverToExitQueue.poll();
            visited[now.x][now.y] = true;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                Point next = new Point(nextX, nextY, now.distance);

                if (next.isPossiblePosition(maze) && !visited[next.x][next.y]) {
                    visited[next.x][next.y] = true;
                    leverToExitQueue.add(next);
                    next.distance++;
                }

                if (next.isExitPosition(exit)) {
                    isPossible = true;
                    distance += next.distance;
                    break;
                }
            }

            if (isPossible) break;
        }

        // 레버에서 출구로 도달할 수 없는 경우 -1 반환
        if (!isPossible) return -1;

        // 정상적으로 도달가능한 경우, 거리를 반환
        return distance;
    }
}

class Point {
    int x;
    int y;
    int distance;

    public Point(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public boolean isPossiblePosition(char[][] maze) {

        // 미로의 바깥으로 넘어간 경우
        if (maze[this.x][this.y] == '\0') return false;

        // 미로의 벽인 경우
        if (maze[this.x][this.y] == 'X') return false;

        return true;
    }

    public boolean isLeverPosition(Point lever) {
        return this.x == lever.x && this.y == lever.y;
    }

    public boolean isExitPosition(Point exit) {
        return this.x == exit.x && this.y == exit.y;
    }
}