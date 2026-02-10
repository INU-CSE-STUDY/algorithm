package programmers.lv2;

/* 프로그래머스 42587번 프로세스 문제

[문제 풀이]
큐에 전부 다 넣고, 하나씩 꺼내서 확인해보기!
자기보다 우선순위가 큰 친구가 존재하면 지금 실행되면 안되니까 큐의 가장 뒤로 일단 보내기
우선순위가 큰 친구가 없으면 지금 실행되어야 하니까 실행.
실행되면 answer 증가시키고(실행순서는 1번부터니까) 실행된 프로세스가 우리가 찾는 위치의 프로세스면 종료하기

*/

import java.util.*;

class Q42587_kj {
    public int solution(int[] priorities, int location) {

        Queue<Process> processQueue = new LinkedList<>();
        for (int index = 0; index < priorities.length; index++) {
            processQueue.offer(new Process(index, priorities[index]));
        }

        int answer = 0;
        while (!processQueue.isEmpty()) {

            Process now = processQueue.poll();

            // 더 높은 우선순위를 가진 아이가 있는지 확인
            boolean hasHigher = false;
            for (Process p : processQueue) {
                if (p.priority > now.priority) {
                    hasHigher = true;
                    break;
                }
            }

            if (hasHigher) {
                processQueue.offer(now);
            } else {
                answer++;

                if (now.index == location) break;
            }
        }

        return answer;
    }

    class Process {

        int index;
        int priority;

        public Process(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
}
