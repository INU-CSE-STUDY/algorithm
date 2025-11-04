package programmers.lv2;

/* 프로그래머스 131704번 택배상자 문제

[문제 풀이]
후입선출이니 스택이고 스택에 넣고 뺄 때마다 order 배열과 비교해서 같으면 빼고 answer 증가

*/

import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int current = 1;
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        
        while (current <= order.length) {
            stack.push(current);

            while (!stack.isEmpty() && stack.peek() == order[idx]) {
                stack.pop();
                idx++;
                answer++;
            }

            current++;
        }

        return answer;
    }
}