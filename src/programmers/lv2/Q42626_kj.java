package programmers.lv2;

/* 프로그래머스 42626번 더 맵게 문제

[문제 풀이]
가장 맵지 않은 음식이랑 두번째로 맵지 않은 음식을 계속 알아야 하니까 계속 정렬된 상태가 필요 -> 우선순위 큐 사용
1. 우선순위 큐 써서 스코빌 지수가 작은 순으로 정렬
2. 두 개 꺼내서 섞어서 넣기
3. 가장 앞에가 K 이상이 될 때까지 반복 - 안되는 경우에만 -1 반환

*/

import java.util.*;

class Q42626_kj {
    public int solution(int[] scoville, int K) {

        PriorityQueue<Integer> scovillePQ = new PriorityQueue<>();
        for (int scovilleScale : scoville) {
            scovillePQ.offer(scovilleScale);
        }

        boolean isPossibleCase = false;
        int answer = 0;
        while (!scovillePQ.isEmpty() && scovillePQ.peek() < K) {

            int food1 = scovillePQ.poll();

            // 음식이 하나밖에 안남았다면 섞을 수 없는데 남은 하나도 K 미만이므로 불가능한 경우
            if (scovillePQ.isEmpty()) return -1;

            // 음식이 1개 이상 남았다면 둘을 섞어서 스코빌 지수 높이기
            int food2 = scovillePQ.poll();
            scovillePQ.offer(food1 + (food2 * 2));
            answer++;
        }

        // 가능한 경우면 answer, 불가능한 경우면 -1 반환
        return answer;
    }
}