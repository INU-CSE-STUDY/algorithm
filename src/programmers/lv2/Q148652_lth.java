package programmers.lv2;

/* 프로그래머스 148652번 유사 칸토어 비트열 문제

[문제 풀이]
수학문제 같아 점화식을 찾고 싶었지만 모르겠어서 재귀로 풀었다
보통 구간을 구하는 문제는 처음부터 x까지 구하는 함수를 만들고
r까지의 1 개수 - (l-1)까지의 1 개수로 구하는게 좋다했다
이렇게 구하게 된다면 마지막 부분이 포함된 블록을 처리하기만 하면 되는데
마지막 부분 블록이 있다면 그 블록이 '1'에서 파생된 블록인지 확인하고
'1'에서 파생된 블록이라면 하위 레벨의 prefix(remainder)를 그대로 재귀호출해 합산한다
'0'에서 파생된 블록이라면 전부 0이므로 더할 것 없다
*/

import java.util.*;

class 148652_lth {
    // 해당 블록이 '1'에서 파생인지 여부
    private static final boolean[] ONE_BLOCK = {true, true, false, true, true};

    public int solution(int n, long l, long r) {
        // r까지의 1 개수 - (l-1)까지의 1 개수, l은 포함되어야함
        long ans = countOnesPrefix(n, r) - countOnesPrefix(n, l - 1);
        return (int) ans;
    }

    // 앞에서 x개를 포함했을 때의 '1' 개수
    private long countOnesPrefix(int n, long x) {
        if (x <= 0) return 0;
        if (n == 0) {
            // 레벨 0은 길이 1, 문자열은 "1"
            return Math.min(1, (int)x);
        }

        long blockSize = pow5(n - 1);   // 각 블록 길이
        long fullBlockOnes = pow4(n - 1); // '1'에서 파생 블록 하나가 가지는 1의 개수

        // x개를 채우기 위해 꽉 채운 블록 수와 마지막 부분 블록 길이
        long fullBlocks = x / blockSize;      
        long remainder = x % blockSize;

        long sum = 0;

        // 완전 포함된 블록들 합산
        for (int i = 0; i < fullBlocks && i < 5; i++) {
            if (ONE_BLOCK[i]) sum += fullBlockOnes;
        }

        // 부분 블록 처리
        if (fullBlocks < 5 && remainder > 0) {
            int idx = (int) fullBlocks; // 현재 부분 블록 인덱스
            if (ONE_BLOCK[idx]) {
                // 이 블록이 '1' 파생이면, 하위 레벨의 prefix(remainder)를 그대로 재귀
                sum += countOnesPrefix(n - 1, remainder);
            } else {
                // '0' 파생이면 전부 0이므로 더할 것 없음
            }
        }

        return sum;
    }

    // 5의 e제곱 계산
    private long pow5(int e) {
        long v = 1;
        for (int i = 0; i < e; i++) v *= 5L;
        return v;
    }

    // 4의 e제곱 계산
    private long pow4(int e) {
        long v = 1;
        for (int i = 0; i < e; i++) v *= 4L;
        return v;
    }
}
