package programmers.lv2;

/* 프로그래머스 132265번 롤케이크 자르기 문제

[문제 풀이]
hashset으로 중복을 제거하며 size를 비교해서 비교를 하고자 하였으나 set은 몇개가 있는지 알 수 없기에 
한쪽을 줄일 때 개수를 몰라 줄일 수 없어서 map을 이용하여 key는 토핑 번호, value는 토핑의 개수로 저장하여 비교하는 방법을 사용하였다.
a가 하나를 가지고 b가 n-1개를 가지고 시작하여 a는 하나씩 증가, b는 하나씩 감소시키며 size를 비교하였다.
증가시키는 쪽은 개수가 중요하지 않으니 그대로 set을 사용하고 감소시키는 쪽은 개수가 중요하니 map을 사용하였다.
    -> 처음에 answer을 -1로 주어줘서 answer++를 해주었는데 답이 틀렸다 0으로 주어주지  
*/

import java.util.*;

class Q132265_lth {
    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> right = new HashMap<>();
        Set<Integer> left = new HashSet<>();
        
        // 오른쪽에 다 주고 토핑 개수 세기
        for (int t : topping) {
            right.put(t, right.getOrDefault(t, 0) + 1);     
        }
        
        for (int t : topping) {
            // 왼쪽에 하나 추가
            left.add(t);

            // 오른쪽에서 하나 제거
            right.put(t, right.get(t) - 1);         //t를 빼서 개수에서 1 감소
            if (right.get(t) == 0) {
                right.remove(t);               //개수가 0이 되면 map에서 제거
            }

            // size 비교
            if (left.size() == right.size()) {
                answer++;
            }
        }
        
        return answer;
    }
}