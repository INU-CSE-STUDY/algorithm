package programmers.lv2;

/**
 * 그냥 거져주는 문제 풀자..
 */
public class Q181187_jhg {

    public long solution(int r1, int r2) {
        long answer = 0;

        Circle in = new Circle(r1);
        Circle out = new Circle(r2);

        for (long x = 1; x <= r2; x++) {
            long outCount = out.count(x, false);
            long intCount = in.count(x, true);

            answer = answer + outCount - intCount + 1;
        }

        return answer * 4;
    }

    class Circle {
        long r;

        public Circle(int r) {
            this.r = r;
        }

        public long count(long x, boolean isCeil) {
            if (isCeil) {
                return (long) Math.ceil(Math.sqrt(r * r - x * x));
            } else {
                return (long) Math.floor(Math.sqrt(r * r - x * x));
            }
        }
    }
}
