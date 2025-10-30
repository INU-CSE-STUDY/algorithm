package programmers.lv2;

/* 프로그래머스 134239번 우박수열 정적분 문제

[문제 풀이]
각 구간마다의 정적분결과(넓이)를 구하고 double로 넣고 꺼내쓰기
y의 값은 k부터 시작하여 while문으로 1이될때까지 실행하며 
while문에서 next를 구하고 (k+next)/2를 배열에 넣고 k를 next로 변경
*/


import java.util.*;

class Q134239_lth {
    public double[] solution(int k, int[][] ranges) {
        List<Double> extent = calculate(k);
        int n = ranges.length;
        int m = extent.size();
        double[] answer = new double[n];
        
        for(int i = 0; i < n; i++){
            int start = ranges[i][0];
            int end = m + ranges[i][1];

            if (start < 0 || end > m || start > end) {
                answer[i] = -1.0;
                continue;
            }
            
            double sum = 0.0;
            for (int j = start; j < end; j++) {
                sum += extent.get(j);
            }
            answer[i] = sum;
        }
        return answer;
    }
    
    private List<Double> calculate(int k){
        double cur = k;
        List<Double> extent = new ArrayList<>();
        
        while (cur != 1) {
            double next = (isOdd((int)cur)) ? cur * 3 + 1 : cur / 2;
            extent.add((cur + next) / 2.0);
            cur = next;
        }
        return extent;
    }
    
    private boolean isOdd(int k){
        return k % 2 != 0;
    }
}