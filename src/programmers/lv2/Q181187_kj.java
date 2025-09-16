package programmers.lv2;

/* 프로그래머스 181187번 두 원 사이의 정수 쌍 문제

[문제 풀이]
하나의 사분면에 존재하는 점의 개수 구한 다음에 *4 하면 될 듯
x 범위를 0부터해서 하면 분명히 축 위의 점들이 겹칠테니 이런 부분 조심!

*/

class Q181187_kj {
    public long solution(int r1, int r2) {
        long answer = 0;

        for (int x = 1;  x <= r2; x++) {
            long y2 = (long) Math.floor(Math.sqrt(Math.pow(r2, 2) - Math.pow(x, 2)));
            long y1 = (long) Math.ceil(Math.sqrt(Math.pow(r1, 2) - Math.pow(x , 2)));

            answer += y2 - y1 + 1;
        }

        return answer * 4;
    }
}
