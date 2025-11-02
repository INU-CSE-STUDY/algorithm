package programmers.lv2;

import java.util.stream.LongStream;

public class Q140107_jhg {
    public long solution(long k, long d) {
        return LongStream.iterate(0, i -> i <= d, i -> i + k)
                .map(i -> (long) (Math.floor(Math.sqrt(d * d - i * i)) / k) + 1)
                .sum();
    }
}
