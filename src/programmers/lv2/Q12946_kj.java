package programmers.lv2;

/* 프로그래머스 12946번 하노이의 탑 문제

[문제 풀이]
공무원 시험에도 나오는 재귀 문제(시간복잡도나 그런 식으로 나옴)
*/

import java.util.*;

class Q12946_kj {

    List<int[]> answer = new ArrayList<>();

    public int[][] solution(int n) {

        hanoi(n, 1, 2, 3);
        return answer.toArray(int[][]::new);
    }

    private void hanoi(int n, int from, int by, int to) {

        if (n == 1) {
            answer.add(new int[]{ from, to });
            return;
        }

        hanoi(n - 1, from, to, by);
        answer.add(new int[]{ from, to });
        hanoi(n - 1, by, from, to);
    }
}
