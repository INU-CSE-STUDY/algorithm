package programmers.lv2;

/* 프로그래머스 12945번 피보나치 수

[문제 풀이]
fiboArr[n]을 1234567으로 나눠서 리턴했었지만 중간에 int값을 넘어가는지 실패를 하여
각 배열에 저장할때부터 1234567로 나눠서 실행한다
*/

class Q12945_lth {
    public int solution(int n) {
        int[] fiboArr = new int[n + 1];
        fiboArr[0] = 0;
        fiboArr[1] = 1;
        
        for(int i = 2; i < n + 1; i++){
            fibonacci(fiboArr, i); 
        }
        
        return fiboArr[n];
    }
    
    private void fibonacci(int[] fiboArr, int i){
        fiboArr[i] = (fiboArr[i-1] + fiboArr[i-2]) % 1234567;
    }
}