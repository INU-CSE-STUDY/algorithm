package programmers.lv2;

/* 프로그래머스 60058번 괄호 변환 문제

[문제 풀이]
엄청,, 자세하게 문자열 변환하는 법을 알려주었다. 진짜 일단 순서대로 만들어보자;

*/

import java.util.*;

class Q60058_kj {
    public String solution(String p) {

        // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
        return p.equals("") ? "" : getAnswer(p);
    }

    private String getAnswer(String p) {

        if (p.equals("")) {
            return "";
        }

        String[] uv = splitStr(p); // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
        String u = uv[0];
        String v = uv[1];

        if (isCorrectStr(u)) {
            /*
            3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
            3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
            */

            return u + getAnswer(v);
        }

        // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.

        /*
        4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
        4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
        4-3. ')'를 다시 붙입니다.
        */
        String answer = "(" + getAnswer(v) + ")";

        /*
        4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
        */
        u = u.length() == 2 ? "" : u.substring(1, u.length() - 1);
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') answer += ")";
            else answer += "(";
        }

        return answer; // 4-5. 생성된 문자열을 반환합니다.
    }

    private String[] splitStr(String w) {
        /*
        2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
        단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
        */

        int left = 0, right = 0; // 왼쪽, 오른쪽 괄호 개수를 세서 "균형잡힌 괄호 문자열 u"와 v로 분리
        int index = -1;
        for (int i = 0; i < w.length(); i++) {

            char c = w.charAt(i);
            if (c == '(') left++;
            else right++;

            if (left == right) {
                index = i;
                break;
            }
        }

        // "균형잡힌 괄호 문자열 u", v 정보를 담은 String 배열 반환
        return new String[]{ w.substring(0, index + 1), w.substring(index + 1) };
    }

    private boolean isCorrectStr(String u) {
        // 올바른 괄호 문자열인지 검사하는 메서드

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < u.length(); i++) {

            char c = u.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    // 오른쪽 괄호가 들어왔는데 스택이 비어있으면 올바른 괄호 문자열이 아님
                    return false;
                }

                stack.pop();
            }
        }

        // 반복문 종료 후 스택이 비어있다면 올바른 괄호 문자열이 맞고, 스택에 왼쪽 괄호가 남아있다면 올바른 괄호 문자열이 아님
        return stack.isEmpty();
    }
}
