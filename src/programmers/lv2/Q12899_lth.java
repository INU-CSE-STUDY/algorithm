package programmers.lv2;

/* 프로그래머스 12899번 124 나라의 숫자 문제

[문제 풀이]
3진법과 비슷해서 3으로 나누면서 푸는 문제
n이 0이 될때까지 나머지가 0이면 4를 붙이고 n을 3으로 나눈 몫에서 1을 뺌
나머지가 1이면 1을 붙이고 나머지가 2이면 2를 붙이고 n을 3으로 나눈 몫을 n으로
*/

import java.util.*;

class Q12899_lth {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();     // StringBuilder로 문자열 생성
        while(n > 0){
            int r = n % 3;
            if (r == 0) {
                answer.append('4');         // 3으로 나눈 나머지가 0이면 4
                n = n / 3 - 1;
            } else {
                answer.append(r == 1 ? '1' : '2');      // 나머지가 1이면 1, 2면 2
                n /= 3;
            }
        }
        
        return answer.reverse().toString();
    }
}