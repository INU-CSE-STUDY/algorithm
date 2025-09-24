package programmers.lv2;

import java.util.*;

// 구현.
public class Q169198_jhg {

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        Pos ball = new Pos(startX, startY);

        return Arrays.stream(balls)
                .map(Pos::new)
                .map(target -> {
                    int result = Integer.MAX_VALUE;

                    for (Pos point: target.getPoint(n, m)) {
                        if (ball.isValid(target, point)) {
                            result = Math.min(result, ball.getDistance(point));
                        }
                    }

                    return result;
                })
                .mapToInt(i->i).toArray();
    }


    static class Pos {
        int x;
        int y;

        public Pos(int[] pos) {
            this.x = pos[0];
            this.y = pos[1];
        }

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private int getDistance(Pos pos) {
            return (int) (Math.pow(this.x - pos.x, 2) + Math.pow(this.y - pos.y, 2));
        }

        public List<Pos> getPoint(int n, int m) {
            return List.of(
                    new Pos(this.x, 2 * n - this.y),
                    new Pos(2 * m - this.x, this.y),
                    new Pos(this.x, -this.y),
                    new Pos(-this.x, this.y)
            );
        }

        public boolean isValid(Pos target, Pos point) {
            if (this.x == target.x && target.x == point.x) {
                if ((target.y > this.y && point.y > this.y)
                        || (target.y < this.y && point.y < this.y)) {
                    return false;
                }
            }
            if (this.y == target.y && target.y == point.y) {
                if ((target.x > this.x && point.x > this.x)
                        || (target.x < this.x && point.x < this.x)) {
                    return false;
                }
            }

            return true;
        }
    }
}
