package programmers.lv2;

/* 프로그래머스 12953번 N개의 최소공배수 문제

[문제 풀이]
최소공배수(LCM) = (a * b) / 최대공약수(GCD), 최대공약수 구하는 법 유클리드 호제법 사용
두개씩 하나의 최소공배수로 바꾸면서 하기위해 넣었다뺼수 있는 큐를 사용하고 큐에 최소 2개의 숫자가 있어야 동작함으로 1개남으면 종료
(a * b) / 최대공약수(GCD)가 범위를 벗어나 return (a / gcd(a, b)) * b로 변경
*/



import java.util.*;

class Q12953_lth {
    public int solution(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num : arr){
            pq.offer(num);
        }
        while(pq.size() != 1){
            int num1 = pq.poll();
            int num2 = pq.poll();
            pq.offer(lcm(num1, num2));
        }
        return pq.poll();
    }
    
    // 최대공약수 구하기
    int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    
    // 최소공배수 구하기
    int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}