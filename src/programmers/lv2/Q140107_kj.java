package programmers.lv2;

/* 프로그래머스 140107번 점 찍기 문제

[문제 풀이]
진짜 단순하게 생각하면 2중 for문해서 구하는 거겠지만.. k랑 d 범위도 1,000,000 이라서 2중 포문 돌리면 시간 초과
-> for문을 한개로 줄일 수 있는 방법?

원점 사이의 거리니까 x^2 + y^2 = d^2 임을 알고 있음.
y^2 = d^2 - x^2 -> y = sqrt(d^2 - x^2) 까지 구할 수 있음!
그러면 x가 가능한 범위를 찾으면 자연스레 가능한 y의 개수를 알 수 있음

*/

class Q140107_kj {
    public long solution(int k, int d) {
        long answer = 0;

        for (int x = 0; x <= d; x += k) {

            int y = getMaxY(x, d);

            // 가능한 y의 개수는 k로 나눈 몫 + 1 (0일 때 고려)
            answer += 1 + (int) (y / k);
        }

        return answer;
    }

    private int getMaxY(int x, int d) {
        // 이거 d * d로 하면 터지고 Math.pow로 하면 안터지네.. 값이 커지면 int 범위 제한 때문에 문제 생기는듯
        return (int) Math.sqrt(Math.pow(d, 2) - Math.pow(x, 2));
    }
}
