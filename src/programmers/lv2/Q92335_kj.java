package programmers.lv2;

/* 프로그래머스 92335번 k진수에서 소수 개수 구하기 문제

[문제 풀이]
결국엔 n을 k진수로 바꿨을 때 0 기준으로 split해서 소수인지 판별하면 되는 아주 쉬운 문제!

n이 1,000,000이라 진수변환했을 때 아주 큰 수가 나올 수 있어서 long 타입 고려했어야 했던 문제
*/

import java.util.*;

class Q92335_kj {
    public int solution(int n, int k) {
        int answer = 0;

        String convertToBaseK = Integer.toString(n, k);

        String[] numArr = convertToBaseK.split("0");
        for (String numStr : numArr) {
            if (numStr.equals("")) continue; // 빈 문자열이면 그냥 넘어가기

            Long number = Long.parseLong(numStr);
            if (isPrime(number)) answer++;
        }

        return answer;
    }

    private boolean isPrime(Long number) {
        if (number < 2) return false;

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }

        return true;
    }
}
