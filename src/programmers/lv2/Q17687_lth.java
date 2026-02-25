package programmers.lv2;

/* 프로그래머스 17687번 n진수 게임

[문제 풀이]
문자의 진수변환과 그 진수를 한 글짜씩 끊는게 중요한 문제

*/

class Q17687_lth {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        int num = 0, turn = 0;

        while (answer.length() < t) {
            // 현재 숫자를 n진수 문자열로 변환하고 toCharArray로 한 글자씩 끊음
            for (char c : Integer.toString(num, n).toCharArray()) {
                // 튜브의 차례인지 확인
                if (turn++ % m == p - 1) {
                    answer.append(Character.toUpperCase(c));
                }
                if (answer.length() == t) break;
            }

            num++;
        }

        return answer.toString();
    }
}