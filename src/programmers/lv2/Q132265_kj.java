package programmers.lv2;

/* 프로그래머스 132265번 롤케이크 자르기 문제

[문제 풀이]
완전탐색으로 가능하지 않을까?
나누는 위치를 한칸씩 옮겨가면서 토핑이 몇 개 들어있는지 체크하기

map에 철수와 동생이 가지고 있는 토핑을 저장 후, map.size를 비교해서 공평한지 체크하기
이렇게 하면 하나 빼고 하나 넣고가 자유로니까! (토핑 하나 바꿀 때마다 탐색할 필요가 없음)
- 철수는 토핑이 하나씩 증가(getOrDefault로 증가시켜주면 됨)
- 동생은 토핑이 하나씩 감소(1개 남은 토핑이었을 경우 아예 remove, 2개 이상 남았을 경우 그냥 하나 빼주기)

내 생각엔 이렇게 완탐이 될 거 같다
*/

import java.util.*;

class Q132265_kj {
    Map<Integer, Integer> cheolsu; // 철수가 갖고 있는 토핑 별 개수
    Map<Integer, Integer> brother; // 동생이 갖고 있는 토핑 별 개수

    public int solution(int[] topping) {

        initMap(topping);
        return getAnswer(topping);
    }

    private void initMap(int[] topping) {

        cheolsu = new HashMap<>();
        brother = new HashMap<>();

        // 철수는 맨 첫 번째 토핑을 갖고 시작
        cheolsu.put(topping[0], 1);

        // 동생은 첫 번째 토핑을 제외하고 나머지 토핑을 모두 갖고 시작
        for (int i = 1; i < topping.length; i++) {
            brother.put(topping[i], brother.getOrDefault(topping[i], 0) + 1);
        }
    }

    private int getAnswer(int[] topping) {
        int answer = 0;
        for (int i = 1; i < topping.length; i++) {

            // 현재 토핑 개수를 비교
            if (cheolsu.size() == brother.size()) answer++;

            // 한 칸 더 잘랐을 경우로 변경
            // 철수는 토핑이 하나씩 증가
            cheolsu.put(topping[i], cheolsu.getOrDefault(topping[i], 0) + 1);

            // 동생은 토핑이 하나씩 감소
            if (brother.get(topping[i]) == 1) {
                // 1개 있는 거면 아예 map에서 지우기 (size로 비교 중이니까!! 0으로 남겨두면 안됨)
                brother.remove(topping[i]);
            } else {
                // 2개 이상 갖고 있을 경우는 한 개 빼주기
                brother.put(topping[i], brother.get(topping[i]) - 1);
            }
        }

        return answer;
    }
}