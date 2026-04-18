package programmers.lv2;

/* 프로그래머스 12941번 최솟값 만들기 문제

[문제 풀이]
정렬만 하면 되는 문제
*/

import java.util.*;

class Q12941_lth
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        int n = A.length;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        for(int i = 0; i < n; i++){
            answer += A[i] * B[n - 1 - i];
        }
        
        
        return answer;
    }
}