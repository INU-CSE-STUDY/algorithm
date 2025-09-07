package programmers.lv2;

import java.util.Arrays;

/**
 * 5억 미터 밖에서 봐도 이분탐색인 거 같았다.
 * 이분 탐색 VS DP의 차이점만 생각하면 쉽게 푼다.
 */
public class Q340212_jhg {
    public int solution(int[] diffs, int[] times, long limit) {
        long low = Arrays.stream(diffs).min().orElse(0);
        long height = Arrays.stream(diffs).max().orElse(100000);

        long answer = 0;
        while (low <= height) {
            long mid = (low + height) / 2;

            long time = simulation(diffs, times, mid);

            if (time > limit) {
                low = mid + 1;
            } else {
                height = mid - 1;
                answer = mid;
            }
        }

        return (int) answer;
    }

    private long simulation(int[] diffs, int[] times, long level) {
        long time = 0;
        for (int i = 0; i < diffs.length; i++) {
            time += times[i];

            if (diffs[i] > level) {
                time += (diffs[i] - level) * (times[i] + times[i - 1]);
            }
        }

        return time;
    }
}
