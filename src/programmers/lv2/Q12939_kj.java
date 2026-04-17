package programmers.lv2;

/* 프로그래머스 12939번 최댓값과 최솟값 문제

[문제 풀이]
정말,,, 당연한 문제라고 생각함...
split해서 숫자 구분하고, 최솟값 최댓값을 각각 구하면 된다.

*/

class Q12939_kj {
    public String solution(String s) {

        String[] numArr = s.split(" ");

        int MIN = Integer.MAX_VALUE;
        int MAX = Integer.MIN_VALUE;

        for (String numStr : numArr) {

            int num = Integer.parseInt(numStr);

            MIN = Math.min(MIN, num);
            MAX = Math.max(MAX, num);
        }

        return MIN + " " + MAX;
    }
}
