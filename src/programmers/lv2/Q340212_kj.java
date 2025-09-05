package programmers.lv2;

/* 프로그래머스 340212번 퍼즐 게임 챌린지 문제

[문제 풀이]
diff <= level: time_curr
diff > level: (diff - level) * (time_curr + time_prev) + time_curr

이분탐색으로 풀면 풀리나?
난이도의 최소와 최대의 합의 중간을 mid로 잡고, 실제로 계산해서 결과랑 비교
limit > 결과값 -> 숙련도를 올려야함
limit <= 결과값 -> 숙련도를 내려도 되긴 함 + 이게 result일 수도 있으므로 따로 저장하기
*/

class Q340212_kj {
    public int solution(int[] diffs, int[] times, long limit) {
        long left = Integer.MAX_VALUE;
        long right = Integer.MIN_VALUE;
        for (int i = 0; i < diffs.length; i++) {
            left = Math.min(left, diffs[i]);
            right = Math.max(right, diffs[i]);
        }

        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;

            long time = getTotalTimes(diffs, times, mid);

            if (time > limit) {
                // 시간 내에 문제를 풀지 못했으므로, mid 값을 올려야 함
                left = mid + 1;
            } else {
                // 시간 내에 문제를 풀었으면, mid 값을 내려도 됨
                right = mid - 1;

                // 현재값이 최선일 수 있으므로, 따로 저장
                answer = mid;
            }
        }

        return (int) answer;
    }

    private long getTotalTimes(int[] diffs, int[] times, long level) {
        long time = 0;
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                time += times[i];
            } else {
                time += ((diffs[i] - level) * (times[i] + times[i - 1]) + times[i]);
            }
        }

        return time;
    }
}