package programmers.lv2;

import java.util.*;

/**
 * DFS 탐색 + 그리디이다.
 * 그냥 기분이 더럽다. 엔터 여러개 치니깐 통과함
 * 프로그래머스는 각성해야한다. 엔터 개수에 따라 통과 실패가 말이되냐?
 */
public class Q250136_jhg {

    static int[][] visited;
    static int N;
    static int M;
    static int count;
    static int[][] visitedY;
    static int[] counts;
    static int total = 0;

    public int solution(int[][] land) {

        N = land.length;
        M = land[0].length;
        int[] answer = new int[M];

        visited = new int[N][M];
        visitedY = new int[12502][M];
        counts = new int[125002];

        Arrays.fill(answer, 0);

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], 0);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0 && land[i][j] == 1) {
                    count = 1;
                    visitedY[total][j] = 1;
                    visited[i][j] = 1;
                    dfs(land, new Pos(i, j));
                    counts[total] = count;
                    total++;
                }
            }
        }


        for (int i = 0; i < total; i++) {
            for (int j = 0; j < M; j++) {
                if (visitedY[i][j] == 1) {
                    answer[j] += counts[i];
                }
            }
        }

        return Arrays.stream(answer)
                .max()
                .orElse(0);
    }

    public void dfs(int[][] land, Pos now) {
        for (Pos d: Pos.D) {
            Pos nxt = now.move(d);
            if (nxt.isMove(N, M) && land[nxt.x][nxt.y] == 1) {
                visited[nxt.x][nxt.y] = 1;
                count++;
                visitedY[total][nxt.y] = 1;
                dfs(land, nxt);
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

        public Pos move(Pos pos) {
            return new Pos(pos.x + this.x, pos.y + this.y);
        }

        public boolean isMove(int n, int m) {
            return (0 <= x && x < n) && (0 <= y && y < m)
                    && visited[this.x][this.y] == 0;
        }
    }
}
