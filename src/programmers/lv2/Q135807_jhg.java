package programmers.lv2;

import java.util.Arrays;

public class Q135807_jhg {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = Arrays.stream(arrayA)
                .reduce(this::getGCD)
                .orElse(0);
        int gcdB = Arrays.stream(arrayB)
                .reduce(this::getGCD)
                .orElse(0);

        return Math.max(calculateAvailableNumber(arrayA, gcdB), calculateAvailableNumber(arrayB, gcdA));
    }

    private int calculateAvailableNumber(int[] array, int gcd) {
        return Arrays.stream(array)
                .anyMatch(number -> number % gcd == 0) ? 0 : gcd;
    }

    private int getGCD(int b, int a) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
