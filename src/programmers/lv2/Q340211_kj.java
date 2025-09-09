package programmers.lv2;

/* 프로그래머스 340211번 충돌위험 찾기 문제

[문제 풀이]
규칙을 정리해보자면 다음과 같다.
1. 2차원 좌표 평면에 n개의 포인트가 존재
2. 로봇마다 정해진 운송 경로(각각의 포인트)를 따라 순서대로 방문
3. 총 x대의 로봇이 0초에 동시 출발, 가로 또는 세로로 한 칸씩만 이동 가능(대각선 이동 불가)
4. 항상 최단 경로로 이동, 최단 경로가 여러 가지일 경우 r 이동을 우선
5. 마지막 포인트에 도착한 로봇은 사라짐(벗어나는 경로 고려하지 않는댔으니)

이동 중 같은 좌표에 로봇이 2대 이상 모이면 충돌할 가능성이 있는 위험 상황으로 판단
-> 이 경우가 총 몇 번 발생하는지 세기

[코드 짜기]
1. 각 로봇이 최단 경로(최단 경로가 여러 가지일 경우는 r 이동을 우선)로 이동할 수 있게 하기
2. 같은 좌표에 로봇이 겹치는지 확인하는 거.. 얘를 어떻게 셀 지 고민
- 각 로봇이 이동하는 경로를 list 또는 queue에 저장해서 하나씩 꺼내보며 비교하기??
  내 생각에 모든 로봇이 한칸씩 이동해서 이동할 때마다 겹치는지 비교하는 것보단 위에거가 낫다고 생각이 들긴함..
  - 일단 잘 모르겠으니 로봇 하나씩 경로대로 이동하고, queue에 자기가 이동한 경로를 삽입
    (1) 이동 경로를 담은 queue 크기를 똑같이 만들어줌. 모자른 애들은 뒤에 [0, 0] 넣어가지고 문제가 되지 않게..
    (2) queue에서 값 빼낼 때, map<String, Integer> 초기화해서 이동마다 좌표값이 같은 데에 몇 개 들어가는지 체크하기
        getOrDefault 메서드로 +1 해서 넣으면 되니까!
    (3) map에 value 값이 2 이상인 애들 개수 세서 결과값에 더해주기
    (4) 결과값 반환
    이렇게 하면 되지 않을까....

코드 짜면서 생각한건데, queue에 담고, queue에서 꺼내면서 계속 비교하는 것보다
Map<Integer, Map<String, Integer>> 이렇게 만들어서, <시간, <좌표값, 로봇 개수>> 이렇게 저장하는게 더 효율적이지 않을까?
queue에 담고 빼고, 좌표값마다 로봇 개수 세고 이렇게 안하고 그냥 시간 별 좌표값에 로봇 개수가 2개 이상인 애만 셀 수 있으니..?
*/

import java.util.*;

class Q340211_kj {
    static Map<Integer, int[]> pointMap; // 좌표값과 포인트 번호 매핑용
    static Map<Integer, Map<String, Integer>> timePosition; // <현재 시간, <좌표, 좌표에 있는 로봇 수>>
    static int time; // 좌표 -> 좌표를 한 칸만에 이동되는게 아니라서.. 전역으로 잡고 시간 관리 편하게 하기

    public int solution(int[][] points, int[][] routes) {

        pointMap = new HashMap<>();
        timePosition = new HashMap<>();

        initPointMap(points);

        // 로봇 별로 하나씩 이동시키기
        for (int[] route : routes) {
            time = 0; // 최초 시간
            int[] now = pointMap.get(route[0]); // 최초 위치

            putTimePosition(time, now[0], now[1]);

            for (int j = 1; j < route.length; j++) {
                int[] next = pointMap.get(route[j]);
                move(now, next);
                now = next;
            }
        }

        int answer = 0;
        for (Map<String, Integer> pos : timePosition.values()) {
            for (int cnt : pos.values()) {
                if (cnt >= 2) answer++;
            }
        }

        return answer;
    }

    // 좌표와 포인트 번호 매핑
    private void initPointMap(int[][] points) {
        for (int i = 0; i < points.length; i++) {
            pointMap.put(i + 1, points[i]);
        }
    }

    // 현재 좌표에서 다음 좌표로 이동
    private void move(int[] now, int[] next) {
        int r = now[0], c = now[1];
        int nextR = next[0], nextC = next[1];

        // 다음 좌표에 도착할 때까지 반복
        while (r != nextR || c != nextC) {
            time++; // 1칸씩 이동할 때마다 시간이 흐름

            // r 이동이 우선
            if (r < nextR) r++;
            else if (r > nextR) r--;
            else if (c < nextC) c++;
            else c--;

            putTimePosition(time, r, c);
        }
    }

    // timePosition Map에 값 삽입
    private void putTimePosition(int time, int r, int c) {
        // 이미 같은 time이 존재할 수도 있으니 putIfAbsent 사용
        timePosition.putIfAbsent(time, new HashMap<>());
        Map<String, Integer> position = timePosition.get(time);

        String pKey = r + ", " + c;
        // 해당 좌표에 몇 개의 로봇이 있는지 세기, 나중에 이 value 값으로 위험 상황으로 판단할 예정(2 이상일 때)
        position.put(pKey, position.getOrDefault(pKey, 0) + 1);
    }
}