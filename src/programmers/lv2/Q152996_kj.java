package programmers.lv2;

/* 프로그래머스 152996번 시소 짝꿍 문제

[문제 풀이]
map 자료구조 만들어서 배열에 있는 값 2, 3, 4 곱한거 다 넣기
getOrDefault 써서 같은 숫자 몇개 있는지 카운팅하면 순서쌍 몇개 나오는지 알 수 있으니까!
-> 생각해보니 안되네.. 100 100 같은 경우엔 같은 거리에 앉으면 되는건데 내가 하고 싶은대로 하려고 하면 1개 셀거 3개 세는구나

위에 아이디어 살려서 3개 센 다음에 중복으로 더해진 2개는 빼는 방식으로 채택
예제만 생각했을 때는 100, 100은 중복이니 Set에 넣어서 하나만 생각해서 하면 풀리지만.. [200, 300, 300] 같은게 들어왔을 때 다른 300일지라도 map에는 하나의 300밖에 안들어있어서 오답이 나오게 됨
있는대로 다 넣어서 조합 구한다음에, 중복된 애로 만들어지는 조합 * 2개(중복되는거 총 3개 들어가서 2개 빼야하니까) 빼면 됨
*/

import java.util.*;

class Q152996_kj {
    public long solution(int[] weights) {
        Map<Integer, Integer> duplicantMap = new HashMap<>();
        Map<Integer, Integer> weightMap = new HashMap<>();

        for (int weight : weights) {
            duplicantMap.put(weight, duplicantMap.getOrDefault(weight, 0) + 1);

            weightMap.put(weight * 2, weightMap.getOrDefault(weight * 2, 0) + 1);
            weightMap.put(weight * 3, weightMap.getOrDefault(weight * 3, 0) + 1);
            weightMap.put(weight * 4, weightMap.getOrDefault(weight * 4, 0) + 1);
        }

        long answer = 0;
        for (int count : weightMap.values()) {
            if (count >= 2) {
                answer += (long) count * (count - 1) / 2;
            }
        }

        long duplication = 0;
        for (int count : duplicantMap.values()) {
            if (count >= 2) {
                duplication += (long) count * (count - 1) / 2;
            }
        }

        return answer - duplication * 2;
    }
}