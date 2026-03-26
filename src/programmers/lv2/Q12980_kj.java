package programmers.lv2;

/* 프로그래머스 12980번 점프와 순간 이동 문제

[문제 풀이]
순간 이동하면 건전지 사용 X
점프하면 점프한 거리만큼 건전지 사용 O

도착지점부터 시작해서 n이 홀수면 그냥 한칸 점프해서 짝수 맞추고 쭉 순간이동만 하기. 그렇게 하면 될듯

*/

import java.util.*;

public class Q12980_kj {
    public int solution(int n) {

        int ans = 0;

        while (n != 0) {

            if (n % 2 != 0) {
                // n이 홀수이면 1칸 점프
                n -= 1;
                ans++;
                continue;
            }

            // n이 짝수이면 순간이동이 나으니까 순간이동
            n /= 2;
        }

        return ans;
    }
}
