package programmers.lv2;

/* 프로그래머스 12953번 N개의 최소공배수 문제

[문제 풀이]
문제가 엄청 짧다.
2 2 6 8 14
  1 3 4 7
=> 2 * 1 * 3 * 4 * 7 = 168 되는 그것.

예전에 어디서 본 거 같은데 여러 수 최소공배수 구하려면 그냥 앞에서부터 최소공배수 구하면서 된다고
2 6 8 14 => 6 8 14 => 24 14 => 168 이렇게!

하나만 틀려서 질문하기 보니까,, lcm 구할 때 범위 넘어가는 경우가 있는 듯. 계산 순서를 살짝 바꿈
*/

import java.util.*;

class Q12953_kj {
    public int solution(int[] arr) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : arr) {
            pq.offer(num); // 우선순위 큐 초기화
        }

        while (pq.size() != 1) {

            // pq에 저장했으므로, 항상 오름차순으로 정렬되어있음
            int num1 = pq.poll();
            int num2 = pq.poll();

            int gcd = getGCD(num1, num2);
            int lcm = getLCM(num1, num2, gcd);

            pq.offer(lcm);
        }

        return pq.poll();
    }

    private int getGCD(int num1, int num2) {

        while (num1 != 0) {
            int temp = num2 % num1;
            num2 = num1;
            num1 = temp;
        }

        return num2;
    }

    private int getLCM(int num1, int num2, int gcd) {

        return (num1 / gcd) * (num2 / gcd) * gcd;
    }
}