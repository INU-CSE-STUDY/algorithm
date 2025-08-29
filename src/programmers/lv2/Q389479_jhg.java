package programmers.lv2;

import java.util.Arrays;

/**
 * 그냥 보자마자 구현으로 생각했다.
 * -> 24 * m * k를 해도 1억 미만이다.
 * -> 서버 가동 시간이 매우 짧으므로 요구사항에 맞게만 작성해도 통과할 거 같다. 다만 가동 시간이 길어지면.. 다른 방법으로 접근해야한다. 느리게 갱신되는 세그먼트 트리라던가?
 * 풀이
 * map은 그 시간대의 돌아가는 서버의 시간이라고 생각하면 된다.
 * 0시부터 23시까지 돌면서 증설된 서버만 체크해주면 된다.
 */
public class Q389479_jhg {

    int answer = 0;
    static final int LENGTH = 24;
    int[] map = new int[100];

    public int solution(final int[] players, final int m, final int k) {

        Arrays.fill(map, 1);

        for (int i = 0; i < LENGTH; i++) {
            if (players[i] >= map[i] * m) {
                int server = (int) Math.ceil((double) (1 + players[i] - map[i] * m) / m);
                answer += server;

                for (int j = i; j < i + k; j++) {
                    map[j] += server;
                }
            }
        }

        return answer;
    }
}
