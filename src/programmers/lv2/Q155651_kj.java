package programmers.lv2;

/* 프로그래머스 155651번 호텔 대실 문제

[문제 풀이]
1. 일단 계산 편의성을 위해 hh:mm 형태의 시간을 분의 형태로 변경 + 청소시간 고려해서 종료 시간에는 10분 더해주기
2. 시작 시간 순서대로 정렬하기 (정렬안하면 예약과 예약 사이에 예약이 되는지 찾아보고 해야 하겠지..)
3. 예약건을 우선순위 큐에 저장해 같은 방에 예약이 가능한지 확인
   - 현재 방의 대실 종료 시각 <= 새로운 예약의 대실 시작 시각 을 만족해야 해당 방에 예약할 수 있음
     만족한다면, 해당 방의 종료 시각을 새로운 예약의 종료 시각으로 업데이트하기 (정렬된 상태라 사이에 들어올 예약 없으니까)
     예약이 불가능한 경우, 큐에 새로운 방의 예약 종료 시각 삽입
4. 큐의 사이즈 = 전체 방의 개수
*/

import java.util.*;

class Q155651_kj {
    public int solution(String[][] book_time) {

        int[][] timeTable = convertTimeTable(book_time);
        sortTimeTable(timeTable);

        PriorityQueue<Integer> roomEndTimes = new PriorityQueue<>();
        for (int[] time : timeTable) {
            if (roomEndTimes.isEmpty()) {
                // 첫번째 예약건일 경우
                roomEndTimes.offer(time[1]);
                continue;
            }

            if (roomEndTimes.peek() <= time[0]) {
                // 현재 예약 시작시간이 앞선 예약 종료시간과 같거나 더 큰 경우, 예약 가능
                roomEndTimes.poll(); // 종료 시간을 업데이트 해줘야하므로 빼주기
            }

            // 새로운 종료 시간 삽입
            roomEndTimes.offer(time[1]);
        }

        return roomEndTimes.size();
    }

    private int[][] convertTimeTable(String[][] book_time) {
        int[][] timeTable = new int[book_time.length][2];

        for (int i = 0; i < book_time.length; i++) {
            String[] time = book_time[i];

            int start = convertTimeToMinutes(time[0]);
            int end = convertTimeToMinutes(time[1]);

            timeTable[i][0] = start;
            timeTable[i][1] = end + 10; // 퇴실 시간을 기준으로 10분간 청소하는 시간이 포함되어 있음
        }

        return timeTable;
    }

    private int convertTimeToMinutes(String time) {
        String[] hhmm = time.split(":");
        int hh = Integer.parseInt(hhmm[0]);
        int mm = Integer.parseInt(hhmm[1]);

        return hh * 60 + mm;
    }

    private void sortTimeTable(int[][] timeTable) {
        Arrays.sort(timeTable,
                Comparator.comparingInt((int[] a) -> a[0])
                        .thenComparingInt(a -> a[1])
        );
    }
}