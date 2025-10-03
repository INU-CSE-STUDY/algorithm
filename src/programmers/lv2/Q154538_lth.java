package programmers.lv2;

/* 프로그래머스 154539번 뒤에 있는 큰 수 찾기 문제

[문제 풀이]
문제를 보면 최단 횟수 = bfs 
처음에는 x에서 y로 가는 bfs를 생각하고 x부터 3가지씩 실행하며 y에 도달하면 그 횟수를 반환
하지만 메모리 초과가 나서 반대로 y에서 x로 가는 bfs로 바꿈
*/

import java.util.*;

class Q154538_lth {
    public int solution(int x, int y, int n) {
        int answer = 0;
        answer = bfs(x, y, n);
        
        return answer;
    }
    
    class Result{
        int value;
        int number;
        public Result(int value, int number){
            this.value = value;
            this.number = number;
        }
    }
    
    private int bfs(int x, int y, int n){
        Deque<Result> dq = new ArrayDeque<>();
        dq.offer(new Result(y, 0));
        
        while(!dq.isEmpty()){
            Result r = dq.poll();
            if(r.value == x){
                return r.number;
            }
            
            if(r.value - n >= x){
                dq.offer(new Result(r.value - n, r.number + 1));
            }
            if(r.value / 2 >= x && r.value % 2 == 0){
                dq.offer(new Result(r.value / 2, r.number + 1));
            }
            if(r.value / 3 >= x && r.value % 3 == 0){
                dq.offer(new Result(r.value / 3, r.number + 1));
            }
        }
        return -1;
    }
}