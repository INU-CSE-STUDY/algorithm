package programmers.lv2;

/* 프로그래머스 49994번 방문 길이 문제

[문제 풀이]
일단 범위가 (-5, -5) ~ (5, 5) 까지로 이루어진 정사각형
- 11 * 11 행렬 + 시작 위치가 (5, 5)로 시작하면 될듯

여기서부터는 저번에 태현이랑 얘기하면서 나온 건데

Set에 String으로 이동한 좌표를 저장(양방향 어디쪽으로 오든 상관 없으므로 두 개 다 저장하기)
Set에 저장했으므로, 중복 제거는 당연히 될 거니까 set.size() / 2로 반환하기(양방향 다 저장했으니까 나누기 2가 필수)
*/

import java.util.*;

class Q49994_kj {

    static int MAX = 11;

    public int solution(String dirs) {
        int answer = 0;

        String[] commands = dirs.split("");

        Set<String> newRouteSet = new HashSet<>();
        int r = 5, c = 5; // 시작 좌표
        for (String command : commands) {

            int nextR = r, nextC = c;

            switch (command) {
                case "U":
                    nextR--;
                    break;
                case "D":
                    nextR++;
                    break;
                case "R":
                    nextC++;
                    break;
                case "L":
                    nextC--;
                    break;
            }

            // 좌표평면의 경계를 넘어가는 명령어인지 확인
            if (isPossiblePosition(nextR, nextC)) {
                // 경계를 넘어가지 않는 명령어라면 이동

                StringBuilder route1 = new StringBuilder();
                StringBuilder route2 = new StringBuilder();

                route1.append(r).append(", ").append(c).append(" ").append(nextR).append(", ").append(nextC);
                route2.append(nextR).append(", ").append(nextC).append(" ").append(r).append(", ").append(c);

                newRouteSet.add(route1.toString());
                newRouteSet.add(route2.toString());

                r = nextR;
                c = nextC;
            }
        }


        return newRouteSet.size() / 2;
    }

    private boolean isPossiblePosition(int r, int c) {
        // 좌표평면의 경계를 넘어가지 않은 위치인지 확인(가능한 좌표값은 0부터 MAX - 1 까지)
        return (0 <= r && r < MAX) && (0 <= c && c < MAX);
    }
}
