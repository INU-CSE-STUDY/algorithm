package programmers.lv2;
/* 프로그래머스 176962번 과제 진행하기 문제

[문제 풀이]
시간 순서대로 정렬이 되어있는 줄 알았지만 안 되어 있어서 일단 정렬을 한다
늦게 들어온게 먼저 나간다? 스택이다
그냥 스택에 넣고 다음 과제 시작시간과 현재 과제 시작시간의 차이만큼 빼주면서
진행하다가 다 못 끝내면 다시 스택에 넣고 다음 과제로 넘어간다
다음 과제가 없으면 마지막 문제의 시간을 최대값으로 해서 다 끝날 때까지 진행
*/

import java.util.*;

class Q176962_lth {
    public String[] solution(String[][] plans) {
        int n = plans.length;
        
        //sort는 신이야
        Arrays.sort(plans, (a, b) -> toMinutes(a[1]) - toMinutes(b[1]));
        List<String> answerList = new ArrayList<>();
        // 스택 생성
        Deque<Assignment> stack = new ArrayDeque<>();
        
        // 과제 시작 시간 순서대로 처리
        for(int i = 0; i < n; i++){
            String subjects = plans[i][0];
            int start = toMinutes(plans[i][1]);
            int remain = Integer.parseInt(plans[i][2]);
            
            stack.push(new Assignment(subjects, remain));
            
            // 다음 과제 시작 시간이 범위를 초과하면 최대값으로 설정
            int nextStart = (i + 1 < n) ? toMinutes(plans[i + 1][1]) : Integer.MAX_VALUE;
            int gap = nextStart - start;
            
            // 다음 시간과의 차이만큼 스택에서 과제를 빼면서 진행
            while (gap > 0 && !stack.isEmpty()) {
                Assignment cur = stack.pop();
                if (cur.remain <= gap) {
                    gap -= cur.remain;
                    answerList.add(cur.subjects);   // 과제 완료
                } else {
                    cur.remain -= gap;
                    stack.push(cur);        // 과제 미완료, 다시 스택에 넣기
                    gap = 0;
                }
            }
        }
        // 혹시 스택에 남아있으면 처리
        while (!stack.isEmpty()) {
            answerList.add(stack.pop().subjects);
        }
        
        return answerList.toArray(new String[0]);
    }
    
    static int toMinutes(String time) {
        String[] p = time.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }
    
    //과제 객체 생성
    class Assignment{
        String subjects;
        int remain;
        public Assignment(String subjects, int remain){
            this.subjects = subjects;
            this.remain = remain;
        }
    }
}