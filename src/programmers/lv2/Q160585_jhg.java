package programmers.lv2;

import java.util.*;

// ㄱㅎ
public class Q160585_jhg {
    public int solution(String[] board) {
        return new TicTacToe(board).valid() ? 1 : 0;
    }

    public static class TicTacToe {
        List<String> board;
        static final char FIRST_MARK = 'O';
        static final char SECOND_MARK = 'X';
        static final int SIZE = 3;

        public TicTacToe(String[] board) {
            this.board = Arrays.asList(board);
        }

        public boolean valid() {
            int firstWin = getWinCount(FIRST_MARK);
            int secondWin = getWinCount(SECOND_MARK);
            int firstCount = getMarkCount(FIRST_MARK);
            int secondCount = getMarkCount(SECOND_MARK);

            if (secondWin == 0 && firstWin == 0) {
                return firstCount - secondCount == 1 || firstCount == secondCount;
            }

            if (firstWin > 0 && secondWin == 0) {
                return firstCount - secondCount == 1;
            }

            if (firstWin == 0 && secondWin > 0) {
                return firstCount == secondCount;
            }


            return false;
        }

        private int getMarkCount(char mark) {
            return board.stream()
                    .map(string -> {
                        int count = 0;
                        for (int i = 0; i < SIZE; i++) {
                            if (string.charAt(i) == mark) {
                                count++;
                            }
                        }

                        return count;
                    })
                    .reduce(0, Integer::sum);
        }

        private int getWinCount(char mark) {
            int result = 0;
            StringBuilder diagonal = new StringBuilder();
            StringBuilder reverseDiagonal = new StringBuilder();
            for (int i = 0; i < SIZE; i++) {
                final int finalI = i;
                // 가로
                String row = board.get(finalI);
                // 세로
                String col = board.stream()
                        .map(string -> String.valueOf(string.charAt(finalI)))
                        .reduce("", (x, y) -> x + y);

                if (isWin(row, mark)) {
                    result++;
                }

                if (isWin(col, mark)) {
                    result++;
                }

                diagonal.append(board.get(finalI).charAt(finalI));
                reverseDiagonal.append(board.get(SIZE - finalI - 1).charAt(finalI));
            }

            if (isWin(diagonal.toString(), mark)) {
                result++;
            }

            if (isWin(reverseDiagonal.toString(), mark)) {
                result++;
            }

            return result;
        }

        private boolean isWin(String row, char mark) {
            return row.replaceAll(String.valueOf(mark), "").isBlank();
        }
    }
}
