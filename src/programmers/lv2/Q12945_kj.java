package programmers.lv2;

/* 프로그래머스 12945번 피보나치 수

[문제 풀이]
n + 1 크기로 배열 만들고 피보나치 계산만 하면 된다!
n 범위가 2 이상이니까, 0번째 1번째 미리 정의하고, 그 이후로 반복문 돌려서 계산하면 됨

큰 수가 나올 수도 있으니까 각각의 피보나치 수를 % 1234567로 나눈수로 저장하기
*/

class Q12945_kj {
    public int solution(int n) {

        int[] fiboArr = new int[n + 1];
        fiboArr[0] = 0;
        fiboArr[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibo(fiboArr, i);
        }

        return fiboArr[n];
    }

    private void fibo(int[] fiboArr, int idx) {

        // F(n) = F(n - 1) + F(n - 2)
        fiboArr[idx] = (fiboArr[idx - 1] + fiboArr[idx - 2]) % 1234567;
    }
}
