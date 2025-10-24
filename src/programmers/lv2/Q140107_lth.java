package programmers.lv2;

/* 프로그래머스 140107번 점 찍기 문제

[문제 풀이]
원점에서 (x, y) 좌표까지의 거리가 d 이하인 점들을 k 간격으로 찍는 문제
이중 for문을 이용해서 x좌표를 0부터 d까지 k 간격으로, y좌표도 0부터 d까지 k 간격으로 돌면서
(x, y) 좌표가 d 이하인 경우에만 answer를 1씩 증가시켰다
-> 시간초과로 d가 아닌 d^2로 비교를 하여 sqrt 연산을 제거
-> 시간초과로 for문을 줄이기 위해 y좌표를 감소시키면서 d^2 이하인 y좌표의 개수를 세도록 변경
*/

class Q140107_lth {
    public long solution(int k, int d) {
        long answer = 0;
        long max = 1L * d * d;
        // 최대 y좌표
        long j  = d - (d % k);
        for (long i = 0; i <= d; i += k) {
            long ii = i * i;
            // 원보다 밖에 있는 y좌표는 제외하며 j 감소
            while (j >= 0 && ii + j * j > max) {
                j -= k;
            }
            // y좌표가 음수라면 종료
            if (j < 0){
                break; 
            }
            // 0을 포함하므로 j / k + 1
            answer += (j / k) + 1;
        }
        return answer;
    }
}