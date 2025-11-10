package programmers.lv3;

/* 프로그래머스 214288번 상담원 인원 문제

[문제 풀이]
n명의 멘토를 잘 분배해서 대기 시간을 최소로 만들기
-> 멘토가 모두 1명일 때를 기준으로 대기 시간이 긴 친구들부터 차례차례 배분해주기..??

> 유형 별로 멘토는 적어도 1명 이상이어야 하므로 분배해줘야 하는 멘토 수는 (n - k) 명 (= remainMento)
> PriorityQueue를 이용해서 상담 유형 별 대기 시간(int[]{ 상담 유형, 참가자의 총 대기 시간, 배정된 멘토 수 }) 저장
  - 내림차순으로 정렬해서 대기 시간이 가장 긴 사람부터 멘토를 한명씩 더 붙여주기.
    멘토 한명씩 늘어날 때마다 대기 시간 새로 구하고 다시 pq에 넣는거 반복
=> 최종적으로 남아있는 멘토(remainMento) 수가 0명이 되었을 때가 대기 시간이 최소일테니까 pq에 저장되어있는 대기 시간 총합해서 반환!

---
참가자의 총 대기 시간만을 보고 멘토를 더 추가하기 << 라는 아이디어가 잘못되었다,,
사람을 한명 더 늘렸을 때 대기시간이 얼마나 줄어드는지가 이 문제에서의 핵심이라 이걸 고려한 pq로 변경

정렬 조건을
[참가자의 총 대기 시간] -> [멘토가 한명 더 늘었을 때 대기 시간이 얼마나 감소하는지(현재 대기시간 - 멘토 1명 늘어났을 때의 대기시간)] 로 변경
*/

import java.util.*;

class Q214288_kj {

    List<List<int[]>> reqsList; // 상담 유형별로 요청 시간과 상담 시간을 저장할 리스트
    int remainMento; // 남아있는 멘토의 수
    PriorityQueue<int[]> waitingTimeQueue; // int[]{ 상담 유형, 참가자의 총 대기 시간, 배정된 멘토 수 }

    final int INIT_MENTO_NUM = 1; // 상담 유형별 초기 멘토의 수 (적어도 1명 이상 배치해야 하므로 1)

    public int solution(int k, int n, int[][] reqs) {
        int answer = 0;

        initReqsList(reqs, k); // 상담 유형별로 참가자 요청 시간과 상담 시간 저장
        initWaitingTimeQueue(); // 상담 유형별 참가자의 대기 시간, 배정된 멘토 수 저장
        remainMento = n - k; // 각 상담 유형별로 적어도 1명의 멘토가 배치되어야 하므로, 한 명씩 배정

        while (remainMento != 0) {

            // 대기시간 감소가 가장 큰 경우가 나오므로, 여기에 멘토 수를 한명 더 배정
            int[] typeInfo = waitingTimeQueue.poll();

            remainMento--;

            int type = typeInfo[0];
            int mentoNum = typeInfo[1] + 1;
            int nowWaitingTime = typeInfo[3]; // 기존의 nextWaitingTime을 now로 업데이트
            int nextWaitingTime = getWaitingTime(reqsList.get(type), mentoNum);


            waitingTimeQueue.offer(new int[]{ type, mentoNum, nowWaitingTime, nextWaitingTime });
        }

        while (!waitingTimeQueue.isEmpty()) {
            int[] typeInfo = waitingTimeQueue.poll();

            int waitingTime = typeInfo[2];

            answer += waitingTime;
        }

        return answer;
    }

    private void initReqsList(int[][] reqs, int k) {

        // reqsList 초기화(상담 유형 개수 맞춰서 초기화)
        reqsList = new ArrayList<>();
        for (int type = 0; type < k; type++) {
            reqsList.add(new ArrayList<int[]>());
        }

        for (int[] req : reqs) {
            int a = req[0]; // 요청 시간
            int b = req[1]; // 상담 시간
            int c = req[2]; // 상담 유형

            // 상담 유형별로 요청 시간, 상담 시간 저장
            reqsList.get(c - 1).add(new int[]{ a, b });
        }
    }

    private void initWaitingTimeQueue() {

        waitingTimeQueue = new PriorityQueue<>(
                Comparator.comparingInt((int[] arr) -> arr[2] - arr[3]).reversed()
        ); // 상담 유형별 참가자의 대기 시간을 저장하는 우선순위 큐
        // 대기 시간 감소가 큰 상담 유형에 멘토를 넣어야 대기 시간이 최소가 되므로 내림차순 정렬

        for (int type = 0; type < reqsList.size(); type++) {
            List<int[]> requests = reqsList.get(type);

            int nowWaitingTime = getWaitingTime(requests, INIT_MENTO_NUM);
            int nextWaitingTime = getWaitingTime(requests, INIT_MENTO_NUM + 1);

            waitingTimeQueue.offer(new int[]{ type, INIT_MENTO_NUM + 1, nowWaitingTime, nextWaitingTime });
        }
    }

    private int getWaitingTime(List<int[]> requests, int mentoNum) {
        // 각 멘토별로 상담이 종료되는 시간을 관리하는 우선순위 큐
        // 빨리 끝나는 멘토에게 상담을 받는게 대기 시간이 최소인 것이므로 오름차순 정렬(기본)
        PriorityQueue<Integer> finishTimeQueue = new PriorityQueue<>();
        for (int i = 0; i < mentoNum; i++) {
            finishTimeQueue.offer(0); // 시작할 땐 다들 놀고 있으니 멘토 수만큼 0으로 넣기
        }

        int totalWaitingTime = 0;
        for (int participant = 0; participant < requests.size(); participant++) {

            int[] nowRequest = requests.get(participant);
            int a = nowRequest[0];
            int b = nowRequest[1];

            int nowFinishTime = finishTimeQueue.poll();

            // 멘토 수만큼의 참가자는 대기 없이 상담 가능
            // 멘토의 종료 시간보다 더 늦게 상담 요청한 경우도 대기 없이 상담 가능
            if (participant < mentoNum || nowFinishTime <= a) {
                nowFinishTime = (a + b);
            } else {
                totalWaitingTime += nowFinishTime - a; // 대기 시간 업데이트
                nowFinishTime += b; // 멘토의 상담 종료 시간 업데이트
            }

            finishTimeQueue.offer(nowFinishTime);
        }

        return totalWaitingTime;
    }
}
