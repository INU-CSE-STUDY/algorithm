package programmers.lv2;

/* 프로그래머스 81302번 거리두기 확인하기 문제

[문제 풀이]
각 테스트케이스 별로 거리두기를 지키고 있는지 체크하기

- 전체 탐색할 필요 없이, 응시자의 위치를 리스트에 담아서 단순 비교하기
  1. 응시자가 1명 이하일 경우는 무조건 거리두기를 지키고 있음
  2. 응시자가 2명 이상일 경우
     1) 맨해튼 거리가 3 이상이면 거리두기를 지키고 있는 경우
     2) 맨해튼 거리가 2 이하일 경우 응시자끼리 파티션으로 분리되어 있는지 체크하기
=> 여기서 하나라도 false 나오면 거리두기 안지킨 경우라 0, 다 통과하면 거리두기 지키고 있는 경우라 1

*/

import java.util.*;

class Q81302_kj {

    static final int SIZE = 5; // 대기실 사이즈 = 5 * 5

    char[][] map; // 대기실 내부
    List<Point> personPositionList; // 각 대기실 별 응시자의 현재 위치

    public int[] solution(String[][] places) {

        int length = places.length;

        int[] answer = new int[length];

        for (int i = 0; i < length; i++) {

            String[] room = places[i];
            personPositionList = new ArrayList<>();

            // 모든 응시자가 거리두기를 지키고 있으면 1, 한 명이라도 지키지 않고 있으면 0 반환
            if (followRule(room)) answer[i] = 1;
            else answer[i] = 0;
        }

        return answer;
    }

    private boolean followRule(String[] room) {

        initMap(room);
        int personNum = personPositionList.size(); // 대기실에 존재하는 인원수

        if (personNum <= 1) {
            // 대기실에 응시자가 1명 이하인 경우, 거리두기는 항상 지켜짐
            return true;
        }

        // 대기실에 응시자가 2명 이상인 경우, 응시자의 위치를 비교해 거리두기가 지켜지고 있는지 확인
        for (int i = 0; i < personNum - 1; i++) {

            Point p1 = personPositionList.get(i);

            for (int j = i + 1; j < personNum; j++) {

                Point p2 = personPositionList.get(j);

                // 맨해튼 거리가 2 이하면 파티션으로 분리되어있는지 확인해야 함
                if (p1.getDistance(p2) <= 2) {

                    // 파티션에 의해 분리된 공간인지 확인
                    if (isSeperatedByPartitions(p1, p2)) continue;
                    return false;
                }
            }
        }

        return true; // 모든 응시자가 거리두기를 지키고 있는 경우
    }

    private boolean isSeperatedByPartitions(Point p1, Point p2) {

        int r1 = p1.r, c1 = p1.c;
        int r2 = p2.r, c2 = p2.c;

        if (r1 == r2) {
            // 같은 행에 위치할 경우, 둘 사이에 위치한 열에 파티션이 있어야 함
            int c = Math.min(c1, c2) + 1;

            if (map[r1][c] == 'X') return true;
            return false;
        }

        if (c1 == c2) {
            // 같은 열에 위치할 경우, 둘 사이에 위치한 행에 파티션이 있어야 함
            int r = Math.min(r1, r2) + 1;

            if (map[r][c1] == 'X') return true;
            return false;
        }

        // 대각선 방향에 위치한 경우, 대각선으로 파티션이 있어야 함
        if (map[r1][c2] == 'X' && map[r2][c1] == 'X') return true;
        return false;
    }

    private void initMap(String[] room) {

        map = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            String line = room[i];
            for (int j = 0; j < SIZE; j++) {

                char c = line.charAt(j);

                if (c == 'P') {
                    // 응시자가 위치한 곳은 리스트에 추가
                    personPositionList.add(new Point(i, j));
                }

                map[i][j] = c;
            }
        }
    }

    class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int getDistance(Point p) {
            // 맨해튼 거리는 |r1 - r2| + |c1 - c2|
            return Math.abs(this.r - p.r) + Math.abs(this.c - p.c);
        }
    }

}