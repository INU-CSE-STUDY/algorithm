package programmers.lv2;

/* 프로그래머스 42885번 구명보트 문제

[문제 풀이]
한 번에 최대 2명씩 타는 보트면,, 그냥 정렬해서 가장 무거운 사람 + 가장 가벼운 사람 조합으로 하나씩 태우고 안되면 다 한명씩 밖에 못태우는 거니까 그렇게 하면 되는 거 아닌가?

*/

import java.util.*;

class Q42885_kj {
    public int solution(int[] people, int limit) {
        int answer = 0;

        int left = 0;
        int right = people.length - 1;
        Arrays.sort(people);

        while (left <= right) {

            int minWeight = people[left];
            int maxWeight = people[right];

            // 가장 무거운 사람은 무조건 태우니까 태우는 처리
            right--;

            if (minWeight + maxWeight <= limit) {
                // 무게의 합이 제한 아래라면 두명 태울 수 있으니까 가장 가벼운 사람도 태우기
                left++;
            }

            answer++;
        }

        return answer;
    }
}
