package programmers.lv2;

/* 프로그래머스 169198번 당구 연습 문제

[문제 풀이]
선대칭(상/하/좌/우)해서 나오는 직선의 최소 거리를 구하면 됨
좌측 벽 반사일때 y가 같고 x가 더 크면 안됨
우측 벽 반사일때 y가 같고 x가 더 작으면 안됨
하단 벽 반사일때 x가 같고 y가 더 크면 안됨
상단 벽 반사일때 x가 같고 y가 더 작으면 안됨
*/

class Q169198_lth {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
            int bx = balls[i][0];
            int by = balls[i][1];
            int best = Integer.MAX_VALUE;

            // 1) 좌측 벽 반사: (-bx, by)
            if (!(startY == by && startX > bx)) {
                best = Math.min(best, getDistance(startX, startY, -bx, by));
            }

            // 2) 우측 벽 반사: (2m - bx, by)
            if (!(startY == by && startX < bx)) {
                best = Math.min(best, getDistance(startX, startY, 2 * m - bx, by));
            }

            // 3) 하단 벽 반사: (bx, -by)
            if (!(startX == bx && startY > by)) {
                best = Math.min(best, getDistance(startX, startY, bx, -by));
            }

            // 4) 상단 벽 반사: (bx, 2n - by)
            if (!(startX == bx && startY < by)) {
                best = Math.min(best, getDistance(startX, startY, bx, 2 * n - by));
            }

            answer[i] = best;
        }
        return answer;
    }
    
    private int getDistance(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2;
        int dy = y1 - y2;
        return dx * dx + dy * dy;
    }
}