package programmers.lv2;

/* 프로그래머스 250136번 석유 시추 문제

[제약 조건]
n * m 크기의 격자모양 땅

[문제 풀이]
하나의 열을 수직으로 뚫고 들어갈 때, 가장 큰 석유량 구하기

1. 이미 방문했던 석유칸도 계속 방문하면서 합해지는 경우도 존재하기 때문에, 단지번호 구하기마냥 한번에 싹 순회해서 석유 단지별로 용량이 얼마인지 저장하기
- [단지 이름, 단지 크기] 이런 식으로? -> map으로 저장하기
2. 각 시추관 위치마다 set에 저장해서 중복되지 않는 석유단지의 용량 더하기
3. max 값 반환하기
*/

import java.util.*;

class Q250136_kj {
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static Map<Integer, Integer> oilMap; // [석유 덩어리 단지, 단지의 크기] 정보를 저장할 map
    static Set<Integer> oilSet; // 시추관 별로 중복되지 않는 석유 덩어리 단지를 저장할 set

    public int solution(int[][] land) {
        int n = land.length; // 세로 길이
        int m = land[0].length; // 가로 길이

        oilMap = new HashMap<>();

        // 일반 땅 - 0, 석유 존재 - 1 이므로 단지 번호는 2부터 시작
        int landNum = 2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // 각 단지 별로 몇 칸의 석유가 있는지 세기
                int oilCnt = 0;

                // 석유가 존재하면서, 방문하지 않은 땅을 발견한 경우
                if (land[i][j] == 1) {

                    // DFS 탐색으로 해당 단지의 크기를 구함
                    oilCnt = dfs(i, j, land, 1, landNum);

                    // 단지 번호와 크기를 map에 삽입
                    oilMap.put(landNum, oilCnt);

                    // 다음 단지를 저장하기 위해 단지 번호 증가
                    landNum++;
                }
            }
        }

        int max = 0;
        // 시추관을 수직으로 뚫기
        for (int i = 0; i < m; i++) {

            // 수직으로 뚫었을 때 만나는 석유 단지를 저장할 set
            oilSet = new HashSet<>();

            int total = 0; // 전체 석유 매장량
            for (int j = 0; j < n; j++) {
                // 0이 아닌 값이 있을 경우(단지 번호를 만났을 경우)
                if (land[j][i] != 0) {
                    oilSet.add(land[j][i]);
                }
            }

            // 탐색이 종료됐을 경우, 해당 위치에서 얻을 수 있는 석유량을 합산
            for (Integer index : oilSet) {
                total += oilMap.get(index);
            }

            // 가장 큰 값 저장
            max = Math.max(max, total);
        }

        return max;
    }

    // land 범위를 벗어나지 않는 선에서 탐색을 진행해야 함
    private static boolean isPossiblePosition(int x, int y, int[][] land) {
        return (0 <= x && x < land.length) && (0 <= y && y < land[0].length);
    }

    private static int dfs(int x, int y, int[][] land, int count, int landNum) {
        // 방문 처리(단지 번호로 업데이트하는 방식)
        land[x][y] = landNum;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            // 범위를 벗어나지 않으면서 석유가 존재하는 곳 탐색
            if (isPossiblePosition(nextX, nextY, land) && land[nextX][nextY] == 1) {
                count = dfs(nextX, nextY, land, count + 1, landNum);
            }
        }

        return count;
    }
}