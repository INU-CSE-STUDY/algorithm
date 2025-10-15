package programmers.lv2;

/* 프로그래머스 150369번 택배 배달과 수거하기 문제

[문제 풀이]
스택을 이용해 deliveries와 pickups를 따로 저장
두 스택이 빌때까지 반복
두 스택 중 크기가 더 큰 스택의 길이*2만큼 answer에 더함
cap만큼의 배달을 들고 deliveries 스택에서 pop하면서 cap이 0이 될때까지 뻄
pickips에서 cap만큼 또 뺌
*/

import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        Deque<Home> deliveryStack = new ArrayDeque<>();
        Deque<Home> pickupStack = new ArrayDeque<>();
        
        for(int i = 0; i < n; i++){
            if (deliveries[i] > 0){
                deliveryStack.push(new Home(i, deliveries[i]));
            }
            if (pickups[i] > 0){
                pickupStack.push(new Home(i, pickups[i]));
            }
        }
        
        while(!deliveryStack.isEmpty() || !pickupStack.isEmpty()){
            int far = -1;
            if (!deliveryStack.isEmpty()) far = Math.max(far, deliveryStack.peek().idx);
            if (!pickupStack.isEmpty()) far = Math.max(far, pickupStack.peek().idx);

            answer += (long)(far + 1) * 2;
            
            long dCap = cap;
            while (dCap > 0 && !deliveryStack.isEmpty()) {
                Home top = deliveryStack.peek();
                if (top.remain <= dCap) {
                    dCap -= top.remain;
                    deliveryStack.pop();
                } else {
                    top.remain -= dCap;
                    dCap = 0;
                }
            }

            long pCap = cap;
            while (pCap > 0 && !pickupStack.isEmpty()) {
                Home top = pickupStack.peek();
                if (top.remain <= pCap) {
                    pCap -= top.remain;
                    pickupStack.pop();
                } else {
                    top.remain -= pCap;
                    pCap = 0;
                }
            }
        }
        
        return answer;
    }
    
    static class Home{
        int idx;
        long remain;
        Home(int idx, long remain) {
            this.idx = idx;
            this.remain = remain;
        }
    } 
}