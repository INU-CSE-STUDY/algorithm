package programmers.lv2;

/* 프로그래머스 76502번 괄호 회전하기 문제

[문제 풀이]
그냥 돌리면서 스택으로 올바른 괄호 문자열인지 확인하는 문제

*/

import java.util.*;

class Q76502_lth {
    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            String rotated = s.substring(i) + s.substring(0, i);

            if (isValid(rotated)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (!match(top, c)) return false;
            }
        }

        return stack.isEmpty();
    }

    private boolean match(char open, char close) {
        return (open == '(' && close == ')')
            || (open == '{' && close == '}')
            || (open == '[' && close == ']');
    }
}
