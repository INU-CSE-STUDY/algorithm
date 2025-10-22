package programmers.lv2;

/* 프로그래머스 147354번 테이블 해시 함수

[문제 풀이]
col 번째 컬럼의 값을 기준으로 오름차순 정렬, 만약 그 값이 동일하다면 첫번째 컬럼의 값 기준으로 내림차순 정렬
=> 이거는 뭐.. Arrays.sort로 col-1번째 기준 오름차순 정렬하고, .thenComparing으로 0번 인덱스 기준 내림차순 정렬하면 되지 않나


*/

import java.util.*;

class Q147354_kj {
    public int solution(int[][] data, int col, int row_begin, int row_end) {

        // 문제 조건에 맞게 정렬
        Arrays.sort(data,
                Comparator.comparingInt((int[] arr) -> arr[col - 1])
                        .thenComparing(
                                Comparator.comparingInt((int[] arr) -> arr[0]).reversed()
                        )
        );

        // 범위에 해당하는 부분만 조건에 맞게 연산 후 저장
        List<Integer> modSumList = new ArrayList<>();
        for (int i = row_begin - 1; i < row_end; i++) {
            int[] si = data[i];

            int modSum = 0;
            for (int num : si) {
                modSum += (num % (i + 1));
            }

            modSumList.add(modSum);
        }

        // XOR 연산을 통해 답을 구함
        int answer = 0;
        for (int i = 0; i < modSumList.size(); i++) {
            answer ^= modSumList.get(i);
        }

        return answer;
    }
}