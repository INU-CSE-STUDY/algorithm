package programmers.lv2;

import java.util.*;
import java.util.stream.*;

public class Q134239_jhg {

    public double[] solution(int k, int[][] ranges) {
        List<Integer> sequence = getSequence(k);
        sequence.add(1);

        double[] prefix = getPrefix(sequence);

        return Arrays.stream(ranges)
                .mapToDouble(range -> {
                    int a = range[0];
                    int b = sequence.size() + range[1] - 1;

                    return (a > b) ? -1 : prefix[b] - prefix[a];
                })
                .toArray();
    }

    private double[] getPrefix(List<Integer> sequence) {
        int size = sequence.size() - 1;

        double[] prefix = new double[size + 1];

        for (int i = 1; i <= size; i++) {
            prefix[i] = ((sequence.get(i - 1) + sequence.get(i))) / 2d  + prefix[i - 1];
        }

        return prefix;
    }

    private List<Integer> getSequence(int k) {
        return Stream.iterate(k * 2, n -> n != 1,
                        n -> (n % 2 == 0) ? n / 2 : n * 3 + 1)
                .collect(Collectors.toList());
    }
}
