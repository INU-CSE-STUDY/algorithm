package programmers.lv2;

/* 프로그래머스 131701번 연속 부분 수열 합의 개수 문제

[문제 풀이]
어떻게 풀어야할지 모르겠어서 그냥 하나하나 더해서 완탐으로 품

*/

import java.util.*;

class Q131701_lth {
    public int solution(int[] elements) {
        HashSet<Integer> set = new HashSet<>();
        int n = elements.length;
        for(int i = 0 ; i < n; i++){
            int sum = 0;
            for(int j = 0 ; j < n ; j++){
                sum += elements[(i + j) % n];
                set.add(sum);
            }
        }
        return set.size();
    }
}