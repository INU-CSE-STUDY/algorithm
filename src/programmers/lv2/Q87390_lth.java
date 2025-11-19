package programmers.lv2;

/* 프로그래머스 87390번 n^2 배열 자르기 문제

[문제 풀이]
n^2 배열에서 left부터 right까지 자른 배열을 구하는 문제
배열에서 i,j번째 원소는 max(i,j)+1 이라는 규칙이 있다
*/


class Q87390_lth {
    public int[] solution(int n, long left, long right) {
        int length = (int) (right - left) + 1;
        int[] answer = new int[length];
        
        for(int i = 0; i < length; i++){
            long idx = left + i;
            int row = (int) (idx / n);
            int col = (int) (idx % n);
            
            answer[i] = Math.max(row, col) + 1;
        }
        
        return answer;
    }
}