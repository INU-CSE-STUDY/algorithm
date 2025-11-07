package programmers.lv2;

/* 프로그래머스 131127번 할인 행사 문제

[문제 풀이]
map으로 물품과 개수를 지정하고 discount에서 want의 물품이 나온 i순간부터 10일동안 map에 있다면 개수 -1
map이 비면 answer++ 아니면 i++하고 다시 반복
if(!wants.containsKey(discount[i])) continue;로 불필요한 반복문 줄이기 + map 복사줄이기

*/


import java.util.*;


class Q131127_lth {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        Map<String, Integer> wants = new HashMap<>();
        for(int i = 0; i < want.length; i++){
            wants.put(want[i], number[i]);
        }
        
        for(int i = 0; i < discount.length - 9; i++){
            if(!wants.containsKey(discount[i])) continue;
            Map<String, Integer> map = new HashMap<>(wants);
            for (int j = i; j < i + 10; j++) {
                String item = discount[j];
                if (map.containsKey(item)) {
                    map.put(item, map.get(item) - 1);
                    if (map.get(item) == 0) map.remove(item);
                }
            }
            if (map.isEmpty()) {
                answer++;
            }
        }
        
        return answer;
    }
}