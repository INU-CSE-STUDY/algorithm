package programmers.lv2;

/* 프로그래머스 77485번 행렬 테두리 회전하기 문제

[문제 풀이]
문제에서 주어진 것처럼 그냥 직접 회전하면 된다!
rows, columns 크기도 작고 회전해야 하는 개수 자체도 작아서 그냥 직접 행렬 채워서 회전하면 문제가 없다.
회전도 그냥 for문 4개써서 구현했고.. (dx, dy 만들어서 하려고 하니까 if문 처리를 좀 해줘야 해서.. 이게 더 깔끔하다고 판단하고 변경함) 회전 돌리면서 min 값 업데이트해서 반환하는 방식으로 구현

*/

class Q77485_kj {

    int ROW, COLUMN;
    int[][] map;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        ROW = rows + 1;
        COLUMN = columns + 1;
        initMap();

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int minValue = rotateMap(query[0], query[1], query[2], query[3]);
            answer[i] = minValue;
        }

        return answer;
    }

    private int rotateMap(int x1, int y1, int x2, int y2) {

        int prev = map[x1][y1];
        int min = prev;

        // 회전은 for문 4개로 작성
        for (int nowY = y1 + 1; nowY <= y2; nowY++) {

            int now = map[x1][nowY];
            min = Math.min(min, now);

            map[x1][nowY] = prev;
            prev = now;
        }

        for (int nowX = x1 + 1; nowX <= x2; nowX++) {

            int now = map[nowX][y2];
            min = Math.min(min, now);

            map[nowX][y2] = prev;
            prev = now;
        }

        for (int nowY = y2 - 1; nowY >= y1; nowY--) {

            int now = map[x2][nowY];
            min = Math.min(min, now);

            map[x2][nowY] = prev;
            prev = now;
        }

        for (int nowX = x2 - 1; nowX >= x1; nowX--) {

            int now = map[nowX][y1];
            min = Math.min(min, now);

            map[nowX][y1] = prev;
            prev = now;
        }

        return min;
    }

    private void initMap() {
        map = new int[ROW][COLUMN];

        for (int i = 1; i < ROW; i++) {
            for (int j = 1; j < COLUMN; j++) {
                map[i][j] = ((i - 1) * (COLUMN - 1) + j);
            }
        }
    }
}