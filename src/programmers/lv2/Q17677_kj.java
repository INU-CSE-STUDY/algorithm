package programmers.lv2;

/* 프로그래머스 17677번 뉴스 클러스터링 문제

[문제 풀이]
list.addAll() - 합집합
list.retainAll() - 교집합
list.removeAll() - 차집합 라고 생각했는데 이렇게 문제 푸는게 아니었다. 다중 집합이어서;;

리스트 생성 후, 교집합과 합집합을 직접 구해서 계산하면 됨!

*/

import java.util.*;

class Q17677_kj {

    static final int NUMBER = 65536;

    public int solution(String str1, String str2) {

        List<String> str1SetList = getStrSetList(str1.toUpperCase());
        List<String> str2SetList = getStrSetList(str2.toUpperCase());

        List<String> unionList = new ArrayList<>();
        List<String> intersectionList = new ArrayList<>();

        for (String s : str1SetList) {

            if (str2SetList.contains(s)) {
                // str1과 str2 모두 존재하는 거면 교집합에만 추가
                str2SetList.remove(s);
                intersectionList.add(s);
            }

            // 합집합은 다 추가해야 하니까 하나씩 추가
            unionList.add(s);
        }
        // str2 리스트에 남은 원소도 추가
        unionList.addAll(str2SetList);

        int unionCount = unionList.size();
        int intersectionCount = intersectionList.size();


        if (unionCount == 0) return 1 * NUMBER;

        double answer = ((double) intersectionCount / unionCount) * NUMBER;
        return (int) answer;
    }

    private List<String> getStrSetList(String str) {

        List<String> strSetList = new ArrayList<>();

        String regex = ".*[^a-zA-Z].*"; // 영문자 제외 다른 문자가 들어있는지 확인할 정규식
        for (int i = 0; i < str.length() - 1; i++) {

            String substringStr = str.substring(i, i + 2);

            // 영문자 제외 다른 문자가 들어있다면, 해당 글자 쌍을 버림
            if (substringStr.matches(regex)) continue;

            strSetList.add(substringStr); // 영문자로만 구성된 글자쌍만 list에 삽입
        }

        return strSetList;
    }
}
