package programmers.lv2;

import java.util.Arrays;

// 아무리 봐도 누적합 같아서 누적합으로 풀었다.
public class Q155651_jhg {

    public int solution(String[][] book_time) {
        // 전체 시간 0 ~ 1540
        int totalTime = 24 * 60 * 60 + 100;
        int[] array = new int[totalTime];
        int[] prefixSum = new int[totalTime + 1];

        for (String[] row : book_time) {
            int startTime = getSecond(row[0]);
            // 청소 시간 고려
            int endTime = getSecond(row[1]) + 10;
            array[startTime]++;
            array[endTime] = array[endTime] - 1;
        }

        // 누적합 계산
        for (int i = 0; i < totalTime; i++) {
            prefixSum[i + 1] = prefixSum[i] + array[i];
        }

        // 최대 찾기
        return Arrays.stream(prefixSum).max().getAsInt();
    }

    private static int getSecond(String time) {
        String[] times = time.split(":");
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }
}
