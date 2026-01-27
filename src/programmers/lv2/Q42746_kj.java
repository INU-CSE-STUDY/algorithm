package programmers.lv2;

/* 프로그래머스 42746번 가장 큰 수 문제

[문제 풀이]
숫자를 앞자리부터 비교해서 정렬하면 되지 않을까? -> 사전식 정렬로!!!
67, 6 -> 이런 애들은 앞자리는 똑같으니까 단순 사전식 정렬만 하면 676 / 667 이런 식으로.. 큰 수가 아닌 결과가 나올 수도 있으니
앞뒤 숫자를 붙여가지고 더 큰수인지 확인하면서 사전식 정렬하기

+) 11번 다 0인 경우란다ㅡㅡ 0000 이런 식으로 나오면 안됨.. 가장 큰 수가 0이면 그냥 0 return 할 수 있게
*/

import java.util.*;

class Q42746_kj {
    public String solution(int[] numbers) {

        String[] strArr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strArr[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(strArr, (a, b) -> (b + a).compareTo(a + b));

        // 가장 큰 수가 0이면 다 0인 경우니까 0 반환
        if (strArr[0].equals("0")) return "0";

        StringBuilder answer = new StringBuilder();
        for (String number : strArr) {
            answer.append(number);
        }

        return answer.toString();
    }
}