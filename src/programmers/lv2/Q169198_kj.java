package programmers.lv2;

/* 프로그래머스 169198번 당구 연습 문제

[문제 풀이]

선대칭(상/하/좌/우)해서 나오는 직선의 최소 거리를 구하면 됨.
대칭이동하면 점의 위치는 다음과 같이 바뀜
상 (x, 2n - y) / 하 (x, -y) / 좌 (-x, y) / 우 (2m - x, y)

예제 2번처럼 벽에 맞기 전에 공이 먼저 맞게 되는 경우가 최소일 수도 있으니 이런 부분은 걸러주고 대칭이동하면 됨
- x 값이 같을 때
  목표 위치의 y 값이 더 크다면 상 방향으로 선대칭하면 안됨
  목표 위치의 y 값이 더 작다면 하 방향으로 선대칭하면 안됨
- y 값이 같을 때
  목표 위치의 x 값이 더 크다면 우 방향으로 선대칭하면 안됨
  목표 위치의 x 값이 더 작다면 좌 방향으로 선대칭하면 안됨
*/

class Q169198_kj {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        int index = 0;

        for (int[] ball : balls) {
            int targetX = ball[0], targetY = ball[1];

            // 선대칭 했을 때 나오는 좌표들
            int[][] points = {
                    { targetX, 2 * n  - targetY },
                    { targetX, -targetY },
                    { -targetX, targetY },
                    { 2 * m - targetX, targetY }
            };

            int minValue = Integer.MAX_VALUE;
            for (int[] point : points) {
                int tx = point[0], ty = point[1];

                if (startX == targetX && startX == tx) {
                    if (targetY > startY && ty > startY) continue; // 상 방향 안됨
                    if (targetY < startY && ty < startY) continue; // 하 방향 안됨
                }
                if (startY == targetY && startY == ty) {
                    if (targetX > startX && tx > startX) continue; // 우 방향 안됨
                    if (targetX < startX && tx < startX) continue; // 좌 방향 안됨
                }

                // 가능한 경우만 최솟값 계산
                int dist = getDistance(startX, startY, tx, ty);
                minValue = Math.min(minValue, dist);
            }

            answer[index++] = minValue;
        }

        return answer;
    }

    private int getDistance(int x1, int y1, int x2, int y2) {
        return (int) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}