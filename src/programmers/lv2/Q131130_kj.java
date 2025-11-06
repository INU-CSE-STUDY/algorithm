package programmers.lv2;

/* 프로그래머스 131130번 혼자 놀기의 달인 - 문제 번호가 1130이다 감동적인 번호

[문제 풀이]
순서대로 열어본다 생각했을 때
8 -> 4 -> 7 -> 1 이미 1 자리에 잇는 8 열려 있으니 빼고, 얘네 visited 배열로 관리해줘야 할듯??
선택 안된 것중에 가장 앞에 있는거부터해서
6 -> 5 -> 2 해서 2 자리에 6 이미 ㅇ열려있으니 스탑, 얘네도 visited 배열에 넣고
이렇게 해서 나온 그룹 세개의 size?? 를 저장해놔가지고 정렬해서 앞에 두 개 있는거 곱하면 되는거 아닐까?

*/

import java.util.*;

class Q131130_kj {
    public int solution(int[] cards) {
        int answer = 0;

        List<Integer> groupSize = new ArrayList<>();

        boolean[] visited = new boolean[cards.length]; // 해당 상자가 그룹에 속해있는지 방문 체크용
        for (int i = 0; i < cards.length; i++) {

            // 이미 다른 그룹에 속해있다면 그냥 넘어가기
            if (visited[i]) continue;

            visited[i] = true;
            int index = i;
            int count = 1;
            while (true) {
                int nextIndex = cards[index] - 1; // 다음에 열어봐야 할 상자의 index

                // nextIndex 위치의 상자가 이미 열려있는 상자라면 이 그룹은 종료
                if (visited[nextIndex]) {
                    groupSize.add(count);
                    break;
                }

                visited[nextIndex] = true;
                index = nextIndex;
                count++;
            }

        }

        groupSize.sort(Collections.reverseOrder()); // 최고 점수를 얻기 위해 내림차순 정렬

        // 그룹이 하나만 나오면 0, 아니라면 둘의 곱 반환
        if (groupSize.size() == 1) return 0;
        return groupSize.get(0) * groupSize.get(1);
    }
}
