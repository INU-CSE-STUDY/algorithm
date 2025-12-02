package programmers.lv2;

/* 프로그래머스 76502번 괄호 회전하기 문제

[문제 풀이]
문제에서 알려준 것처럼 문자열 s를 왼쪽으로 x번(0 <= x < s의 길이) 회전시켰을 때 나오는 문자열이 올바른 괄호 문자열인지 판단하는 문제!
s의 길이는 1000이하이므로,, 하나씩 회전시키면서 확인해도 무리없다고 판단(완전탐색)
그리고 문제에서도 대괄호 중괄호 소괄호 순서라고 해야 하나? 그걸 신경 안쓰고 그냥 괄호끼리 짝 맞기만 하면 올바른 괄호 문자열이라 해서 쉬운듯!!

올바른 괄호 문자열인지 확인하는 방법
1. 왼쪽 괄호가 들어왔을 경우 스택에 push
2. 오른쪽 괄호가 들어왔을 경우
   - 스택이 비어있음 = 짝이 맞는 왼쪽 괄호가 없음 = 올바른 괄호 문자열이 아님 => return false;
   - 스택이 비어있지 않음
     - 들어온 괄호 모양(소/중/대)이랑 스택 pop해서 나온 왼쪽 괄호 모양이 똑같은지 확인
       => 괄호 모양이 똑같음 = 올바른 괄호 문자열 = 탐색 이어가기
       => 괄호 모양이 다름 = 올바른 괄호 문자열이 아님 => return false;
3. for문 탐색이 종료되면 스택이 비어있는지 확인하기
   - 스택이 비어있지 않음 = 짝이 맞는 오른쪽 괄호가 존재하지 않음 = 올바른 괄호 문자열이 아님 => return false;
   - 스택이 비어있음 = 모든 괄호가 짝이 맞음 = 올바른 괄호 문자열 = return true;

*/

import java.util.*;

class Q76502_kj {
    public int solution(String s) {

        int answer = 0;
        for (int x = 0; x < s.length(); x++) {

            if (x != 0) {
                // 왼쪽으로 회전시키기
                s = rotateString(s);
            }

            if (isCorrect(s)) {
                // 올바른 괄호 문자열인 경우
                answer++;
            }
        }

        return answer;
    }

    private String rotateString(String s) {
        return s.substring(1) + s.substring(0, 1);
    }

    private boolean isCorrect(String s) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {

            char now = s.charAt(i);

            if (now == '[' || now == '(' || now == '{') {
                // 현재 문자가 왼쪽 괄호일 경우, 스택에 삽입
                stack.push(now);
            } else {

                // 오른쪽 괄호가 들어왔는데 스택이 비어있을 경우 = 잘못된 괄호 문자열
                if (stack.isEmpty()) return false;

                // 스택이 비어있지 않다면 짝이 맞는 오른쪽 괄호가 들어왔는지 확인해야 함
                char left = stack.pop();

                // 짝이 맞다면 탐색을 이어나가기
                if ((left == '(' && now == ')') ||
                        (left == '{' && now == '}') ||
                        (left == '[' && now == ']')) continue;

                // 짝이 맞지 않음 = 잘못된 괄호 문자열
                return false;
            }
        }

        if (stack.isEmpty()) return true;
        return false; // 탐색이 종료됐는데 스택에 값이 남아있다면 올바른 괄호 문자열이 아님
    }
}
