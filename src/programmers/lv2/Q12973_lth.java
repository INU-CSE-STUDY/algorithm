package programmers.lv2;

/* 프로그래머스 Q12973번 짝지어 제거하기 문제

[문제 풀이]
중복된 것을 제거하고 그 전의 내용도 저장하기 위해 스택을 사용해야해서 dq로 스택을 구현했다
배열은 인덱스와 반복문 조건이 까다로워져서 dq를 사용했으며 dq에 char에 해당하는 것이 있다면
제거하고 없다면 dq에 저장하는 식으로 dq에 제거되지 않은 게 있는지에 따라 반환

*/

import java.util.*;

class Q12973_lth {
    public int solution(String s) {
        Deque<Character> dq = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (!dq.isEmpty() && dq.peek() == c) {
                dq.pop(); // 짝 제거
            } else {
                dq.push(c);
            }
        }

        if(dq.isEmpty()){
            return 1;
        }else{
            return 0;
        }
    }

}