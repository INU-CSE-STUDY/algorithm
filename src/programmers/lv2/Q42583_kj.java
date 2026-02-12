package programmers.lv2;

/* 프로그래머스 42583번 다리를 지나는 트럭 문제

[문제 풀이]
뭔 문제가 다 스택/큐 문제네
1. queue.isEmpty()면 트럭 하나 올리기
2. 하나라도 올라가있으면 queue.size()랑 bridge_length 비교하기
   - 트럭 더 올라갈 수 있으면 무게 비교해서 올리기(bridgeWeight 같은데 저장해두기)
   - 못 올라가면 그냥 넘기기.
3. 이 과정을 모두 time++ 하면서 1초씩 진행하기??
   - 도착 시간을 저장해서 시간 지나고 뺄 수 있는거 빼고 1, 2번 계속 반복하기

*/

import java.util.*;

class Q42583_kj {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        int time = 1;
        int bridgeWeight = truck_weights[0];

        Queue<int[]> bridgeQueue = new LinkedList<>();
        bridgeQueue.add(new int[]{ time + bridge_length, bridgeWeight });

        int index = 1;
        while (!bridgeQueue.isEmpty()) {

            time++;

            if (!bridgeQueue.isEmpty()) {

                int[] truckInfo = bridgeQueue.peek();

                if (truckInfo[0] == time) {
                    bridgeQueue.poll();
                    bridgeWeight -= truckInfo[1];
                }
            }

            if (index == truck_weights.length) continue;

            int truckWeight = truck_weights[index];

            if (bridgeQueue.isEmpty()) {
                bridgeQueue.add(new int[]{ time + bridge_length, truckWeight });
                bridgeWeight += truckWeight;
                index++;
                continue;
            }

            if (bridgeQueue.size() < bridge_length && bridgeWeight + truckWeight <= weight) {
                bridgeQueue.add(new int[]{ time + bridge_length, truckWeight });
                bridgeWeight += truckWeight;
                index++;
            }
        }

        return time;
    }
}
