package programmers.lv3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 보자마자 진수 변환 문제로 이해했다
 * 더 설명이 필요한가?
 * 금지당한 문자를 원래 위치를 기억하게 하고, 이제 찾아야할 문자의 위치를 조정하면 된다.
 */
public class Q389481_jhg {

    private static final long N = 26;

    public String solution(long n, String[] bans) {

        List<Long> decimalList = Arrays.stream(bans)
                .map(ban -> conversionDecimal(ban, N))
                .sorted()
                .collect(Collectors.toList());

        for (Long decimal : decimalList) {
            if (decimal <= n) {
                n++;
            }
        }

        return conversionAlphabet(n);
    }


    // n진수 -> 10진수로 변환
    public long conversionDecimal(String character, long n) {
        long pos = 0;

        String lowerCase = character.toLowerCase();

        for (int i = 0; i < lowerCase.length(); i++) {
            int reverseIndex = lowerCase.length() - i - 1;

            char c = lowerCase.charAt(i);

            pos += (long) (Math.pow(n, reverseIndex) * (c - 'a' + 1));
        }

        return pos;
    }


    // 10진수 -> 문자 변환
    public String conversionAlphabet(long decimal) {
        StringBuilder result = new StringBuilder();

        while (decimal > 0) {
            long num = decimal % 26;
            if (num == 0) {
                result.append("z");
                decimal -= 1;
                decimal /= 26;
            } else {
                result.append((char) ('a' + num - 1));
                decimal /= 26;
            }
        }

        return result.reverse().toString();
    }
}
