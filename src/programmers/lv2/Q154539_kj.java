package programmers.lv2;

/* 프로그래머스 154539번 뒤에 있는 큰 수 찾기 문제

[문제 풀이]
이중포문 같은거 쓰면 최악의 경우 O(n^2)만큼 걸려서 시간초과
어떤 걸 써도 앞에서부터 탐색하면 최악의 경우 O(n^2)만큼 걸리니까.. 뒤에서부터 탐색하는게 핵심
=> 스택 쓰기
*/

import java.util.*;

class Q154539_kj {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = numbers.length - 1; i >= 0; i--) {
            // 현재 숫자보다 작거나 같은 값들은 스택에서 제거
            while (!stack.isEmpty() && stack.peek() <= numbers[i]) {
                stack.pop();
            }

            answer[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(numbers[i]);
        }

        return answer;
    }
}
