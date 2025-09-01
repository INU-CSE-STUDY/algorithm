package programmers.lv2;

/* 프로그래머스 12909번 올바른 괄호호 문제

[문제 풀이]
입력받은 '(' 와 ')'로 이루어진 문자열이 '(' 와 ')'의 개수가 같아야함

입력받은 문자열의 길이만큼 반복문을 돌리면서 '(' 가 나오면 stack +1, ')'가 나오면 stack -1을 해서 음수가 되거나 마지막 종료시 0이 아닐 시
올바르지 않은 문장으로 판별
*/

import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int len = s.length();
        int stack = 0;
        // 문장의 길이만큼 반복
        for (int i =0; i < len; i++){
            char ch = s.charAt(i);
            if(ch == ')'){
                stack -= 1;
            }
            if(ch == '('){
                stack += 1;
            }
            // 중간에 stack이 음수가 되는 경우
            if(stack < 0){
                answer = false;
                return answer;
            }
        }
        // 종료시에 stack이 0이 아닌경우
        if(stack != 0){
            answer = false;
            return answer;
        }
        return answer;
    }
}