package programmers.lv3;

/* 프로그래머스 389481번 봉인된 주문 문제

[문제 풀이]
a -> 1번 ... z -> 26번
aa -> 27번 ... az -> 52번
ba -> 53번 ... bz -> 78번
...
이런 식으로 증가하니까, 문자 길이 + 각 자릿수마다 있는 단어 위치 계산해서 몇 번째 글자인지 구해낼 수 있을 것..

ex) aa -> 26 * 1(a) + 1(a) / bz -> 26 * 2(b) + 26(z)
abc -> 26 * 26 * 1(a) + 26 * 2(b) + 3(c)

1. bans에 있는 단어 중에 n번째 이내에 있는 단어가 몇 개인지 세기
- 예시 1번처럼 bb와 같이 n번째 주문을 찾는 데 영향을 주지 않는 주문이 존재할 수 있기 때문
- bans 배열을 정렬해서 앞에서부터 단어를 삭제(n을 증가)하면서 영향을 주는 단어를 계산
2. 우리가 찾는 단어의 위치를 알았다면, 단순한 나눗셈 연산으로 우리가 찾고자하는 단어를 역순으로 저장한 뒤, reverse()를 통해 반환
- 26으로 나누어떨어질 때만 주의해서 계산! 우리가 원하는 범위는 0 ~ 25가 아닌 1 ~ 26이므로, 나누어 떨어질 때는 앞자리에서 빌려온다는 생각으로 계산해야 함!!

[제약 조건]
1 <= n <= 10^15 이므로 long 타입으로 계산 필요
*/

import java.util.*;

class Q389481_kj {
    public String solution(long n, String[] bans) {

        // bans 배열을 고대의 규칙에 따라 정렬
        Arrays.sort(bans, Comparator.comparingInt(String::length)
                .thenComparing(String::compareTo));

        // bans 배열에서 삭제되는 단어찾기
        // 삭제된다는 말은 삭제된 개수만큼 뒤에 있는 단어를 구하는 것이기 때문에 더하기 연산!
        for (int i = 0; i < bans.length; i++) {
            String banWord = bans[i];

            long length = banWord.length();
            long num = 0;
            for (int j = 0; j < length; j++) {
                num += Math.pow(26, length - j - 1) * (long) (banWord.charAt(j) - 'a' + 1);
            }

            // 범위 내에서 삭제된다면, 정상적으로 삭제(증가)
            if (n >= num) n++;
            else break; // 삭제되지 않는 단어라면 반복문 종료, 정렬된 배열이므로 그 뒤는 쭉 만족안하니까!
        }

        StringBuffer answer = new StringBuffer();
        while (n > 0) {
            long num = n % 26;

            if (num == 0) {
                // 나누어떨어지는 경우는 z, 앞에서 받아내림한다고 생각해야 함
                answer.append("z");
                n -= 1;
                n /= 26;
            } else {
                // 나누어떨어지지 않는 경우는 그냥 단순 계산
                answer.append((char) ('a' + num - 1));
                n /= 26;
            }
        }

        // 역순으로 저장했기 때문에 reverse()로 우리가 원하는 순서로 변경
        return answer.reverse().toString();
    }
}