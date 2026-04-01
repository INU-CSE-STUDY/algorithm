package programmers.lv2;

/* 프로그래머스 12952번 N-Queen 문제

[문제 풀이]

같은 행, 열 + 대각선에는 설치가 안됨
- 행은 어차피 퀸 설치하면 다음 행으로 넘어가니까 생각할 필요 없고 열이랑 대각선만 생각하면 됨
  크기가 n인 배열 만들어서 index를 행, value를 열로 생각해서 관리하기
  새로운 퀸 배치할 때 행은 아니까 이전 행까지 확인해서 열이 똑같은지랑 |행의 차| = |열의 차|(대각선 계산)를 만족하는 지 확인

dfs 돌려서 퀸을 n개 다 배치하면 answer 증가하게하고 백트래킹으로 가능한 위치 탐색

*/

class Q12952_kj {

    int answer = 0;
    int[] positionCheck;

    public int solution(int n) {

        positionCheck = new int[n]; // index = 행, value = 열로 저장해서 배열 하나로 대각선 판단하기
        dfs(0, n);

        return answer;
    }

    private void dfs(int row, int n) {

        if (row == n) {
            answer++;
            return;
        }

        for (int column = 0; column < n; column++) {

            if (isPossiblePosition(positionCheck, row, column)) {
                // 퀸을 배치할 수 있는 위치라면 퀸 배치
                positionCheck[row] = column;
                dfs(row + 1, n);
            }
        }
    }

    private boolean isPossiblePosition(int[] positionCheck, int nowRow, int nowColumn) {

        for (int row = 0; row < nowRow; row++) {

            int column = positionCheck[row]; // 열 정보를 받아옴

            if (column == nowColumn) return false; // 같은 열에는 퀸을 둘 수 없음
            if (Math.abs(nowRow - row) == Math.abs(nowColumn - column)) return false; // 대각선 지점에는 퀸을 둘 수 없음
        }

        return true;
    }
}