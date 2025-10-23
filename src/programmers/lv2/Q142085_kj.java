package programmers.lv2;

/* 프로그래머스 142085번 디펜스 게임 문제

[문제 풀이]
최대한 작은 적의 수를 병사로 막고, 큰 걸 무적권으로 막는게 이득
일단 무적권의 수만큼은 무조건 처리 가능(k 라운드)
새로 들어오는 적의 수와 이전에 들어온 적의 수(pq니까 작은 애부터!) 비교해서 작은 애를 먼저 병사로 처리하고, 큰 애는 다시 pq에 넣어서 순서대로 처리하기

*/

import java.util.*;

class Q142085_kj {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int e : enemy) {

            if (k > 0) {
                // 무적권이 있다면, 해당 라운드는 무조건 넘어갈 수 있으므로 pq에 담아두기
                k--;
                pq.offer(e);
            } else {
                int prevE = pq.poll();

                if (prevE < e) {
                    n -= prevE;
                    pq.offer(e);
                } else {
                    n -= e;
                    pq.offer(prevE);
                }

                if (n < 0) {
                    // 병사의 수가 부족하다면 종료
                    break;
                }
            }

            answer++;
        }

        return answer;
    }
}