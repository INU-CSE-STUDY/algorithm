package programmers.lv2;

/* 프로그래머스 12981번 점프와 순간 이동 문제

[문제 풀이]
마지막부터 역산 

*/



import java.util.*;

public class Q12980_lth {
    public int solution(int n) {
        int ans = 0;
        while (n > 0) {
            if (n % 2 == 1){
               ans++; 
            } 
            n /= 2;
        }
        
        return ans;
    }
}