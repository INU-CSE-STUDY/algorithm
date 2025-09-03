package programmers.lv2;/* 프로그래머스 388353번 지게차와 크레인 문제

[문제 내용]
- n * m 크기의 물류창고 (세로 n줄, 가로 m줄)
- 지게차로 창고에서 접근가능한 해당 종류의 컨테이너를 모두 꺼냄
  접근이 가능한 컨테이너란 4면 중 적어도 1명이 창고 외부와 연결된 컨테이너(동서남북 4방향)
- 창고 외부와 연결되지 않은 컨테이너도 꺼낼 수 있도록 크레인을 도입
  크레인을 사용하면 요청된 종류의 모든 컨테이너를 꺼냄
- 출고 요청
  알파벳 하나 -> 지게차를 사용해 출고 요청이 들어온 순간 접근 가능한 컨테이너를 꺼냄 (ex. A)
  알파벳 두번 반복 -> 크레인을 사용해 요청된 종류의 모든 컨테이너를 꺼냄 (ex. BB)

[제약 조건]
- 2 <= n(= storage.length) <= 50
- 2 <= m(= storage[i].length()) <= 50
  최대 50 * 50 크기
- 1 <= requests.length <= 100
- 1 <= requests[i].length() <= 2
  길이가 1이면 지체가, 길이가 2이면 크레인 요청

[문제 풀이]
1. 전체 컨테이너를 2차원 배열로 변경
2. 들어온 요청을 지게차와 크레인으로 구분
- *지게차일 경우, 탐색을 통해 접근가능한 컨테이너만 출고
  -> 출고된 컨테이너의 위치는 따로 저장해놨다가 모든 순회가 끝나면 따로 공백 처리를 해야 하는 것으로 보임
     (ex. 입출력 예 #1에서 2행 2열의 A 컨테이너만 접근이 가능하고, 2행 3열의 A 컨테이너는 접근이 불가능했음)
- 크레인일 경우, 같은 이름을 가진 컨테이너 모두 출고
  -> 단순히 전체 탐색해서 같은 이름을 가진 컨테이너 모두 공백으로 변경하며 개수 세기
3. 전체 컨테이너 개수 - 제거된 컨테이너 개수 반환 반환(남은 컨테이너의 수를 반환)

*지게차일 경우
1. 외곽만 순회
- 이미 출고되어 빈 공간이 있을 경우만 그 내부 탐색
2. 접근가능한 컨테이너이면서 내가 찾는 컨테이너와 동일한 이름일 경우 출고
- 출고된 컨테이너 위치는 공백으로 변경
- 만난 당시에 변경하면 그 이후 탐색 때 잘못될 가능성이 있으므로, queue에 좌표를 따로 저장해놨다가 탐색이 끝나면 일괄 업데이트
*/
import java.util.*;

class Q388353_kj {
    static int N, M;
    static char[][] map;

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static boolean[][] visited;

    static Set<String> pos; // 출고한 컨테이너의 위치를 저장해둘 queue

    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        int total = N * M; // 전체 컨테이너의 크기

        map = new char[N][M];
        initMap(storage);

        int removeContainer = 0; // 꺼내어진 컨테이너의 개수
        for (int i = 0; i < requests.length; i++) {
            String cmd = requests[i];
            char containerName = cmd.charAt(0);

            if (cmd.length() == 1) {
                // 길이가 1이면 지게차를 이용한 출고 요청
                pos = new HashSet<>();
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
        for (int i = 0; i < N; i++) {
            String str = storage[i];
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }
    }

    // 지게차 이용
    private static int forkLift(char name) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited = new boolean[N][M];

                if (i == 0 || i == N - 1) {
                    // 가장 위 또는 아래일 경우 해당 행 전체 탐색 필요
                    if (map[i][j] == name) {
                        pos.add(i + " " + j);
                    } else if (map[i][j] == ' ') {
                        dfs(i, j, name);
                    }
                } else {
                    // 가장 위 또는 아래가 아닐 경우는 가장 왼쪽 또는 오른쪽만 탐색 필요
                    if (j == 0 || j == M - 1) {
                        if (map[i][j] == name) {
                            pos.add(i + " " + j);
                        } else if (map[i][j] == ' ') {
                            dfs(i, j, name);
                        }
                    }
                }
            }
        }

        // 탐색이 종료되면 발견한 컨테이너를 공백으로 변경
        for (String s : pos) {
            String[] part = s.split(" ");
            int x = Integer.parseInt(part[0]);
            int y = Integer.parseInt(part[1]);
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
                    pos.add(nextX + " " + nextY);
                } else if (map[nextX][nextY] == ' ') {
                    dfs(nextX, nextY, name);
                }
            }
        }
    }

    // 크레인 이용
    private static int crane(char name) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == name) {
                    count++;
                    map[i][j] = ' ';
                }
            }
        }
        return count;
    }

    // 전체 크기에서 벗어나면 안됨
    private static boolean isPossiblePosition(int x, int y) {
        return (0 <= x && x < N) && (0 <= y && y < M);
    }
}