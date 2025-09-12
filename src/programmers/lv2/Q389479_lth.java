package programmers.lv2;

/* 프로그래머스 389479번 서버 증설 횟수 문제

[문제 풀이]
배열을 하나 만들어서 각 시간대에 몇 개의 서버가 추가되어야 하는지 기록
추가할 때 answer에도 값을 추가해서 최종적으로 answer를 return
*/


class Solution {
    public int solution(int[] players, int m, int k) {
        int n = players.length;
        int answer = 0;
        //길이가 plqyers.length인 count 배열 생성
        int[] count = new int[players.length];
        
        //m + count[i]*k 보다 players[i]가 같거나 크다면 count 배열[i]부터 [i+k]에 + ((players[i] - count[i]*k)/k)
        for(int i = 0; i < n; i++){
            if(m + count[i]*m <= players[i]){
                int add = (players[i] - count[i]*m) / m;
                for(int j = 0; j < k; j++){ 
                    if(i+j < n){
                        count[i+j] += add; 
                    }
                } 
                answer += add;
            }
        }
        
        return answer;
    }
    
}