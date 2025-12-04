package programmers.lv2;

/* 프로그래머스 72411번 메뉴 리뉴얼 문제

[문제 풀이]
course에 있는 숫자대로 조합 만들어서 map에 저장한 다음에 주문 들어온 수 세기(getOrDefault 써서)
그래서 2명 이상이 시킨 메뉴만 출력하게 하기
-> 라고 생각했는데 문제를 똑바로 읽었어야 했다 ㅋㅋ;;

최소 2가지 이상의 단품 메뉴로 구성 + 최소 2명 이상의 손님으로부터 주문된 메뉴라고만 생각해서 그렇게 짰던건데,, 당연히 테스트케이스부터 통과되지 않는당..ㅎㅎ
-> 코스요리 가짓수별로 "가장 많이" 함께 주문한 메뉴만 하는거라.. 만약에 2가지 구성으로 AB 4번 주문, BC 3번 주문, CD 4번 주문 이렇게 있다치면 AB랑 CD만 파는거다. BC는 아니고;;
이거까지 고려해서 짜면 쉽다 ㅎㅎ

*/

import java.util.*;

class Q72411_kj {
    public String[] solution(String[] orders, int[] course) {

        Map<Integer, Map<String, Integer>> courseMapPerNumber = new HashMap<>();

        for (String o : orders) {

            String[] order = o.split("");
            Arrays.sort(order); // AB, BA 둘 다 똑같은거니까..!! 미리 정렬해서 조합하게 하기

            for (int courseNum : course) {

                if (!courseMapPerNumber.containsKey(courseNum)) {
                    courseMapPerNumber.put(courseNum, new HashMap<>());
                }

                Map<String, Integer> courseMap = courseMapPerNumber.get(courseNum);
                combination(courseNum, 0, courseMap, order, "", 0);
            }
        }

        List<String> answer = new ArrayList<>();
        for (int courseNum : courseMapPerNumber.keySet()) {

            Map<String, Integer> courseMap = courseMapPerNumber.get(courseNum);

            // 가짓수별로 가장 많이 시킨 메뉴 개수 세기
            int max = 0;
            for (String key : courseMap.keySet()) {
                int count = courseMap.get(key);
                if (count >= 2) {
                    max = Math.max(max, count);
                }
            }

            // 가장 많이 시킨 메뉴를 list에 추가
            for (String key : courseMap.keySet()) {
                int count = courseMap.get(key);
                if (count == max) {
                    answer.add(key);
                }
            }

        }

        Collections.sort(answer); // 정렬된 값 반환해야 하니까 정렬 먼저~

        return answer.toArray(new String[answer.size()]);
    }

    private void combination(int courseNum, int depth, Map<String, Integer> courseMap, String[] order, String courseStr, int index) {

        if (depth == courseNum) {

            // 원하는 코스 조합 수에 도달한 경우
            courseMap.put(courseStr, courseMap.getOrDefault(courseStr, 0) + 1);
            return;
        }

        for (int i = index; i < order.length; i++) {
            combination(courseNum, depth + 1, courseMap, order, courseStr + order[i], i + 1);
        }
    }
}
