package programmers.lv2;

import java.util.*;

/**
 * BFS 같아
 */
public class Q169199_jhg {


    public int solution(String[] board) {
        return bfs(board);
    }

    private int bfs(String[] board) {
        int n = board.length;
        int m = board[0].length();
        boolean[][] visited = new boolean[n][m];
        Pos start = findStartPos(board);
        Queue<Pos> queue = new LinkedList<>();

        start.visited(visited);

        queue.add(start);

        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            for (Pos d: Pos.getD()) {
                Pos nxt = new Pos(now);
                while (true) {
                    Pos t = nxt.move(d);

                    if (!t.isMove(n, m)) {
                        break;
                    }

                    if (t.isD(board)) {
                        break;
                    }

                    nxt = t;
                }

                if (!nxt.isVisited(visited)) {
                    nxt.visited(visited);
                    queue.add(nxt.count());
                }

                if (nxt.isEnd(board)) {
                    return nxt.getCount();
                }

            }
        }

        return -1;
    }


    private Pos findStartPos(String[] board) {
        for (int i = 0; i < board.length; i++) {
            String s = board[i];
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c == 'R') {
                    return new Pos(i, j);
                }
            }
        }

        return new Pos(-1, -1);
    }

    public static class Pos {
        int x;
        int y;
        int count;
        static final List<Pos> D = List.of(
                new Pos(1, 0),
                new Pos(-1, 0),
                new Pos(0, 1),
                new Pos(0, -1)
        );

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
            this.count = 1;
        }

        public Pos(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        public Pos(Pos now) {
            this.x = now.x;
            this.y = now.y;
            this.count = now.count;
        }

        public Pos move(Pos pos) {
            return new Pos(pos.x + this.x, pos.y + this.y, this.count);
        }

        public boolean isMove(int n, int m) {
            return (0 <= x && x < n) && (0 <= y && y < m);
        }

        public void visited(boolean[][] visited) {
            visited[x][y] = true;
        }

        public static List<Pos> getD() {
            return D;
        }

        public boolean isD(String[] board) {
            return 'D' == board[x].charAt(y);
        }

        public boolean isVisited(boolean[][] visited) {
            return visited[x][y];
        }

        public Pos count() {
            return new Pos(x, y, count + 1);
        }

        public boolean isEnd(String[] board) {
            return 'G' == board[x].charAt(y);
        }

        public int getCount() {
            return count;
        }
    }
}
