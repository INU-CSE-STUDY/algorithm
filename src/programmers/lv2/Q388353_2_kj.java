package programmers.lv2;

/* 프로그래머스 388353번 지게차와 크레인 문제

외곽 처리를 if문 분기로 하는 것이 아닌, 원래 배열을 감싸는 하나의 외곽을 추가하는 방식으로 변경
char[][] 배열의 빈 공간에는 '/0' 문자가 들어가는 것을 이용해, 해당 문자열을 만났을 때만 dfs로 탐색할 수 있게 변경
- dfs 탐색에서 내가 찾는 컨테이너 이름이 발견되면 해당 컨테이너 좌표를 따로 저장(마지막에 한번에 처리하기 위함)
- dfs 탐색에서 ' '를 만났을 경우, 빈공간으로 간주해 dfs 탐색 추가 진행

크레인 이용시에는 원래 배열 부분만 탐색해 기존과 동일하게 진행
*/
import java.util.*;

class Q388353_2_kj {
    static int N, M;
    static char[][] map;

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static boolean[][] visited;

    static Queue<int[]> pos; // 출고한 컨테이너의 위치를 저장해둘 queue

    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        int total = N * M; // 전체 컨테이너의 크기

        map = new char[N + 2][M + 2];
        initMap(storage);

        int removeContainer = 0; // 꺼내어진 컨테이너의 개수
        for (String cmd : requests) {
            char containerName = cmd.charAt(0);

            if (cmd.length() == 1) {
                // 길이가 1이면 지게차를 이용한 출고 요청
                pos = new LinkedList<>();
                removeContainer += forkLift(containerName);
            } else {
                // 길이가 2이면 크레인을 이용한 출고 요청
                removeContainer += crane(containerName);
            }
        }

        return total - removeContainer;
    }

    // 입력받은 String[] 배열을 2차원 배열로 변경
    private static void initMap(String[] storage) {
        for (int i = 1; i <= N; i++) {
            String str = storage[i - 1];
            for (int j = 1; j <= M; j++) {
                map[i][j] = str.charAt(j - 1);
            }
        }
    }

    // 지게차 이용
    private static int forkLift(char name) {
        int count = 0;

        // 한번의 탐색 동안만 사용할 방문 배열이므로, 입력이 들어올 때마다 초기화 필요
        visited = new boolean[N + 2][M + 2];
        for (int i = 0; i < N + 2; i++) {
            for (int j = 0; j < M + 2; j++) {
                // 외곽 부분일 경우만 탐색 진행(char[][] 배열의 빈 공간은 '/0' 문자가 들어있음)
                if (!visited[i][j] && map[i][j] == '\0') {
                    dfs(i, j, name);
                }
            }
        }

        while (!pos.isEmpty()) {
            int[] position = pos.poll();
            int x = position[0];
            int y = position[1];

            map[x][y] = ' ';
            count++;
        }

        return count;
    }

    private static void dfs(int x, int y, char name) {
        visited[x][y] = true; // 방문처리

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isPossiblePosition(nextX, nextY) && !visited[nextX][nextY]) {
                if (map[nextX][nextY] == name) {
                    pos.add(new int[]{ nextX, nextY });
                    // 방문 처리를 통해 같은 칸 방문하지 못하도록 처리
                    visited[nextX][nextY] = true;
                } else if (map[nextX][nextY] == ' ') {
                    dfs(nextX, nextY, name);
                }
            }
        }
    }

    // 크레인 이용
    private static int crane(char name) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] == name) {
                    count++;
                    map[i][j] = ' ';
                }
            }
        }
        return count;
    }

    // DFS 탐색 시 범위를 벗어나면 안됨
    private static boolean isPossiblePosition(int x, int y) {
        return (1 <= x && x <= N) && (1 <= y && y <= M);
    }
}