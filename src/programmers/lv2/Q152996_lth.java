package programmers.lv2;

/* 프로그래머스 152996번 시소 짝꿍 문제

[문제 풀이]
map 자료구조 만들어서 배열에 있는 값 2, 3, 4 곱한거 다 넣기
getOrDefault 써서 같은 숫자 몇개 있는지 카운팅하면 순서쌍 몇개 나오는지 알 수 있으니까!
겹치는 부분이 있어서 그거 빼주는 작업 필요
*/

import java.util.*;

class Q152996_lth {
    public long solution(int[] weights) {
        Map<Long, Long> momentCnt = new HashMap<>(); // 키: 2w,3w,4w / 값: 해당 키 개수
        Map<Integer, Long> weightCnt = new HashMap<>(); // 같은 무게 개수 (보정용)

        long pairs = 0;

        for (int w : weights) {
            long m2 = 2L * w;
            long m3 = 3L * w;
            long m4 = 4L * w;

            // 삽입 전에 현재까지의 동일 키 개수를 더하면, (나와 만날 수 있는 기존 사람 수) 만큼 쌍이 생김
            pairs += momentCnt.getOrDefault(m2, 0L);
            pairs += momentCnt.getOrDefault(m3, 0L);
            pairs += momentCnt.getOrDefault(m4, 0L);

            // 이제 나를 등록
            momentCnt.merge(m2, 1L, Long::sum);
            momentCnt.merge(m3, 1L, Long::sum);
            momentCnt.merge(m4, 1L, Long::sum);

            // 같은 무게 카운트 (보정용)
            weightCnt.merge(w, 1L, Long::sum);
        }

        // 같은 무게 쌍은 3번 잡혔으니 2*cC2 만큼 빼기
        long over = 0;
        for (long c : weightCnt.values()) {
            if (c >= 2) {
                long comb = c * (c - 1) / 2; // cC2
                over += 2 * comb;
            }
        }

        return pairs - over;
    }
}