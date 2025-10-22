package programmers.lv2;

/* 프로그래머스 147354번 테이블 해시 함수

[문제 풀이]
col번째 값이 같다면 첫 번째 컬럼 기준 내림차순, col번째 값 기준 오름차순
컬럼마다 돌면서 i번째 행의 각 원소를 i로 나눈 나머지의 합을 구하고, 들어온 순서대로 XOR 누적
끝
*/

import java.util.*;

class Q147354_lth {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, (a, b) -> {
            if (a[col - 1] == b[col - 1]) {
                // col번째 값이 같다면 첫 번째 컬럼 기준 내림차순
                return b[0] - a[0];
            } else {
                // col번째 값 기준 오름차순
                return a[col - 1] - b[col - 1];
            }
        });
        
        for (int i = row_begin; i <= row_end; i++) {
            int sum = 0;
            for (int num : data[i - 1]) {  // i번째 행
                sum += num % i;
            }
            answer ^= sum;  // 들어온 순으로 XOR 누적
        }
        
        return answer;
    }
}