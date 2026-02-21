package programmers.lv2;

/* 프로그래머스 42578번 의상 문제

[문제 풀이]
map으로 각 종류가 몇번 나왔는지 계산하고 그 숫자들로 가능한 개수를 구함
구하는 식은 (n₁ + 1)(n₂ + 1)(n₃ + 1)...(nₖ + 1) - 1

*/



import java.util.*;

class Q42578_lth {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();

        // map 만들기
        for(String[] dress : clothes){
            map.put(dress[1], map.getOrDefault(dress[1], 0) + 1);
        }
        
        //(n₁ + 1)(n₂ + 1)(n₃ + 1)...(nₖ + 1)
        for (int cur : map.values()) {
            answer *= (cur + 1);
        }
        
        // -1
        return answer - 1;
    }
}