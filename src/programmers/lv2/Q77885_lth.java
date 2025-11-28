package programmers.lv2;

/* 프로그래머스 77885번 2개 이하로 다른 비트

[문제 풀이]
짝수의 경우 마지막 비트 0 -> 1 = numbers + 1
홀수의 경우 0인 비트를 찾아서 그 비트를 1로, 그 전 비트를 0으로 변경
자바의 경우 비트 연산자를 지원하기 때문에 이를 활용해서 문제 풀이
*/

class Q77885_lth {
    public long[] solution(long[] numbers) {
        int n = numbers.length;
        long[] answer = new long[n];
        
        for(int i = 0; i < n; i++){
            answer[i] = calculate(numbers[i]);
        }
        
        return answer;
    }
    
    private long calculate(long number){
        // 짝수인 경우 numbers + 1
        if((number % 2) == 0) return number + 1;
        
        // 홀수인 경우
        long bit = 1;
        // 1인지를 확인하고 다음 비트로 이동해서 0인 비트를 찾기
        while((number & bit) != 0){
            bit <<= 1;  // 다음 비트로 이동 10 -> 100 -> 1000 ...
        }
        return number + bit - (bit >> 1);   // 0인 비트를 1로, 그 전 비트를 0으로 변경 10111 + 01000 - 00100 = 11011
    }
}