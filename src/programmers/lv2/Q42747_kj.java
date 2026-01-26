package programmers.lv2;

/* 프로그래머스 42747번 H-Index 문제

[문제 풀이]
n편 중, h번 이상 인용된 논문이 h편 이상이고, 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 과학자의 H-Index

일단 문제 쉽게 풀려면 정렬해야함. 아니면 탐색을 계속 해야 하기 때문엥,,,
hIndex를 0부터 시작해서 전체 논문개수까지 돌리고!! 문제 조건에 맞춰서 풀면 된다..

*/

import java.util.*;

class Q42747_kj {
    public int solution(int[] citations) {

        int answer = -1;

        Arrays.sort(citations); // 이진탐색을 위해 정렬
        int totalCitationsCount = citations.length; // 전체 논문의 개수

        int start = 0, end = citations[totalCitationsCount - 1];
        for (int hIndex = 0; hIndex <= totalCitationsCount; hIndex++) {

            for (int i = 0; i < totalCitationsCount; i++) {

                if (citations[i] >= hIndex) {
                    // 현재 hIndex보다 더 많이 인용된 논문일 경우

                    int moreH = totalCitationsCount - i; // h번 이상 인용된 논문의 개수
                    if (moreH >= hIndex) {
                        // hIndex 업데이트
                        answer = hIndex;
                    }

                    break; // 뒤에는 당연히 더 많이 인용된 논문임,, 확인할 필요 X
                }
            }
        }

        return answer;
    }
}
