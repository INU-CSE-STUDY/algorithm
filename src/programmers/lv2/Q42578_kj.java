package programmers.lv2;

/* 프로그래머스 42578번 의상 문제

[문제 풀이]
의상의 종류가 같은 경우 그 중 1가지만 입을 수 있고, 아예 안입을 수도 있음
- 해당 의상을 고르는 경우의 수는 의상의 종류의 개수 + 1 // 예제처럼 안경, 선글라스 존재 시 안경, 선글라스, 아무것도 쓰지 않음 이렇게 총 세가지 경우가 나옴
>> 한 의상 종류마다 가능한 가짓수는 해당 의상의 개수 + 1

그러면 1씩 더해서 조합 구한다음에, 아무 것도 안입는 경우(1가지)만 빼주면 됨!
- 코니는 하루에 최소 한 개의 의상을 입으니까..

*/

import java.util.*;

class Q42578_kj {
    public int solution(String[][] clothes) {

        Map<String, Integer> typeMap = new HashMap<>();
        for (String[] clothe : clothes) {
            // clothe[1]에 해당하는 의상 개수 저장하기
            typeMap.put(clothe[1], typeMap.getOrDefault(clothe[1], 0) + 1);
        }

        int answer = 1;
        for (int typeCount : typeMap.values()) {
            // 해당 의상의 종류를 입지 않은 경우를 고려해서 +1 한 값을 곱하기
            answer *= (typeCount + 1);
        }

        // 아무 의상도 입지 않은 경우 빼기
        return answer - 1;
    }
}