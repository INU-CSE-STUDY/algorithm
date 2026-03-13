package programmers.lv2;

/* 프로그래머스 17679번 프랜즈4블록 문제

[문제 풀이]
순서대로 검사하면서 한 블록의 오른쪽, 오른쪽아래대각선, 아래가 같은 블록인지 검사
같다면 그 좌표를 저장하면서 m-1, n-1까지 반복
저장된 좌표기준으로 블록의 오른쪽, 오른쪽아래대각선, 아래를 0(공백을 표시할 문자)으로 변경
n이 아닌것이 n으로 변경될때마다 answer += 1
검사하면서 0인 블록의 위에 0이 아닌 블록이 있다면 서로 바꿈으로써 블록 내리기
검사했는데 저장된 좌표가 없을 때까지 반복
*/



import java.util.*;

class Q17679_lth {
    static int answer = 0;
    public int solution(int m, int n, String[] board) {
        char[][] map = new char[m][n];

        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }
        
        while(true){
            List<int[]> list = new ArrayList<>();
            for(int i = 0; i < m-1; i++){
                for(int j = 0; j < n-1; j++){
                    if(isMatch(i , j, map)){
                        list.add(new int[]{i, j});
                    }
                }
            }
            
            if (list.isEmpty()) {
                break;
            }
            
            initBoard(list, map);
            dropBlock(m, n, map);
        }
        
        return answer;
    }
    
    private boolean isMatch(int i, int j, char[][] board) {
        char base = board[i][j];
        if (base == '0') return false;

        return base == board[i + 1][j] &&
               base == board[i][j + 1] &&
               base == board[i + 1][j + 1];
    }
    
    // 저장된 좌표기준으로 블록의 오른쪽, 오른쪽아래대각선, 아래를 0(공백을 표시할 문자)으로 변경
    private void initBoard(List<int[]> list, char[][] board) {
        boolean[][] remove = new boolean[board.length][board[0].length];

        for (int[] pos : list) {
            int i = pos[0];
            int j = pos[1];

            remove[i][j] = true;
            remove[i][j + 1] = true;
            remove[i + 1][j] = true;
            remove[i + 1][j + 1] = true;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (remove[i][j] && board[i][j] != '0') {
                    board[i][j] = '0';
                    answer++;
                }
            }
        }
    }
    
    private void dropBlock(int m, int n, char[][] board) {
        for (int j = 0; j < n; j++) {           // 각 열 검사
            for (int i = m - 1; i > 0; i--) {       // 아래에서 위로 검사
                if (board[i][j] == '0') {           // 빈칸이면
                    int k = i - 1;                  // 바로 위부터 탐색
                    while (k >= 0 && board[k][j] == '0') {
                        k--;                         // 0이 아닌 블록이 나올때까지
                    }

                    if (k >= 0) {                       //찾았으면 변경
                        board[i][j] = board[k][j];
                        board[k][j] = '0';
                    }
                }
            }
        }
    }
}