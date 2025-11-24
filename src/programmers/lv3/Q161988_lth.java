package programmers.lv3;

/* 프로그래머스 161988번 연속 펄스 부분 수열의 합 문제

[문제 풀이]
끝까지 돌려서 나온 최댓값의 인덱스를 idx라고 하면 그 최댓값을 i부터 idx까지 앞을 지워가며 최댓값을 갱신하면 구할 수 있지 않을까 했지만
이 방법은 3, 7, 19, 20번의 테스트 케이스에서 실패하였다. 그 이유는 최댓값이 되는 구간이 여러개일 수 있기 때문이다.
-> 그래서 누적합과 최솟값을 이용하는 방법으로 바꾸었다.
*/

class Q161988_lth {
    public long solution(int[] sequence) {
        int n = sequence.length;
        // +1
        long best1 = maxPulseSum(sequence, 1);
        // -1
        long best2 = maxPulseSum(sequence, -1);

        return Math.max(best1, best2);
    }

    // pulse = 1 또는 -1
    private long maxPulseSum(int[] seq, int pulse) {
        long maxSum = Long.MIN_VALUE;
        long prefix = 0;         // 누적
        long minPrefix = 0;      // 최소값
        long sign = pulse;

        for (int x : seq) {
            prefix += sign * x;
            // 최댓값과 누적합-최솟값을 비교
            maxSum = Math.max(maxSum, prefix - minPrefix);
            // 다음 비교를 위해 prefix의 최솟값 갱신
            minPrefix = Math.min(minPrefix, prefix);
            // 부호 반전
            sign = -sign;
        }
        return maxSum;
    }
}
