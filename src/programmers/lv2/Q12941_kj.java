package programmers.lv2;

/* 프로그래머스 12941번 최솟값 만들기 문제

[문제 풀이]
두 개 곱해서 더한 값이 최소이려면 제일 작은 값 * 제일 큰 값 해서 곱한 결과가 가장 작기만 하면 되는 거 아닐까!

둘 다 정렬해서 하나는 뒤에서부터, 하나는 앞에서부터 곱해서 더하기

*/

import java.util.*;

class Q12941_kj
{
    public int solution(int []A, int []B)
    {
        int length = A.length;

        Arrays.sort(A);
        Arrays.sort(B);

        int answer = 0;
        for (int i = 0; i < length; i++) {
            answer += (A[i] * B[length - i - 1]);
        }

        return answer;
    }
}
