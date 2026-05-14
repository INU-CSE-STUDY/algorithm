// 연속된 i개의 합: n = x + (x + 1) + ... + (x + i − 1)
// 여기서 i의 값이 자연수라면 n에 대한 i개의 합이 성립
// x에 대해 정리하면 n - (i * (i - 1) / 2);

class Q12924_lth {
    public int solution(int n) {
        int answer = 0;
        
        for(int i = 1; i * (i - 1) / 2 < n; i++){
            int temp = n - (i * (i - 1) / 2);

            if(temp % i == 0){
                answer++;
            }
        }
        
        return answer;
    }
}