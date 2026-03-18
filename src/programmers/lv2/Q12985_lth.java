package programmers.lv2;

/* 프로그래머스 12985번 예상 대진표 문제

[문제 풀이]
같은 대진에서 만나는 거니 둘이 같을때까지 반복한다

*/

class Q12985_lth
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        while (a != b) {
            a = (a + 1) / 2;
            b = (b + 1) / 2;
            answer++;
        }
        return answer;
    }
}