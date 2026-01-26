package programmers.lv2;

/* 프로그래머스 42746번 가장 큰 수 문제

[문제 풀이]
숫자들의 맨앞자리부터 비교하며 배열 길이가 짧을수록 우선순위 높음으로 정렬헸었는데 3과 34처럼 짧았을떄 우선순위가 낮아야하는 경우 발생
그래서 a + b vs b + a 로 비교하는 방식으로 변경. 0 이 여러개 올 수도 있으므로 가장 큰 값이 0 이면 "0" 반환
*/

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        // int -> String 변환
        String[] arr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }

        // (b+a) vs (a+b) 기준으로 내림차순 정렬
        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        // 가장 큰 값이 0 이면 00...00이 아닌 0을 반환해야함
        if (arr[0].equals("0")) return "0";

        // 이어붙이기
        StringBuilder sb = new StringBuilder();
        for (String s : arr) sb.append(s);

        return sb.toString();
    }
}
