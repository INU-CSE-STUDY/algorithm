package programmers.lv2;

/* 프로그래머스 68645번 삼각 달팽이 문제

[문제 풀이]
처음에는 1차원 배열로 풀려고 했는데 너무 복잡하여 2차원 배열로 바꾸었다
n x n 크기의 2차원 배열을 만들고, 삼각형 모양으로 숫자를 채운다
아래 -> 오른쪽 -> 대각선 위 방향으로 이동하면서 숫자를 채운다
그리고 나서 삼각형 모양의 숫자들만 1차원 배열에 담아서 반환한다
*/

class Q68645_lth {
    public int[] solution(int n) {
        int[][] map = new int[n][n];  // 삼각형을 만들기 위해 n×n 배열 생성
        int num = 1;                  // 채울 숫자
        int y = -1, x = 0;            // 초기 위치
        int value = num;

        // 각 단계마다 이동 횟수 = n, n-1, n-2 ...
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i % 3 == 0) {      // 아래로 이동
                    y++;
                } else if (i % 3 == 1) { // 오른쪽으로 이동
                    x++;
                } else {                   // 대각선 위로 이동
                    y--;
                    x--;
                }
                map[y][x] = num++;
            }
        }

        // 결과 1차원 배열 크기 = n * (n + 1) / 2
        int size = n * (n + 1) / 2;
        int[] answer = new int[size];
        int idx = 0;

        // 값 있는 칸만 추출
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {  // 삼각형 영역만 순회
                answer[idx++] = map[i][j];
            }
        }

        return answer;
    }
}
