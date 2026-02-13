package programmers.lv2;

/* 프로그래머스 42584번 주식가격 문제

[문제 풀이]
스택에 다음 가격보다 낮아지지 않은 인덱스들을 저장해서
하락하는데 걸린 시간을 구한다

*/

import java.util.*;

class Q42584_lth {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];

        // 인덱스를 저장하는 스택 역할
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // 가격이 낮아지면 스택을 초기화
            while (!stack.isEmpty() && prices[stack.peekLast()] > prices[i]) {
                int idx = stack.pollLast();      // 하락이 확정된 시점
                answer[idx] = i - idx;           // 하락까지 걸린 시간
            }
            stack.offerLast(i); // 현재 시점을 스택에 추가
        }

        // 끝까지 가격이 안 떨어진 시점들 처리
        while (!stack.isEmpty()) {
            int idx = stack.pollLast();
            answer[idx] = (n - 1) - idx;
        }

        return answer;
    }
}
