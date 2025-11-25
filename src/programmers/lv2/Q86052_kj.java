package programmers.lv2;

/* 프로그래머스 86052번 빛의 경로 사이클 문제

[문제 풀이]
문제 이해 자체를 잘못 했었다..
사이클이란 말 자체가 그냥 시작 위치로 돌아온다 << 의 뜻이 아니라 진짜로 그 길이만큼 무한 반복되는 사이클을 찾는 거였구나..

기존에 생각했던 visited 배열은 그냥 r, c만 관리해서 자기 자신으로 돌아오는가? 이거만 생각할 수 있었는데
visited 배열을 3차원으로 확장해서 r, c 뿐만 아니라 빛이 들어오는 방향까지 고려하는 거로 변경
이렇게 하면 이미 방문한 곳(격자 위치 뿐 아니라 빛의 방향까지)에 또 도착했을 때가 완벽하게 사이클이 이루어지는 그 지점이 되는 거니까!
이렇게 구한 다음에, 방문하지 않은 곳 기준으로 다시 들어가서 다시 뺑뺑이 돌아보고.. 그런 식으로 하면 된다!

*/

import java.util.*;

class Q86052_kj {

    int row, column; // 격자의 행과 열 크기

    static final int[] dRow = { 1, 0, -1, 0 };
    static final int[] dColumn = { 0, 1, 0, -1 };

    char[][] map; // 격자 정보를 저장할 char[][] 배열
    boolean[][][] visited; // 방문 정보를 저장할 boolean[][] 배열
    List<Integer> cycleLengthList; // 사이클 길이를 저장할 list
    int cycleLength = 0;

    public int[] solution(String[] grid) {

        initMap(grid);
        cycleLengthList = new ArrayList<>();

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                for (int dir = 0; dir < 4; dir++) {

                    if (visited[r][c][dir]) continue;

                    visited[r][c][dir] = true;
                    Point startPoint = new Point(r, c, dir);
                    Point nextPoint = startPoint.getNextPoint();

                    cycleLength = 1;
                    getCycleLength(nextPoint);
                    cycleLengthList.add(cycleLength);
                }
            }
        }

        return cycleLengthList.stream()
                .mapToInt(Integer::intValue)
                .sorted()
                .toArray();
    }

    private void getCycleLength(Point now) {

        int r = now.r;
        int c = now.c;
        int dir = now.dir;

        while (!visited[r][c][dir]) {

            visited[r][c][dir] = true;
            cycleLength++;

            Point next = now.getNextPoint();

            r = next.r;
            c = next.c;
            dir = next.dir;
            now = next;
        }
    }

    private void initMap(String[] grid) {

        row = grid.length;
        column = grid[0].length();

        map = new char[row][column];
        visited = new boolean[row][column][4];

        for (int i = 0; i < row; i++) {

            String line = grid[i];

            for (int j = 0; j < column; j++) {
                map[i][j] = line.charAt(j);
            }
        }
    }

    class Point {
        int r;
        int c;
        int dir;

        public Point(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        public Point getNextPoint() {
            int nextDir = getDirectionIdx(map[this.r][this.c], this.dir);

            int nextR = r + dRow[nextDir];
            int nextC = c + dColumn[nextDir];

            if (isImpossiblePosition(nextR, nextC)) {

                if (nextR < 0) {
                    nextR = row - 1;
                }

                if (nextR == row) {
                    nextR = 0;
                }

                if (nextC < 0) {
                    nextC = column - 1;
                }

                if (nextC == column) {
                    nextC = 0;
                }
            }

            return new Point(nextR, nextC, nextDir);
        }

        private boolean isImpossiblePosition(int r, int c) {
            // 격자 밖을 넘어가는 경우는 불가능한 위치 = 반대편으로 넘어가야 함
            return !((0 <= r && r < row) && (0 <= c && c < column));
        }

        private int getDirectionIdx(char command, int prevDirectionIdx) {
            int directionIdx = -1;

            // S E N W

            if (command == 'S') {
                // S일 경우 직진이므로 방향 전환할 필요가 없음
                directionIdx = prevDirectionIdx;
            } else if (command == 'L') {
                // L일 경우 좌회전
                directionIdx = (prevDirectionIdx + 1) % 4;
            } else if (command == 'R') {
                // R일 경우 우회전
                directionIdx = (prevDirectionIdx + 3) % 4;
            }

            return directionIdx;
        }
    }
}