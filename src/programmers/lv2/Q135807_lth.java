package programmers.lv2;

/* 프로그래머스 135807번 숫자 카드 나누기 문제

[문제 풀이]
가능한 가장 큰 숫자 = 정렬했을때 가장 작은 숫자 = max, max의 약수를 구해서 가장 큰 수부터 a에 먼저 돌리면서
a의 공약수를 구하고 a의 공약수중 큰 순서대로 b에 넣어서 구함
-> 반대로 b의 공약수중 큰 순서대로 a에 넣어서 구함도 고려해야함 코드가 너무 길어져서 양쪽 공약수를 구하고 비교하는 식으로 변경
유클리드 호제법이라는 최대공약수 알고리즘으로 구하기

*/


import java.util.*;

class Q135807_lth {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = gcdAll(arrayA);
        int gcdB = gcdAll(arrayB);
        
        int maxA = calculate(gcdA, arrayB); // A의 공약수로 B를 전혀 못 나누는 최대값
        int maxB = calculate(gcdB, arrayA); // B의 공약수로 A를 전혀 못 나누는 최대값

        return Math.max(maxA, maxB);
    }

    // 최대공약수 구하기
    private int gcdAll(int[] arr) {
        int g = arr[0];
        for (int i = 1; i < arr.length; i++){
            g = gcd(g, arr[i]);
        }
        return g;
    }

    // 유클리드 호제법
    private int gcd(int a, int b) {
        if (b == 0){
            return a; 
        }
        return gcd(b, a % b);
    }

    // 약수 구해서 가장 큰 수부터 확인
    private int calculate(int g, int[] array) {
        if (g <= 1) return 0;

        List<Integer> divisors = divisorList(g);
        for (int d : divisors) {
            boolean vaild = true;
            for (int a : array) {
                if (a % d == 0) {
                    vaild = false; 
                    break; 
                }
            }
            if (vaild) return d;
        }
        return 0;
    }
    
    // 약수 리스트 구하기
    public List<Integer> divisorList(int num) {
        List<Integer> list = new ArrayList<>();
        for (int i = num; i > 1; i--) {
            if (num % i == 0) list.add(i);
        }
        return list;
    }
}