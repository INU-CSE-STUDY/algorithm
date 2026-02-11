package programmers.lv2;

/* 프로그래머스 42586번 기능개발 문제

[문제 풀이]
큐에 필요한 날짜들을 순서대로 넣고 꺼낸 날짜보다 뒤에 날짜가 작으면 계속 꺼내서 카운트
뒤에 날짜가 더 크다면 지금까지의 카운트를 저장

*/

import java.util.*;

class Q42586_lth {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answerList = new ArrayList<>();
        
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < progresses.length; i++) {
            if((100 - progresses[i]) % speeds[i] > 0){
                q.offer((100 - progresses[i]) / speeds[i] + 1);
            }else{
                q.offer((100 - progresses[i]) / speeds[i]);
            }
        }
        while(!q.isEmpty()){
            int count = 1;
            int cur = q.poll();
            while(!q.isEmpty()){
                 if (q.peek() <= cur) {
                    q.poll();
                    count++;
                }else{
                    break;
                }
            }
            answerList.add(count);
        }
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}