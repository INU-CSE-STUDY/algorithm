package programmers.lv2;


import java.util.*;
import java.util.stream.IntStream;

// 문제에서 주어진 조건을 이용해서 구현하면 된다. 스트림은 그저 신이야.
public class Q147354_jhg {

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data,
                Comparator.comparingInt((int[] arr) -> arr[col - 1])
                        .thenComparing((int[] arr) -> -arr[0])
        );

        return IntStream.range(row_begin - 1, row_end)
                .map(i -> IntStream.of(data[i])
                        .map(num -> num % (i + 1))
                        .sum())
                .reduce(0, (a, b) -> a ^ b);
    }
}
