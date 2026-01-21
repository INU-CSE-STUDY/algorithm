package programmers.lv2;

/* 프로그래머스 42883번 큰 수 만들기 문제

[문제 풀이]
배열로 인덱스 사용하려다가 너무 비효율적이라 Deque로 변경
앞에서부터 deque에 넣어서 비교하기
deque에 들어있는 마지막 값과 비교해서 현재 값이 더 크면 뒤에 있는 값을 제거하는 식으로 진행
제거한뒤 그 앞에 값이랑도 비교해야 하므로 while문 사용
마지막에 k가 남아있을 수도 있으므로 while문을 한번 더 돌려서 뒤에서부터 k개 제거
*/

import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i a= 0; i < number.length(); i++) {
            char cur = number.charAt(i);

            // 뒤에 있는 숫자보다 현재 숫자가 더 크면 뒤를 제거
            while (!stack.isEmpty() && k > 0 && stack.peekLast() < cur) {
                stack.pollLast();
                k--;
            }
            stack.addLast(cur);
        }

        // 아직 k가 남아있으면 뒤에서 제거
        while (k > 0) {
            stack.pollLast();
            k--;
        }

        StringBuilder answer = new StringBuilder();
        for (char c : stack) answer.append(c);
        return answer.toString();
    }
}
