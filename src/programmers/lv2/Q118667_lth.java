package programmers.lv2;

/* 프로그래머스 118667번 두 큐 합 같게 만들기 문제

[문제 풀이]
일단 각 큐를 더해서 각각 얼마인지와 총얼마인지 계산하고 총값의 반만큼이 목표값이다
그 다음에 리스트를 큐로 바꿔서 문제의 요구사항을 구현하고
첫번째큐의 값만 목표값과 비교해서 크면 첫번째큐에서 빼서 두번째큐에 넣고
작으면 두번째큐에서 빼서 첫번째큐에 넣는 작업을 반복한다
-> 큐의 길이의 2배를 하면 한쪽큐를 다 옮기고 다시 반대쪽큐를 다 옮기는 경우까지 할수있는데 그 이상하면 불가능한 경우이므로 -1을 반환했었지만
1번 테케에서 [1, 1, 1, 1], [1, 1, 7, 1]가 2배안에 해결이 안되서 3배로 늘렸다
*/



import java.util.*;

class Q118667_lth {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long oneSum = sumQueue(queue1);
        long twoSum = sumQueue(queue2);
        long totalSum = (oneSum + twoSum)/2;
        
        Queue<Integer> oneQ = makeQueue(queue1);
        Queue<Integer> twoQ = makeQueue(queue2);
        
        while(answer <= 3 * queue1.length){
            if(oneSum == totalSum){
                return answer;
            }else if(oneSum > totalSum){
                int cnt = oneQ.poll();
                twoQ.offer(cnt);
                oneSum -= cnt;
                answer++;
            }else{
                int cnt = twoQ.poll();
                oneQ.offer(cnt);
                oneSum += cnt;
                answer++;
            }
        }
        
        return -1;
    }
    
    private long sumQueue(int[] queue){
        long sum = 0L;
        for(int que : queue){
            sum += que;
        }
        return sum;
    }
    
    private Queue<Integer> makeQueue(int[] queue){
        Queue<Integer> q = new ArrayDeque<>();
        for(int que : queue){
            q.offer(que);
        }
        return q;
    }
}