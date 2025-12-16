package programmers.lv2;

/* 프로그래머스 64065번 튜플 문제

char 기준으로 돌리면서 숫자를 map에 저장
숫자가 많이 나온 순서대로 튜플의 앞에서부터 저장

*/



import java.util.*;

class Q64065_lth {
    public int[] solution(String s) {
        Map<Integer, Integer> map = init(s);
        
        List<Map.Entry<Integer, Integer>> list =
        new ArrayList<>(map.entrySet());

        list.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        List<Integer> answer = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : list) {
            answer.add(e.getKey());
        }
        int[] result = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }
        
        return result;
    }
    
    private Map<Integer, Integer> init(String s){
        Map<Integer, Integer> map = new HashMap<>();
        int num = 0;
        boolean inNumber = false;

        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
                inNumber = true;
            } else {
                if (inNumber) {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                    num = 0;
                    inNumber = false;
                }
            }
        }

        // 마지막 숫자 처리
        if (inNumber) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map;
    }
}