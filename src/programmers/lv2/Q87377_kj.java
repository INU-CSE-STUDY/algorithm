package programmers.lv2;

/* 프로그래머스 87377번 교점에 별 만들기 문제

[문제 풀이]
Ax + By + C = 0 에서 [A, B, C]가 입력으로 주어짐 (진짜 친절하게 x랑 y 구하는 방식을 알려줌;;)

교점의 좌표가 정수로만 표현되는 좌표를 구한 다음에, 그걸 기반으로 별 그리기
x와 y의 min/max 값으로 구하면 됨!

*/

import java.util.*;

class Q87377_kj {

    Set<Point> crossPointSet;
    int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

    public String[] solution(int[][] line) {
        crossPointSet = new HashSet<>();
        getCrossPoint(line);

        return drawStar().split("\n");
    }

    private String drawStar() {
        StringBuilder sb = new StringBuilder();

        for (int y = maxY; y >= minY; y--) {
            for (int x = minX; x <= maxX; x++) {

                // 현재 위치가 정수로 표현되는 교점이라면 *, 아니라면 .을 표시
                Point now = new Point(x, y);
                if (crossPointSet.contains(now)) {
                    sb.append("*");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private void getCrossPoint(int[][] line) {

        // 2중 for문으로 교점 구하기
        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i + 1; j < line.length; j++) {

                int A = line[i][0];
                int B = line[i][1];
                int E = line[i][2];

                int C = line[j][0];
                int D = line[j][1];
                int F = line[j][2];

                long denominator = ((long) A * D - (long) B * C);
                if (denominator == 0) continue; // 분모 = 0인 경우 두 지석은 평행 또는 일치

                long numeratorX = ((long) B * F - (long) E * D);
                long numeratorY = ((long) E * C - (long) A * F);

                // 정수로 표현되는 교점이 아닌 경우
                if (numeratorX % denominator != 0 || numeratorY % denominator != 0) continue;

                // 정답은 1,000 * 1,000 크기 이내에 표현된다고 했으니 안심하고 int로 바꾸기 ㅎㅎ
                int x = (int) (numeratorX / denominator);
                int y = (int) (numeratorY / denominator);

                // 전체 범위 및 교점 위치 저장
                crossPointSet.add(new Point(x, y));
                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            }
        }
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {

            if (!(o instanceof Point)) return false;

            Point p = (Point) o;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}