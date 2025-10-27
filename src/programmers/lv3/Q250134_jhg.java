package programmers.lv3;

import java.util.List;

// DFS이다.
public class Q250134_jhg {

    static final int RED_START = 1;
    static final int BLUE_START = 2;
    static final int RED_END = 3;
    static final int BLUE_END = 4;

    static int answer = Integer.MAX_VALUE;

    public int solution(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;

        boolean[][] redVisited = new boolean[n][m];
        boolean[][] blueVisited = new boolean[n][m];

        Pos red = null;
        Pos blue = null;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == RED_START) {
                    red = new Pos(i, j);
                }

                if (maze[i][j] == BLUE_START) {
                    blue = new Pos(i, j);
                }
            }
        }

        if (red == null || blue == null) {
            return 0;
        }

        red.visit(redVisited);
        blue.visit(blueVisited);

        dfs(red, blue, 0, n, m, maze, redVisited, blueVisited);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private void dfs(Pos nowRed, Pos nowBlue, int count, int n, int m, int[][] maze, boolean[][] redVisited, boolean[][] blueVisited) {
        // 종료되었는지 확인
        if (nowRed.isEnd(maze, RED_END) && nowBlue.isEnd(maze, BLUE_END)) {
            answer = Math.min(answer, count);
            return;
        }

        for (Pos redD: Pos.D) {
            for (Pos blueD: Pos.D) {
                // 뻘건 수레 이동 근데 만약 수레가 이미 도착했다면 STOP
                Pos nxtRed = nowRed.move(redD, maze, RED_END);
                // 퍼런 수레 이동 근데 만약 수레가 이미 도착했다면 STOP
                Pos nxtBlue = nowBlue.move(blueD, maze, BLUE_END);

                // 같은 자리로 움직인거면 PASS
                if (nxtRed.equals(nxtBlue)) {
                    continue;
                }

                // 자리 Change한 것도 PASS
                if (nxtRed.equals(nowBlue) && nxtBlue.equals(nowRed)) {
                    continue;
                }

                // 움직일 수 있는 지 확인
                if (nxtRed.isMove(n, m, maze, redVisited, RED_END) && nxtBlue.isMove(n, m, maze, blueVisited, BLUE_END)) {
                    nxtRed.visit(redVisited);
                    nxtBlue.visit(blueVisited);
                    dfs(nxtRed, nxtBlue, count + 1, n, m, maze, redVisited, blueVisited);
                    nxtRed.unVisit(redVisited);
                    nxtBlue.unVisit(blueVisited);
                }
            }
        }
    }

    static class Pos {
        int x;
        int y;

        static final List<Pos> D = List.of(
                new Pos(1, 0),
                new Pos(-1, 0),
                new Pos(0, 1),
                new Pos(0, -1)
        );

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos move(Pos d, int[][] maze, int endNumber) {
            if (this.isEnd(maze, endNumber)) {
                return new Pos(this.x, this.y);
            } else {
                return new Pos(this.x + d.x, this.y + d.y);
            }
        }

        public boolean isMove(int n, int m, int[][] maze, boolean[][] visited, int endNumber) {
            return (0 <= x && x < n) && (0 <= y && y < m)
                    && (maze[x][y] == endNumber || !visited[x][y])
                    && maze[x][y] != 5;
        }

        public void visit(boolean[][] visited) {
            visited[x][y] = true;
        }

        public void unVisit(boolean[][] visited) {
            visited[x][y] = false;
        }

        public boolean isEnd(int[][] maze, int endNumber) {
            return maze[x][y] == endNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x && y == pos.y;
        }
    }
}
