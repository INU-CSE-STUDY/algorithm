package programmers.lv2;

/* 프로그래머스 160585번 혼자서 하는 틱택토 문제

[문제 풀이]
실수할 수 있는 경우
- 턴을 착각해서 표시한 경우
  - (정상) O 개수 = X 개수 / O 개수 = X 개수 + 1
  - (비정상) O 개수 < X 개수 - X가 후공인데 더 많을 수가 없음
  - (비정상) O 개수 > X 개수 + 1 - 한 턴씩 주고받는 게임이어서 개수가 2개 이상 차이날 수 없음
- 승리해서 게임이 종료됐음에도 게임이 진행된 경우
  - (승부가 나지 않은 경우) 둘 다 완성되지 않음
  - (승부가 정상적으로 났을 경우) 둘 중 하나만 완성되어야 함
  - (비정상적인 승부) 혼자서 2개 이상 승리 조건을 만족한 경우 / 둘 다 승리 조건을 만족한 경우 등..
    => 혼자서 2개 이상 승리 조건을 만족한 경우는 비정상적인 경우가 아님. 꼭짓점이나 가운데에 둠으로써 한번에 두개가 완성될 수도 있음...

>> 승리해서 게임이 종료된 경우에도 각 모양의 개수를 다 생각해서 해야 함!!
   아직 승부가 나지 않은 경우 => [O 개수 = X 개수] 또는 [O 개수 = X 개수 + 1]
   O가 승리한 경우 => [O 개수 = X 개수 + 1]
   X가 승리한 경우 => [O 개수 = X 개수]
*/

class Q160585_kj {
    public int solution(String[] board) {
        char[][] gameBoard = new char[3][3];
        int[] cnt = initGameBoard(board, gameBoard);
        int oCnt = cnt[0], xCnt = cnt[1];

        TicTacToe game = new TicTacToe(oCnt, xCnt);

        // 진행 턴과 승리 조건 모두 정상적인 경우 return 1
        if (game.isPossibleCase(gameBoard)) {
            return 1;
        }

        // 하나라도 불가능한 경우 return 0
        return 0;
    }

    private int[] initGameBoard(String[] board, char[][] gameBoard) {
        // 게임판을 char[][] 배열로 바꾸면서, 게임판 위에 있는 O와 X 개수 세기
        int oCnt = 0;
        int xCnt = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i].charAt(j);
                gameBoard[i][j] = c;

                if (c == 'O') oCnt++;
                else if (c == 'X') xCnt++;
            }
        }

        return new int[]{ oCnt, xCnt };
    }
}

class TicTacToe {
    int oCnt;
    int xCnt;

    public TicTacToe(int oCnt, int xCnt) {
        this.oCnt = oCnt;
        this.xCnt = xCnt;
    }

    public boolean isPossibleCase(char[][] board) {
        // 승부 결과와 표시된 모양의 개수를 종합하여 정상적인 게임인지 확인

        int[] winCount = getWinCount(board);
        int oWinCnt = winCount[0];
        int xWinCnt = winCount[1];

        // 아직 승부가 나지 않은 경우
        if ((oWinCnt == 0 && xWinCnt == 0) &&
                this.isPossibleCount('N')) return true;

        // O가 승리한 경우
        if ((oWinCnt >= 1 && xWinCnt == 0) &&
                this.isPossibleCount('O')) return true;

        // X가 승리한 경우
        if ((oWinCnt == 0 && xWinCnt >= 1) &&
                this.isPossibleCount('X')) return true;

        return false;
    }

    private int[] getWinCount(char[][] board) {
        // 게임판을 확인해 승리 조건을 만족하는 행/열/대각선을 카운트
        int oWinCnt = 0;
        int xWinCnt = 0;

        for (int i = 0; i < 3; i++) {
            // 가로 승리 조건
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == 'O') oWinCnt++;
                else if (board[i][0] == 'X') xWinCnt++;
            }

            // 세로 승리 조건
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] == 'O') oWinCnt++;
                else if (board[0][i] == 'X') xWinCnt++;
            }
        }

        // 대각선 승리 조건
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            if (board[1][1] == 'O') oWinCnt++;
            else if (board[1][1] == 'X') xWinCnt++;
        }

        return new int[]{ oWinCnt, xWinCnt };
    }

    private boolean isPossibleCount(char winPlayer) {
        // 승리 플레이어별로 가능한 모양의 개수가 다름

        // 아직 승부가 나지 않은 경우
        if (winPlayer == 'N') {
            if (this.oCnt == this.xCnt) return true;
            if (this.oCnt == this.xCnt + 1) return true;
        }

        // O가 승리한 경우
        if (winPlayer == 'O') {
            if (this.oCnt == this.xCnt + 1) return true;
        }

        // X가 승리한 경우
        if (winPlayer == 'X') {
            if (this.oCnt == this.xCnt) return true;
        }

        return false;
    }
}