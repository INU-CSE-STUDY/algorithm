package programmers.lv2;

/* 프로그래머스 42747번 H-Index 문제

[문제 풀이]
정렬한다음 해당인덱스의 숫자가 인덱스보다 크거나 같은 제일 작은수를 찾는 방식
*/

import java.util.*;

class Q42747_lth {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        
        for (int i = 0; i < citations.length; i++) {
            if(citations[citations.length - 1 - i] >= i + 1){
                answer = i + 1;
            }else{
                return answer;
            }
        }
        
        return answer;
    }
}