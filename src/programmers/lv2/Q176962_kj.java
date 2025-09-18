package programmers.lv2;/* 프로그래머스 176962번 과제 진행하기 문제

[문제 풀이]
새로운 과제를 시작할 시각이 되었을 때, 기존에 진행 중이던 과제가 있다면 진행 중이던 과제를 멈추고 새로운 과제를 시작
- 일단 plans 배열을 시작 시간 기준으로 정렬해서 그 순서대로 시작하기!
- 기존에 진행 중이던 과제는 따로 저장하기
  -> stack에 저장할 예정. because 멈춰둔 과제가 여러 개일 경우, 가장 최근에 멈춘 과제부터 해야 하니까
*/

import java.util.*;

class Q176962_kj {
    Stack<Homework> remain = new Stack<>();
    List<String> answer = new ArrayList<>();
    public String[] solution(String[][] plans) {

        // 시작 시간 기준으로 정렬
        Arrays.sort(plans, Comparator.comparing((String[] s) -> s[1]));

        int index = 0;
        Homework now = new Homework(plans[index++]);

        while (index < plans.length) {
            Homework next = new Homework(plans[index++]);

            if (now.endTime <= next.startTime) {
                answer.add(now.name);

                int freeTime = next.startTime - now.endTime;

                while (!remain.isEmpty() && freeTime > 0) {
                    Homework remainWork = remain.pop();
                    if (remainWork.playTime <= freeTime) {
                        answer.add(remainWork.name);
                        freeTime -= remainWork.playTime;
                    } else {
                        remainWork.updateTime(freeTime);
                        remain.push(remainWork);
                        freeTime = 0;
                    }
                }
            } else {
                now.updateTime(next);
                remain.push(now);
            }
            now = next;
        }
        answer.add(now.name);

        while (!remain.isEmpty()) {
            answer.add(remain.pop().name);
        }

        return answer.toArray(new String[0]);
    }
}

class Homework {
    String name; // 과제명
    int startTime; // 과제 시작 시각
    int playTime; // 과제를 완료하는데 걸리는 시간
    int endTime; // 과제 종료 시각

    public Homework(String[] plan) {
        this.name = plan[0];
        this.startTime = getMinutes(plan[1]);
        this.playTime = Integer.parseInt(plan[2]);
        this.endTime = this.startTime + this.playTime;
    }

    private int getMinutes(String start) {
        String[] time = start.split(":");
        int hh = Integer.parseInt(time[0]);
        int mm = Integer.parseInt(time[1]);

        return hh * 60 + mm;
    }

    public void updateTime(Homework next) {
        this.startTime = next.startTime;
        this.playTime = this.endTime - next.startTime;
        this.endTime = this.startTime + this.playTime;
    }

    public void updateTime(int minute) {
        this.startTime += minute;
        this.playTime -= minute;
        this.endTime = this.startTime + this.playTime;
    }
}