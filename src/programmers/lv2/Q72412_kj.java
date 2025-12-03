package programmers.lv2;

/* 프로그래머스 72412번 순위 검색 문제

[문제 풀이]
그냥 문제보고 딱 든 생각은,, 언어별로 나눠서 직군, 경력, 소울 푸드, 점수 저장해가지고 조건에 만족하는 사람 직접 세고 싶음
map의 key를 언어로 하고 value값으로 언어에 해당하는 지원자 넣어서 찾는 걸 생각했으나,, 이렇게 하면 언어가 "-"인 경우에 지원자 5만명에 쿼리 10만개 정도 들어오면 무조건 시간초과로 터짐. 이렇게 하면 안된다~

시간 초과 안나게 하는 방법
1. Map의 Key를 가능한 모든 경우의 수로 다 넣는다
- 조합의 개수가 참 적음. [언어, 직군, 경력, 소울푸드] 해서 총 16가지 조합
  가능한 모든 조합을 다 key로 만들어버리는 거임 java backend junior pizza / - - - pizza 이런 식으로..!!
  >> 그렇게 만들어서 value는 그 조건을 만족하는 사람의 점수를 담는 것!
2. 이제 조건을 만족하는 사람 중에서 점수 X점 이상 받은 사람을 탐색을 통해 구한다
- 입력받은 모든 조건을 key에 저장했으니, 그 key 값을 가져오면 value에는 조건에 해당하는 사람들의 코딩테스트 점수가 담겨있음
  이걸 정렬해서 이진탐색으로 찾으면 참 쉬운 문제
  >> 정렬을 입력받을 때마다 or 매 쿼리마다 하면 시간초과나니까 입력 다 받고 난 다음에 한번 정렬해야 함

*/

import java.util.*;

class Q72412_kj {

    public int[] solution(String[] info, String[] query) {

        Map<String, List<Integer>> conditionMap = new HashMap<>();
        for (String information : info) {

            String[] infoArr = information.split(" ");
            int grade = Integer.parseInt(infoArr[4]);  // 코딩테스트 점수

            combination(conditionMap, "", 0, infoArr, grade);
        }

        for (List<Integer> list : conditionMap.values()) {
            Collections.sort(list);
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {

            String q = query[i];

            String[] qArr = q.split(" ");
            String language = qArr[0];              // 언어
            String job = qArr[2];                   // 직군
            String career = qArr[4];                // 경력
            String food = qArr[6];                  // 소울푸드
            int grade = Integer.parseInt(qArr[7]);  // 코딩테스트 점수

            String key = language + job + career + food;

            int count = 0;
            if (conditionMap.containsKey(key)) {
                List<Integer> list = conditionMap.get(key);
                count = binarySearch(list, grade);
            }
            answer[i] = count;
        }

        return answer;
    }

    private int binarySearch(List<Integer> list, int grade) {

        int left = 0;
        int right = list.size() - 1;

        int index = list.size();
        while (left <= right) {

            int mid = (right + left) / 2;

            if (list.get(mid) >= grade) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }


        return list.size() - index;
    }

    private void combination(Map<String, List<Integer>> conditionMap, String key, int index, String[] info, int grade) {

        if (index == 4) {

            if (!conditionMap.containsKey(key)) {
                conditionMap.put(key, new ArrayList<>());
            }

            List<Integer> list = conditionMap.get(key);
            list.add(grade);

            return;
        }

        combination(conditionMap, key + info[index], index + 1, info, grade);
        combination(conditionMap, key + "-", index + 1, info, grade);
    }
}
