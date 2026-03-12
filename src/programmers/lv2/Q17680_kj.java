package programmers.lv2;

/* 프로그래머스 17680번 캐시 문제

[문제 풀이]
LRU로 한다는 말은,, 최근에 사용하지 않은 걸 바꾼다는 말

cacheSize = 3이면 총 3칸짜리 크기라는 소리고, 이 3개 내에서 잘 교체해나가기

두번째 예시로 풀이해보면
- 초기상태 ["", "", ""] / 사용된 순서(숫자가 클 수록 최근 사용) [0, 0, 0] / 실행시간 0
1 ["Jeju", "", ""] / [1, 0, 0] / 5
2 ["Jeju", "Pangyo", ""] / [1, 2, 0] / 10
3 ["Jeju", "Pangyo", "Seoul"] / [1, 2, 3] / 15
4 ["Jeju", "Pangyo", "Seoul"] / [4, 2, 3] / 16
5 ["Jeju", "Pangyo", "Seoul"] / [4, 5, 3] / 17
6 ["Jeju", "Pangyo", "Seoul"] / [4, 5, 6] / 18
...

Map으로 풀 수 있나?
map.containsKey()로 존재하는지 여부 확인
- 있다면 hit + value값 변경
- 없다면 miss + map.size() == cacheSize 확인 후 put하기
  - 가장 오래 사용되지 않은(value가 제일 작은) key값 remove

7, 17번이 틀려서 질문하기를 확인해봤는데 cache 사이즈가 0일 때를 고려하지 않았다;

*/

import java.util.*;

class Q17680_kj {

    static final int CACHE_HIT = 1;
    static final int CACHE_MISS = 5;

    public int solution(int cacheSize, String[] cities) {

        // 캐시 크기가 0이면 무조건 miss만 나옴
        if (cacheSize == 0) return cities.length * 5;

        Map<String, Integer> lruMap = new HashMap<>();

        int answer = 0;
        int nowIdx = 1;
        for (String city : cities) {

            city = city.toUpperCase(); // 대소문자 구분을 하지 않는다고 했으므로, 대문자로 통일

            // 캐시에 해당 도시 이름이 존재하는 경우
            if (lruMap.containsKey(city)) {

                answer += CACHE_HIT;
                lruMap.put(city, nowIdx++); // value 값만 업데이트 (가장 최근에 사용했으므로)

                continue;
            }

            // 캐시에 해당 도시 이름이 존재하지 않는 경우
            answer += CACHE_MISS;

            // 캐시 공간이 가득찼다면, 가장 오래 사용되지 않는 도시 제거
            if (lruMap.size() == cacheSize) {
                String lruTarget = getLruTarget(lruMap);
                lruMap.remove(lruTarget);
            }

            lruMap.put(city, nowIdx++);
        }

        return answer;
    }

    private String getLruTarget(Map<String, Integer> lruMap) {

        String lruTarget = "";
        int minValue = Integer.MAX_VALUE;

        for (String key : lruMap.keySet()) {

            int value = lruMap.get(key);

            if (minValue > value) {
                lruTarget = key;
                minValue = value;
            }
        }

        return lruTarget;
    }
}
