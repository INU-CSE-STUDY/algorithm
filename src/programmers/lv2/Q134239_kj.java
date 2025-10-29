package programmers.lv2;

/* 프로그래머스 134239번 우박수열 정적분 문제

[문제 풀이]
1. 먼저 우박수열을 구해야 함(getSequence)
2. 구해진 우박수열 기준 구간별 넓이 구하기(누적합 이용)
   - 우박수열 구간별로 누적합 구하기 - 사다리꼴 넓이 구하는 공식 이용해서 한칸 한칸마다 넓이가 얼만지 저장
3. 구해진 누적합 배열 이용해서 문제에서 주어진 구간 별 정적분 결과 반환하기

*/

import java.util.*;

class Q134239_kj {
    public double[] solution(int k, int[][] ranges) {

        List<Integer> sequence = getSequence(k); // 우박수열 구하기
        double[] prefixSum = getPrefixSum(sequence); // 우박수열 구간별 넓이 구하기 (누적합)

        return getAnswer(prefixSum, sequence.size() - 1, ranges); // 누적합 이용해서 주어진 구간 내 정적분 결과 반환
    }

    private double[] getAnswer(double[] prefixSum, int n, int[][] ranges) {

        double[] answer = new double[ranges.length];

        for (int i = 0; i < ranges.length; i++) {
            int a = ranges[i][0];
            int b = n + ranges[i][1];

            // 시작점이 끝점보다 커서 유효하지 않은 구간은 -1, 그 외의 경우는 정적분 결과 반환
            answer[i] = (a > b) ? -1 : prefixSum[b] - prefixSum[a];
        }

        return answer;
    }

    private double[] getPrefixSum(List<Integer> sequence) {

        int size = sequence.size() - 1; // 전체 구간의 수는 수열 길이 - 1

        double[] prefixSum = new double[size + 1];
        for (int i = 1; i <= size; i++) {
            // 누적합으로 구간별 넓이 구하기
            prefixSum[i] = getArea(sequence.get(i - 1), sequence.get(i)) + prefixSum[i - 1];
        }

        return prefixSum;
    }

    private List<Integer> getSequence(int k) {
        // 우박수열을 구하는 함수
        List<Integer> sequence = new ArrayList<>();

        // 시작값 넣기
        sequence.add(k);

        while (k != 1) {
            if (k % 2 == 0) {
                // k가 짝수라면 2로 나누기
                k = k / 2;
            }
            else {
                // k가 홀수라면 3을 곱하고 1을 더하기
                k = k * 3 + 1;
            }

            sequence.add(k);
        }

        return sequence;
    }

    private double getArea(int upper, int lower) {
        // 각 구간별로 나타나는 사다리꼴 넓이 구하기, 구간의 높이는 항상 1이어서 생략해도 되지만.. 그냥 남겨둠
        return (upper + lower) * 1 / 2d;
    }
}