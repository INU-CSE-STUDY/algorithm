package programmers.lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 풀이가 있나 싶다..
 * 그냥 구현 + 탐색 문제이다 여기서 DFS, BFS는 취향 차이로 생각하자.
 * 지게차일때는 DFS, BFS로 외곽 탐색를 활용해 처리
 * 크레인일때는 전체 탐색으로 처리
 */

public class Q388353_jhg {

    static int N;
    static int M;
    static boolean[][] visited;
    static char[][] board;
    static int answer;
    static List<Pos> positions;

    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        answer = N*M;
        N += 2; M += 2;
        visited = new boolean[N][M];
        board = new char[N][M];

        setMap(storage);

        for (String request : requests) {
            char name = request.charAt(0);

            if (request.length() == 1) {
                lift(name);
            } else {
                crane(name);
            }
        }

        return answer;
    }

    private void lift(char name) {

        visited = new boolean[N][M];

        visited[0][0] = true;

        positions = new ArrayList<>();
        dfs(new Pos(0, 0), name);

        for (Pos pos : positions) {
            answer--;
            board[pos.x][pos.y] = ' ';
        }
    }


    public void dfs(Pos now, char name) {
        for (Pos d: Pos.D) {
            Pos nxt = now.move(d);
            if (nxt.isMove(N, M)) {
                visited[nxt.x][nxt.y] = true;
                if (board[nxt.x][nxt.y] == name) {
                    positions.add(nxt);
                } else if (board[nxt.x][nxt.y] == ' ') {
                    dfs(nxt, name);
                }
            }
        }
    }

    private void setMap(String[] storage) {
        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], ' ');
        }

        for (int i = 1; i < N-1; i++) {
            String str = storage[i-1];
            for (int j = 1; j < M-1; j++) {
                board[i][j] = str.charAt(j-1);
            }
        }
    }

    private void crane(char name) {
        for (int i = 1; i < N-1; i++) {
            for (int j = 1; j < M-1; j++) {
                if (board[i][j] == name) {
                    answer--;
                    board[i][j] = ' ';
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

        public Pos move(Pos pos) {
            return new Pos(pos.x + this.x, pos.y + this.y);
        }

        public boolean isMove(int n, int m) {
            return (0 <= x && x < n) && (0 <= y && y < m) && !visited[this.x][this.y];
        }
    }
}
