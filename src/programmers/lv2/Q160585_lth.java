package programmers.lv2;

/* 프로그래머스 160585번 혼자서 하는 틱택토 문제

[문제 풀이]
그냥 경우의 수를 다 세서 조건에 맞지 않는 경우 0 리턴
경우의 수: O보다 X가 많거나 O가 2개 이상 더 많음
          O나 X가 3개가 둘 다 이어져 있음
          O가 이겼는데 X가 하나를 더 놓음
          X가 이겼는데 O가 하나를 더 놓음
*/


import java.util.*;

class Q160585_lth {
    public int solution(String[] board) {
        int answer = 1;
        int oCount = 0;
        int xCount = 0;
        boolean oWin = Win(board, 'O');
        boolean xWin = Win(board, 'X');;
        
        
        // O보다 X가 많거나 O가 2개 이상 더 많음
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                char c = board[i].charAt(j);
                if (c == 'O') {
                    oCount++;
                } else if (c == 'X') {
                    xCount++;
                }
            }
        }
        
        if(xCount > oCount || xCount + 1 < oCount){
            answer = 0;
        }
        // O나 X가 3개가 둘 다 이어져 있음
        if (oWin && xWin) {
            answer = 0;
        }
        
        // O가 이겼는데 X가 하나를 더 놓음
        if (oWin && xCount == oCount){
            answer = 0;
        }
        
        // X가 이겼는데 O가 하나를 더 놓음
        if (xWin && xCount < oCount){
            answer = 0;
        }
        
        return answer;
    }
    
    boolean Win(String[] board, char player) {
        // 가로
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == player &&
                board[i].charAt(1) == player &&
                board[i].charAt(2) == player) {
                return true;
            }
        }

        // 세로
        for (int j = 0; j < 3; j++) {
            if (board[0].charAt(j) == player &&
                board[1].charAt(j) == player &&
                board[2].charAt(j) == player) {
                return true;
            }
        }

        // 대각선
        if (board[0].charAt(0) == player &&
            board[1].charAt(1) == player &&
            board[2].charAt(2) == player) {
            return true;
        }
        if (board[0].charAt(2) == player &&
            board[1].charAt(1) == player &&
            board[2].charAt(0) == player) {
            return true;
        }

        return false;
    }
}