package programmers.lv2;

/* 프로그래머스 70129번 이진 변환 반복하기 문제

[문제 풀이]
문자열을 char단위로 세서 1이 나오면 다음 길이 0부터 +1씩해서
문자열의 길이가 1이 될때까지 반복

*/

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];

        while (!s.equals("1")) {
            int ones = 0;

            for (char ch : s.toCharArray()) {
                if (ch == '1') {
                    ones++;
                } else {
                    answer[1]++;
                }
            }

            s = Integer.toBinaryString(ones);
            answer[0]++;
        }

        return answer;
    }
}