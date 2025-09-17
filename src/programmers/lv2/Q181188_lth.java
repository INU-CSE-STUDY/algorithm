package programmers.lv2;

/* 프로그래머스 181188번 요격 시스템 문제

[문제 풀이]
가장 적은 요격 미사일 수로 모든 미사일을 요격하는 방법인데
길이가 1인 구간은 무조건 그 구간에 요격 미사일을 쏴야 한다
남은 구간들은 빨리 끝나는 기준으로 오름차순으로 정리한 뒤에 끝나는 지점에 요격
-> 실패 후 길이 1을 따로 빼지 않고 오름차순만 사용을 해봄
*/  


import java.util.*;

class Q181188_lth {
    public int solution(int[][] targets) {
        // 1. 끝점 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        //Arrays.sort(targets, Comparator.comparingInt(a -> a[1]));
        //Comparator는 “두 값을 비교하는 방법”을 정의
        //comparingInt는 정수 기준 비교기를 만들어주는 헬퍼 메서드
        //a -> a[1] : 람다식으로, 배열 a의 두 번째 요소(끝점)를 기준으로 비교
        
        int answer = 0;
        int last = 0;

        for (int[] seg : targets) {
            int start = seg[0], end = seg[1];
            if (start >= last) {
                answer++;
                last = end;
            }
        }
        return answer;
    }
}