package programmers.lv2;

import java.util.Arrays;

public class Q135807_jhg {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        int gcdA = Arrays.stream(arrayA)
                .reduce(this::getGCD)
                .orElse(0);
        int gcdB = Arrays.stream(arrayB)
                .reduce(this::getGCD)
                .orElse(0);

        return Math.max(calculateAvailableNumber(arrayA, gcdB), calculateAvailableNumber(arrayB, gcdA));
    }

    private int calculateAvailableNumber(int[] array, int gcd) {
        for (int number : array) {
            if (number % gcd == 0) {
                return 0;
            }
        }

        return gcd;
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
