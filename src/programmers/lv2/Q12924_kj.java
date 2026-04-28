package programmers.lv2;

/* 프로그래머스 12924번 숫자의 표현 문제

[문제 풀이]
헐 이게 뭐야? 완전탐색으로 되나?

*/

class Q12924_kj {
    public int solution(int n) {
        int answer = 0;

        for (int i = 1; i <= n; i++) {

            int sum = i;
            int next = i + 1;

            while (sum < n) {

                sum += next;
                next++;
            }

            if (sum == n) answer++;
        }

        return answer;
    }
}
