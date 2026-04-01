package programmers.lv2;

/* 프로그래머스 12952번 N-Queen 문제

[문제 풀이]
대각선의 값을 왼쪽 대각선배열과 오른쪽대각선에 넣어서 퀸을 뒀을때 행열이 안 겹치고 왼쪽 오른쪽 대각선이 모두 겹치지 않아야한다
마지막 줄까지 성공했을 시 answer++
*/

class Q12952_lth {
    int answer = 0;
    boolean[] col;
    // \ 대각선 (row - col)
    boolean[] diag1;
    // / 대각선 (row + col)
    boolean[] diag2;

    public int solution(int n) {
        col = new boolean[n];
        // \ 대각선: (row - col)
        // 범위: -(n-1) ~ (n-1)
        // 음수 방지를 위해 + (n-1) 이동 → 0 ~ (2n-2)
        diag1 = new boolean[2 * n - 1];
        // / 대각선: (row + col)
        // 범위: 0 ~ (n-1) + (n-1) = 2n-2
        diag2 = new boolean[2 * n - 1];

        dfs(0, n);
        return answer;
    }

    void dfs(int row, int n) {
        if (row == n) {
            answer++;
            return;
        }

        for (int c = 0; c < n; c++) {
            // \ 방향 : 음수가 되기에 n-1만큼 이동
            int d1 = row - c + n - 1;
            // / 방향 
            int d2 = row + c;

            // 같은 열 또는 같은 대각선에 퀸이 있으면 skip
            if (col[c] || diag1[d1] || diag2[d2]) continue;

            // 퀸 배치
            col[c] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            dfs(row + 1, n);

            // 백트래킹 (퀸 제거)
            col[c] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}