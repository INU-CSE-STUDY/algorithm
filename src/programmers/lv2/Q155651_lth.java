package programmers.lv2;

/* 프로그래머스 155651번 호텔 대실 문제

[문제 풀이]
계산을 위해 시간을 분 단위로 바꾸고 청소시간 10분을 더해줌
입실 시간 순으로 정렬하고 우선순위 큐에 퇴실시간을 넣어줌
큐에 있는 퇴실시간이 입실시간보다 작거나 같으면 큐에서 빼줌
매 입실 시간마다 큐의 크기를 비교해 최대값을 구함
*/

import java.util.*;

class Q155651_lth {
    public int solution(String[][] book_time) {
        int answer = 0;
        // 일단 입실 시간 순으로 정렬하고 종료 시간은 10분 추가
        // 입실 시 큐에 넣고 입장보다 퇴실이 빠르면 뺀다 그렇게 큐가 최대 몇개까지 쌓였는지
        
        int[][] timeTable = makeTimeTable(book_time);
        // 입실 시간 오름차순 정렬
        Arrays.sort(timeTable, (a, b) -> a[0] - b[0]);
        
        // 퇴실 시간 오름차순 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int[] t : timeTable){
            int start = t[0];
            int end = t[1];
            // 큐가 비어있지 않고 입실 시간보다 퇴실 시간이 빠른 방은 빼주기
            while (!pq.isEmpty() && pq.peek() <= start) {
                pq.poll();
            }
            
            pq.offer(end);
            answer = Math.max(answer, pq.size());
        }
        return answer;
    }
    
    // 시간을 분 단위로 바꾸고 청소시간 10분 추가
    private int[][] makeTimeTable(String[][] book_time){
        int n = book_time.length;
        int[][] timeTable = new int[n][2];
        
        for(int i = 0; i < n; i++){
            String[] first = book_time[i][0].split(":");
            int fh = Integer.parseInt(first[0]);
            int fm = Integer.parseInt(first[1]);
            int start = fh * 60 + fm;
            
            String[] second = book_time[i][1].split(":");
            int sh = Integer.parseInt(second[0]);
            int sm = Integer.parseInt(second[1]);
            int end = sh * 60 + sm;
            timeTable[i][0] = start;
            timeTable[i][1] = end + 10;
        }
        return timeTable;
    }
}