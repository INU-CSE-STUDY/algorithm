package programmers.lv2;

/* 프로그래머스 154540번 무인도 여행 문제

[문제 풀이]
X가 아닌 부분만 탐색
약간 단지번호 붙이기마냥 하는 건데,, 개수 세는게 아니고 써져있는 숫자 더하기 느낌??
BFS로 탐색해서 전체 합 구한다음에 List 정렬 후 반환만 하면 끝!

*/

import java.util.*;

class Q154540_kj {
    static int row, column;

    static char[][] map;
    static boolean[][] visited;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public int[] solution(String[] maps) {
        row = maps.length;
        column = maps[0].length();
        initMap(maps);

        List<Integer> days = new ArrayList<>();
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                if (isPossiblePosition(i, j) && !visited[i][j]) {
                    visited[i][j] = true;
                    int possibleDays = bfs(i, j);
                    days.add(possibleDays);
                }
            }
        }

        if (days.size() != 0) {
            // 각 섬에서 머무를 수 있는 일자를 오름차순으로 정렬 후 반환
            Collections.sort(days);
            return days.stream().mapToInt(i -> i).toArray();
        }

        // 지낼 수 있는 무인도가 없다면 -1 반환
        return new int[]{ -1 };
    }

    private int bfs(int x, int y) {
        int sum = Character.getNumericValue(map[x][y]);

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if (isPossiblePosition(nx, ny) && !visited[nx][ny]) {
                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                    sum += Character.getNumericValue(map[nx][ny]);
                }
            }
        }

        return sum;
    }

    private boolean isPossiblePosition(int x, int y) {
        // 바다 또는 경계를 넘는 곳은 갈 수 없음

        return map[x][y] != 'X' && map[x][y] != '\0';
    }

    private void initMap(String[] maps) {
        // 지도 초기화

        map = new char[row + 2][column + 2];
        visited = new boolean[row + 2][column + 2];

        for (int i = 0; i < row; i++) {
            String line = maps[i];
            for (int j = 0; j < column; j++) {
                map[i + 1][j + 1] = line.charAt(j);
            }
        }
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}