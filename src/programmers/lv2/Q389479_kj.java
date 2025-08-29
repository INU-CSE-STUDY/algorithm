package programmers.lv2;

/* 프로그래머스 389479번 서버 증설 횟수 문제

[제약 조건]
- 게임을 이용하는 사람이 m명 늘어날 때마다 서버 1대가 추가로 필요 / m명 미만이라면 서버 증설 X
- n * m <= 어느 시간대의 이용자 < (n + 1) * m
  -> 최소 n대 증설 필요
- 한 번 증설한 서버는 k 시간 동안만 운영
  -> k = 5일 때, 10시에 증설했다면 10 - 15시까지만 운영


[문제 풀이]
사실 문제 보고 딱 떠오르는 풀이가 없어서.. 그냥 시간을 1씩 늘려가며 직접 세보기! (시뮬레이션)

매 반복문마다 현재 시간의 이용자수를 알 수 있음
-> 이 사람의 수와 서버의 수를 비교해 증설 여부를 체크하기

n * m <= 게임 이용자의 수 < (n + 1) * m (n >= 0)
한번 증설된 서버는 k시간만큼 운영되므로, 해당 시간을 queue에 저장해 시간이 끝나면 뺄 수 있게 하기!
- queue가 선입선출이라 넣고 빼기가 좋으니까!
-> 실제로 코드 짜보니까 한번에 서버가 여러개 증설되는 경우가 존재
   int[]을 넣어 서버 증설이 시작된 시간과 증설된 개수를 저장
*/

import java.util.*;

class Q389479_kj {
    public int solution(int[] players, int m, int k) {
        int time = 0; // 현재 시간대 및 players 배열의 index
        int nowServer = 0; // 증설된 서버의 수
        int answer = 0; // 서버 증설 횟수
        Queue<int[]> serverStatus = new LinkedList<>(); // 서버 증설이 시작된 시간과 개수를 담는 queue
        // 24시가 되면 종료
        while (time < 24) {
            // 현재 시간의 이용자수
            int nowPlayers = players[time];

            // 이번 시간에 종료되는 서버가 있는지 확인
            while (!serverStatus.isEmpty()) {
                int[] status = serverStatus.peek();
                int startTime = status[0]; // 서버 증설이 시작된 시간
                int serverCnt = status[1]; // 증설된 서버 개수

                if (startTime + k == time) {
                    // 서버가 종료되는 시간일 경우 queue에서 빼내기 + 현재 운영 중인 서버 개수 줄이기
                    serverStatus.poll();
                    nowServer -= serverCnt;
                } else {
                    // 서버가 종료되는 시간이 아닐 경우 반복문 종료
                    // 순서대로 들어가있기 때문에 아닌 경우를 만나자마자 종료 가능
                    break;
                }
            }

            // 서버가 부족한 경우
            if (!isAvailable(nowServer, m, nowPlayers)) {
                // 최소 몇 개의 서버가 증설되어야 하는지 확인
                int cnt = (nowPlayers / m) - nowServer;
                nowServer += cnt;
                answer += cnt;
                serverStatus.add(new int[]{ time, cnt });
            }

            time++;
        }

        return answer;
    }

    // 어느 시간대의 이용자가 모두 이용가능한지 확인하는 메서드
    private static boolean isAvailable(int n, int m, int nowPlayers) {
        return nowPlayers < (n + 1) * m;
    }
}