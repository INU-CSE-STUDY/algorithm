package programmers.lv2;

/* 프로그래머스 12973번 짝지어 제거하기 문제

[문제 풀이]
stack 문제구나..
stack에 앞에서 부터 하나씩 넣어서 peek() == charAt() 이랑 똑같은지 확인
- 똑같으면 앞에거 pop 하고 charAt한거 안넣고, 다르면 넣기
-> 문자열 다 돌았을 때 stack이 비어있으면 1, 아니면 0

*/

import java.util.*;

class Q12973_kj {
    public int solution(String s) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {

            char nowChar = s.charAt(i);

            if (stack.isEmpty()) {
                // 스택이 비어있는 경우에는 무조건 push
                stack.push(nowChar);
                continue;
            }

            // 스택이 비어있지 않다면 짝 문자인지 확인
            char pair = stack.peek();

            if (pair == nowChar) {
                // 앞뒤 문자열이 같다면 제거
                stack.pop();
                continue;
            }

            stack.push(nowChar); // 앞뒤 문자열이 다르다면 push

        }

        return stack.isEmpty() ? 1 : 0;
    }
}
