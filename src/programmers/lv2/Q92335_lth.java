package programmers.lv2;

/* 프로그래머스 92335번 k진수에서 소수 개수 구하기 문제

[문제 풀이]
일단 k진수로 바꾸고 배열로 저장, 소수에는 0이 안 들어가고 0을 기준으로 소수를 구분하니
저장시에 0을 기준으로 잘라서 배열에 저장
소수 판별은 그냥 단순 반복문으로 구현해서 소수이면 카운트 증가
*/



import java.util.*;

class Q92335_lth {
    public int solution(int n, int k) {
        int answer = 0;
        String decimal = Integer.toString(n, k);
        String[] parts = decimal.split("0");
        
        for(String part : parts){
            if (!part.isEmpty() && isDecimal(part)){
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean isDecimal(String part){
        long num = Long.parseLong(part);
        if (num < 2) return false;

        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }
}