package programmers.lv3;

/* 프로그래머스 258705번 산 모양 타일링 문제

[문제 풀이]
기본 형태인 사다리꼴의 윗변과 변을 공유하는 삼각형을 붙여 새로운 모양을 만들어 정삼각형 타일과 마름모 타일을 이용해 빈 곳이 없돌독 채워야 함

<기본 형태>
1. n = 1인 사다리꼴 + 위에 삼각형 0개 -> 삼각형만 쓰는 경우 + 마름모 1개 포함된 경우 * 2 = 총 3가지
2. n = 1인 사다리꼴 + 위에 삼각형 1개 -> 삼각형만 쓰는 경우 + 마름모 1개 포함된 경우 * 3 = 총 4가지

n = 2가 됐을 때 일단 옆에 삼각형 두개 추가로 생김
-> 기본 형태 1에서 추가됐을 경우 -> 8가지
-> 기본 형태 2에서 추가됐을 경우 -> 11가지(예제 2번)

진짜 잘 모르겠어서 카카오 테크 블로그랑 질문하기 보면서 풀이를 작성했다...
*/

class Q258705_kj {
    private final int MOD = 10007;

    public int solution(int n, int[] tops) {
        int[] memo = new int[n + 1];

        memo[0] = 1;
        memo[1] = (tops[0] == 1 ? 4 : 3);

        for (int i = 2; i <= n; i++) {
            int current = (tops[i - 1] == 1 ? 4 : 3);
            memo[i] = (current * memo[i - 1] - memo[i - 2] + MOD) % MOD;
        }

        return memo[n] % MOD;
    }
}