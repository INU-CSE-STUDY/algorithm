package programmers.lv3;

/* 프로그래머스 161988번 연속 펄스 부분 수열의 합 문제

[문제 풀이]
[1, -1, 1, -1, ...] 또는 [-1, 1, -1, 1, ...] 로 이루어진 펄스 수열을 곱했을 때 합이 최대가 되는 구간 찾기 문제

이거 어떻게 곱하든 결국에 서로는 반전되는 값이니까 [1, -1] 순서로 반복되는 거로 다 곱한 다음에, 부분 수열 합 min/max 값 구해서 Math.abs로 비교 한다음에 합 반환하면 되는 거 아닐까??

*/

import java.util.*;

class Q161988_kj {

    public long solution(int[] sequence) {

        long[] pulseSequence = initPulseSequence(sequence);

        long answerMax = Long.MIN_VALUE;
        long answerMin = Long.MAX_VALUE;
        long currentMaxSum = 0;
        long currentMinSum = 0;

        for (long num : pulseSequence) {

            currentMaxSum = Math.max(currentMaxSum + num, num); // [이전까지의 최대 부분 합 + 현재값] 이랑 [현재값] 중 최댓값 선택
            answerMax = Math.max(answerMax, currentMaxSum); // 부분 수열의 최대합 업데이트

            currentMinSum = Math.min(currentMinSum + num, num); // [이전까지의 최소 부분 합 + 현재값], [현재값] 중 최솟값 선택
            answerMin = Math.min(answerMin, currentMinSum); // 부분 수열의 최소합 업데이트
        }

        // 최댓값과 최솟값 중 절댓값이 큰 값 반환(최솟값의 절댓값이 크단 말은 [-1, 1, ...] 펄스 배열 곱하면 최대라는 뜻이니까!)
        return Math.max(Math.abs(answerMax), Math.abs(answerMin));
    }

    private long[] initPulseSequence(int[] sequence) {

        long[] pulseSequence = new long[sequence.length];
        int pulse = 1;
        for (int i = 0; i < sequence.length; i++) {
            pulseSequence[i] = (long) sequence[i] * pulse;
            pulse *= -1;
        }

        return pulseSequence;
    }
}
