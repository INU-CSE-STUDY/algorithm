package programmers.lv2;

/* 프로그래머스 154539번 뒤에 있는 큰 수 찾기 문제

[문제 풀이]
정답 배열을 -1로 초기화
처음 만난 수부터 인덱스와 값을 가진 객체를 우선 순위 큐에 넣으며 
큐의 가장 작은 수보다 큰 수가 오면 그 객체를 빼고 큰 수를 큐에 넣으며
그 인덱스에 큰 수의 값을 넣음 남은 객체 들은 뒤에 큰 수가 없는 것임으로 -1로 둠
*/

import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        
        
        // 값이 작은 순서대로 나오는 우선순위 큐
        PriorityQueue<Number> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));
        pq.offer(new Number(0, numbers[0]));
        
        // 처음 만난 수부터 객체를 우선 순위 큐에 넣으며 큰 수가 오면 그 객체를 빼고 큰 수를 큐에 넣으며 그 인덱스에 큰 수의 값을 넣음
        // 작은 수가 여러 개면 다 빼고 큰 수를 넣어줌
        for(int i = 1; i < n; i++){
            while (!pq.isEmpty() && pq.peek().value < numbers[i]) {
                Number smaller = pq.poll();
                answer[smaller.idx] = numbers[i];
            }
            pq.offer(new Number(i, numbers[i]));
        }
        
        return answer;
    }
    
    class Number{
        int idx;
        int value;
        public Number(int idx, int value){
            this.idx = idx;
            this.value = value;
        }
    }
}