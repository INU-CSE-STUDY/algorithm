package programmers.lv2;

/* 프로그래머스 12900번 2 x n 타일링 문제

[문제 풀이]
f(n) = f(n-1) + f(n-2)을 구현하는 문제이다
f(1) = 1, f(2) = 2를 미리 넣어놓고 f(3)부터 f(n)까지 반복문을 돌면서 값을 채워나간다
문제 제약 조건에 따라 1,000,000,007로 나눈 나머지를 return한다
*/


class Q12900_lth {
    public int solution(int n) {
        // f(n) = f(n-1) + f(n-2)
        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] cur = new int[n + 1];
        cur[1] = 1;
        cur[2] = 2;

        for (int i = 3; i <= n; i++) {
            cur[i] = (cur[i - 1] + cur[i - 2]) % 1000000007;
        }

        return cur[n];
    }
}
