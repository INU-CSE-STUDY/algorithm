package programmers.lv2;

import java.util.*;

// 흔하디 흔한 BFS 문제이다.
public class Q154540_jhg {

    public int[] solution(String[] maps) {
        int n = maps.length;
        int m = maps[0].length();

        List<Integer> answer = new ArrayList<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Pos now = new Pos(i, j);
                if (now.isMove(n, m, visited, maps)) {
                    visited[i][j] = true;
                    int possibleDays = bfs(now, maps, visited);
                    answer.add(possibleDays);
                }
            }
        }

        if (answer.isEmpty()) {
            answer.add(-1);
        }

        return answer.stream().mapToInt(i -> i)
                .sorted()
                .toArray();
    }

    private int bfs(Pos start, String[] maps, boolean[][] visited) {
        Queue<Pos> queue = new LinkedList<>();
        int result = Character.getNumericValue(maps[start.x].charAt(start.y));;

        int n = maps.length;
        int m = maps[0].length();

        queue.add(start);

        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            for (Pos d: Pos.D) {
                Pos nxt = now.move(d);

                if (nxt.isMove(n, m, visited, maps)) {
                    queue.add(nxt);
                    visited[nxt.x][nxt.y] = true;
                    result += Character.getNumericValue(maps[nxt.x].charAt(nxt.y));
                }
            }
        }

        return result;
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

        public Pos move(Pos pos) {
            return new Pos(pos.x + this.x, pos.y + this.y);
        }

        public boolean isMove(int n, int m, boolean[][] visited, String[] maps) {
            return (0 <= x && x < n) && (0 <= y && y < m)
                    && maps[x].charAt(y) != 'X'
                    && !visited[this.x][this.y];
        }
    }
}
