package programmers.lv2;

/* 프로그래머스 42586번 기능개발 문제

[문제 풀이]
큐에 해당 작업이 완료되는 날짜를 삽입
앞에 기능이 완성됐는데 그 날짜보다 먼저 완료된게 있으면 하나씩 더해가지고 완성된 기능 세고, 큰 게 나온 순간 break해서 answer 배열에 넣기

*/

import java.util.*;

class Q42586_kj {
    public int[] solution(int[] progresses, int[] speeds) {

        Queue<Integer> completeDays = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {

            int progress = progresses[i];
            int speed = speeds[i];

            // 남은 작업량을 완료할 수 있는 날짜를 저장
            double remainPercent = 100 - progress;
            completeDays.offer((int) Math.ceil(remainPercent / speed));
        }

        List<Integer> answerList = new ArrayList<>();
        while (!completeDays.isEmpty()) {

            // 가장 첫 기능부터 완료가 되어야 뒷 기능이 배포가 가능!
            int completeDay = completeDays.poll();
            int completeJobCount = 1;

            while (!completeDays.isEmpty()) {

                if (completeDays.peek() <= completeDay) {
                    // 뒷 기능이 앞 기능보다 빨리 완성됐을 경우, 함께 배포
                    completeDays.poll();
                    completeJobCount++;
                } else {
                    // 더 이상 배포할 기능이 없다면 break
                    break;
                }
            }

            // 배포 가능한 기능의 개수 저장
            answerList.add(completeJobCount);
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}