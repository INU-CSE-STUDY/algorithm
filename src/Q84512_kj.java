/* 프로그래머스 84512번 모음사전 문제

[문제 풀이]
규칙을 찾으면 좀 규칙성을 이용해서 수학적..? 이라 해야 하나 약간의 계산을 통해 풀어보려 했으나.. 아쉽게도 실패

(5) + (5 * 5) + (5 * 5 * 5) + (5 * 5 * 5 * 5) + (5 * 5 * 5 * 5 * 5) = 3905라는 매우 작은 수이기 때문에 그냥 사전을 직접 만들어서 몇번째 인덱스인지만 반환하면 되는 문제

*/

import java.util.*;

class Q84512_kj {
    public int solution(String word) {

        String[] alphabet = new String[]{ "A", "E", "I", "O", "U" };

        List<String> dictionary = new ArrayList<>();
        for (String first : alphabet) {
            dictionary.add(first); // 1글자

            for (String second : alphabet) {
                dictionary.add(first + second); // 2글자

                for (String third : alphabet) {
                    dictionary.add(first + second + third); // 3글자

                    for (String fourth : alphabet) {
                        dictionary.add(first + second + third + fourth); // 4글자

                        for (String fifth : alphabet) {
                            dictionary.add(first + second + third + fourth + fifth); // 5글자
                        }
                    }
                }
            }
        }

        // index는 0부터 시작하니까 1 더한 값을 반환
        return dictionary.indexOf(word) + 1;
    }
}