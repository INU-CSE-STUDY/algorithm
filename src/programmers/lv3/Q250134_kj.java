package programmers.lv3;

/* 프로그래머스 250134번 수레 움직이기 문제

[문제 풀이]
1 4
0 0
2 3

=> 빨간 수레는 1부터 3까지, 파란 수레는 2부터 4까지 움직이는게 목표
0은 빈칸이라 자유롭게 이동이 가능하지만, 5는 벽이어서 이동이 불가능

1. 둘이 같이 움직임 (2중 for문)
   - 이미 도착한 경우는 움직이지 않도록 하기
2. 둘이 같이 움직인 경우에 문제 조건에 위배되지 않는지 체크하고, 위배되지 않는 경우에만 이동할 수 있게 하기
   - 벽이나 격자 판 밖으로 움직일 수 없습니다.
   - 자신이 방문했던 칸으로 움직일 수 없습니다.
   - 동시에 두 수레를 같은 칸으로 움직일 수 없습니다.
   - 수레끼리 자리를 바꾸며 움직일 수 없습니다.
*/

import java.util.*;

class Q250134_kj {

    static final int[] dx = { 1, -1, 0, 0 };
    static final int[] dy = { 0, 0, -1, 1 };

    int row, column;
    int[] redEnd = new int[2];
    int[] blueEnd = new int[2];

    public int solution(int[][] maze) {
        row = maze.length;
        column = maze[0].length;

        int[] redStart = new int[2];
        int[] blueStart = new int[2];
        initPoints(maze, redStart, blueStart);

        return bfs(maze, redStart, blueStart);
    }

    private int bfs(int[][] maze, int[] redStart, int[] blueStart) {
        Queue<State> queue = new LinkedList<>();
        boolean[][] startRedPath = new boolean[row][column];
        boolean[][] startBluePath = new boolean[row][column];

        // 초기 상태를 큐에 추가하고 방문 처리
        State startState = new State(redStart[0], redStart[1], blueStart[0], blueStart[1], 0, startRedPath, startBluePath);
        queue.add(startState);

        startRedPath[redStart[0]][redStart[1]] = true;
        startBluePath[blueStart[0]][blueStart[1]] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // 현재 상태에서 각 수레의 도착 여부 확인 (변수명 변경 적용)
            boolean isRedArrived = (current.rx == redEnd[0] && current.ry == redEnd[1]);
            boolean isBlueArrived = (current.bx == blueEnd[0] && current.by == blueEnd[1]);

            // 종료 조건: 두 수레가 모두 도착했다면 현재 턴을 반환
            if (isRedArrived && isBlueArrived) {
                return current.turn;
            }

            // 다음 상태 탐색
            for (int rDir = 0; rDir < 4; rDir++) {
                for (int bDir = 0; bDir < 4; bDir++) {

                    // 현재 도착한 상태이면 움직이지 않고, 도착하지 않은 상태일 때만 이동
                    int nrx = isRedArrived ? current.rx : current.rx + dx[rDir];
                    int nry = isRedArrived ? current.ry : current.ry + dy[rDir];
                    int nbx = isBlueArrived ? current.bx : current.bx + dx[bDir];
                    int nby = isBlueArrived ? current.by : current.by + dy[bDir];

                    // 벽이나 격자 판 밖으로 움직인 경우
                    if (!isPossiblePosition(maze, nrx, nry) || !isPossiblePosition(maze, nbx, nby)) continue;

                    // 이미 방문한 칸인 경우
                    if (!isRedArrived && current.redVisitedPath[nrx][nry]) continue;
                    if (!isBlueArrived && current.blueVisitedPath[nbx][nby]) continue;

                    // 두 수레가 같은 자리에 방문한 경우
                    if (nrx == nbx && nry == nby) continue;

                    // 서로 자리를 바꾼 경우
                    if (nrx == current.bx && nry == current.by && nbx == current.rx && nby == current.ry) continue;

                    boolean[][] nextRedPath = copyArray(current.redVisitedPath);
                    boolean[][] nextBluePath = copyArray(current.blueVisitedPath);
                    nextRedPath[nrx][nry] = true;
                    nextBluePath[nbx][nby] = true;

                    queue.add(new State(nrx, nry, nbx, nby, current.turn + 1, nextRedPath, nextBluePath));
                }
            }
        }

        return 0;
    }

    private boolean isPossiblePosition(int[][] maze, int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < column && maze[x][y] != 5;
    }

    private void initPoints(int[][] maze, int[] redStart, int[] blueStart) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (maze[i][j] == 1) {
                    redStart[0] = i;
                    redStart[1] = j;
                } else if (maze[i][j] == 2) {
                    blueStart[0] = i;
                    blueStart[1] = j;
                } else if (maze[i][j] == 3) {
                    redEnd[0] = i;
                    redEnd[1] = j;
                } else if (maze[i][j] == 4) {
                    blueEnd[0] = i;
                    blueEnd[1] = j;
                }
            }
        }
    }

    private boolean[][] copyArray(boolean[][] original) {
        boolean[][] copy = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            copy[i] = Arrays.copyOf(original[i], column);
        }
        return copy;
    }
}

class State {
    int rx, ry; // 빨간 수레 좌표
    int bx, by; // 파란 수레 좌표
    int turn;   // 현재 턴 수
    boolean[][] redVisitedPath;
    boolean[][] blueVisitedPath;

    State(int rx, int ry, int bx, int by, int turn, boolean[][] redVisitedPath, boolean[][] blueVisitedPath) {
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.turn = turn;
        this.redVisitedPath = redVisitedPath;
        this.blueVisitedPath = blueVisitedPath;
    }
}