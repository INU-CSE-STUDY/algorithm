package programmers.lv2;

/* 프로그래머스 138476번 귤 고르기 문제

[문제 풀이]
가장 먼저 든 생각은.. 그냥 중복 개수 몇 개인지 센 다음에, 중복 개수 기준 내림차순 정렬하기
정렬된 결과 가지고, k -= 중복 개수 하면서 몇개까지 가능한지 세면 됨

*/

import java.util.*;

class Q138476_kj {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        // 크기 별로 몇 개씩 존재하는지 카운팅
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int size : tangerine) {
            countMap.put(size, countMap.getOrDefault(size, 0) + 1);
        }

        // hashmap의 value 값으로만 List 생성 후, 내림차순 정렬
        List<Integer> countList = new ArrayList<>(countMap.values());
        Collections.sort(countList, Collections.reverseOrder());

        for (int count : countList) {
            answer++;

            if (k <= count) break;
            else {
                k -= count;
            }
        }

        return answer;
    }
}