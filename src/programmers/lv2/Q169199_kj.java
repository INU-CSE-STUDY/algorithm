package programmers.lv2;

/* 프로그래머스 169199번 리코쳇 로봇 문제

[문제 풀이]
한 방향으로 이동하면 장애물 또는 게임판 가장자리까지 그 방향으로 쭈욱 감

일단 최단거리니까 BFS로 풀이!
- D 또는 맵 끝에 도달할 때 멈추기
- G 위치에 멈춰서면 이동횟수 리턴하기
>> 목표 위치에 도달할 수 없음은 어떻게 알 수 있을까?..
   - visited 배열을 관리해서 큐가 다 비었는데, 목적지 도달하지 못하면 return -1, 아니면 return cnt 인건가
*/

import java.util.*;

class Q169199_kj {
    static int rows, columns;
    static int startX, startY, endX, endY;
    static char[][] map;
    static boolean[][] visited;

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public int solution(String[] board) {
        rows = board.length;
        columns = board[0].length();
        initMap(board);

        return bfs(startX, startY);
    }

    private void initMap(String[] board) {
        map = new char[rows + 2][columns + 2];
        visited = new boolean[rows + 2][columns + 2];

        for (int i = 0; i < rows; i++) {
            String s = board[i];
            for (int j = 0; j < columns; j++) {
                char c = s.charAt(j);
                if (c == 'R') {
                    startX = i + 1;
                    startY = j + 1;
                } else if (c == 'G') {
                    endX = i + 1;
                    endY = j + 1;
                }

                map[i + 1][j + 1] = c;
            }
        }
    }

    private int bfs(int startX, int startY) {
        int moveCount = 0;

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY, moveCount));
        visited[startX][startY] = true;

        boolean isPossible = false;
        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                Point next = new Point(nextX, nextY, now.moveCount + 1);

                while (true) {
                    if (next.isCollision(map)) {
                        // 현재 위치가 가장자리 혹은 장애물이라면 그 전 좌표에서 멈춰서게 됨
                        next.x -= dx[i];
                        next.y -= dy[i];

                        if (!visited[next.x][next.y]) {
                            // 이미 방문했던 좌표면 방문하지 않도록 함(무한루프 방지)
                            queue.add(next);
                            visited[next.x][next.y] = true;
                        }

                        break; // 다음 탐색을 이어나가기 위해 break
                    } else {
                        // 가장자리 또는 장애물에 부딪히지 않는다면 계속해서 같은 방향으로 진행
                        next.x += dx[i];
                        next.y += dy[i];
                    }
                }

                if (next.isEndPoint(endX, endY)) {
                    // 현재 멈춘 위치가 원하는 종료 지점일 경우
                    isPossible = true;
                    moveCount = next.moveCount;

                    break;
                }
            }

            if (isPossible) break;
        }

        return isPossible ? moveCount : -1;
    }

    class Point {
        int x; // x 좌표
        int y; // y 좌표
        int moveCount; // 해당 도착하기 위해 이동한 횟수

        public Point(int x, int y, int moveCount) {
            this.x = x;
            this.y = y;
            this.moveCount = moveCount;
        }

        // 게임판 가장자리 또는 장애물에 부딪히는지 판단하기 위한 메서드
        public boolean isCollision(char[][] map) {
            return map[this.x][this.y] == '\0' || map[this.x][this.y] == 'D';
        }

        // 종료 지점과 일치하는지 판단하기 위한 메서드
        public boolean isEndPoint(int endX, int endY) {
            return (this.x == endX) && (this.y == endY);
        }
    }
}