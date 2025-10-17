package programmers.lv2;

/* 프로그래머스 148653번 마법의 엘리베이터 문제

[문제 풀이]
각 자릿수를 배열에 넣음
일의 자리부터 5보다 크면 0이 되도록 더한 후
십의 자리에 1더한 값으로 계산 십의 자리도 5보다 크면 마찬가지로 계산
5일 때는 그 다음 자릿수가 5 이상인지도 확인
그렇게 자릿수만큼 계산
*/

class 148653_lth {
    public int solution(int storey) {
        int answer = 0;
        int n = String.valueOf(storey).length();
        int[] digits = new int[n+1];
        digits[n] = 0;
        
        for(int i = n - 1; i >= 0; i--) {
            digits[i] = storey % 10;
            storey /= 10;             
        }
        
        for(int i = 0; i <= n; i++){
            if(digits[i] > 5){
                answer += (10 - digits[i]);
                digits[i+1] += 1;
            }else if(digits[i] == 5 && digits[i+1] >= 5){
                answer += (10 - digits[i]);
                digits[i+1] += 1;
            }else{
                answer += digits[i];
            }
        }
        
        return answer;
    }
}