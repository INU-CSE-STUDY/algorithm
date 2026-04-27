package programmers.lv2;

/* 프로그래머스 12936번 줄 서는 방법 문제

[문제 풀이]
그냥,, 순열 문제! 인데 사전순으로 나열하라했으니
List에 순열 결과물 저장하고, 그거 사전순으로 정렬한 다음에 k번째 방법(index 상 k - 1번)을 배열로 반환하면 될듯!
>>> 제한사항이 20!이라는 큰 수여서... list에 있는 값 꺼내기가 안된다 ㅠㅠ 웬일로 순열 코드를 한번에 잘 썼는데... 역시나ㅠ

패턴 찾기 문제인듯
사전순으로 나열한다는게.. 결국엔 1부터 쭉 서는 법, 2부터 쭉 서는 법 해서 나뉠거니까..

(ex) 5명이 잇다치면
1 2 3 4 5 / 1 2 3 5 4 / ... 이런 식으로 1 쫙 서고
2 1 3 4 5 / 2 1 3 5 4 / ... 이런 식으로 2 쫙 서고..

1 섰으면 그 바로 뒤에 2가 서는 경우, 3이 서는 경우로도 또 경우의 수가 쫙 나뉨, 사전순이니까!

*/

import java.util.*;

class Q12936_kj {

    public int[] solution(int n, long k) {

        // 줄 서는 사람 번호 저장하기
        List<Integer> availableNumberList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            availableNumberList.add(i);
        }

        int[] answer = new int[n];
        int index = 0;

        k -= 1; // index 맞춰주기
        while (index < answer.length) {

            long groupSize = factorial(--n); // 한 그룹 당 존재하는 숫자배열 개수

            int nowNumberIdx = (int) (k / groupSize);
            answer[index++] = availableNumberList.get(nowNumberIdx);
            availableNumberList.remove(nowNumberIdx);

            k = k % groupSize; // 다음 타겟 index를 찾기 위해 변경
        }

        return answer;
    }

    private long factorial(long k) {

        long answer = 1;
        for (int i = 1; i <= k; i++) {
            answer *= i;
        }

        return answer;
    }
}