package programmers.lv2;

/* 프로그래머스 131127번 할인 행사 문제

[문제 풀이]
완전 탐색 가능할 거 같은데..?
100,000 이고, 탐색해야 할 것도 10개니까

특정 날짜에서 시작해서 10일 동안 물건 구매 시, 계획했던 모든 물건을 구매할 수 있는지 체크
- 원하는 물건인지 확인은 contains 메서드 사용 (want 배열 list로 변환 필요)
- 전부 다 구매했는지는 따로 변수 둬서 계획했던 물건 개수랑 일치하는지 확인
- 반복문 돌면서 구매할 수 있는 물건 개수 세기

*/

import java.util.*;

class Q131127_kj {
    public int solution(String[] want, int[] number, String[] discount) {

        // 필요한 물품과 개수를 저장
        Map<String, Integer> wantNumber = new HashMap<>();
        int totalNumber = 0;
        for (int i = 0; i < want.length; i++) {
            wantNumber.put(want[i], number[i]);
            totalNumber += number[i];
        }

        int answer = 0;
        List<String> wantList = Arrays.asList(want); // 편하게 구하기 위해서 List로 변환
        for (int day = 0; day <= discount.length - 10; day++) {

            int purchase = 0; // 실제 구매한 물품 개수 구하기
            Map<String, Integer> copyWantNumber = new HashMap<>(wantNumber); // map 복사해서 반복문 돌 때마다 사용할 수 있게 해주기
            for (int checkDay = day; checkDay < day + 10; checkDay++) {
                String todayDiscount = discount[checkDay]; // 오늘의 할인품목

                // 사야할 물건이면서, 원하는 수량만큼 덜 샀을 경우에만 물건 구매
                if (wantList.contains(todayDiscount) && copyWantNumber.get(todayDiscount) > 0) {
                    copyWantNumber.put(todayDiscount, copyWantNumber.get(todayDiscount) - 1);
                    purchase++;
                }
            }

            // 10일 동안 구매한 물품의 개수와 계획했던 물품의 개수가 같은 경우가 정답
            if (totalNumber == purchase) {
                answer++;
            }
        }

        return answer;
    }
}
