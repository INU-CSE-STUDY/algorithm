package programmers.lv2;

import java.util.*;

// pq같다.
public class Q142085_jhg {

    public int solution(int n, int k, int[] enemy) {
        int answer = 1;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int now : enemy) {
            n -= now;
            pq.add(now);

            if (n < 0) {
                if (k == 0) {
                    break;
                }

                n += pq.poll();
                k--;
            }
            answer++;
        }

        return answer;
    }
}
