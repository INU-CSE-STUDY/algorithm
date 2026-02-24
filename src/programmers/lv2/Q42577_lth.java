package programmers.lv2;

/* 프로그래머스 42577번 전화번호 목록 문제

[문제 풀이]
정렬이 코드가 깔끔해보이지만 hash문제이기에 hash로 했다
hashset에 전부 넣고 모든 번호를 서로 비교해보는 방식으로
각 번호를 첫번째부터 마지막 전 문자까지 점점 포함시키며 검사한다
시간이 오래 걸릴거 같지만 hashset으로 확인하는 것이기에 금방 끝난다

*/

import java.util.*;

class Q42577_lth {
    public boolean solution(String[] phone_book) {
        HashSet<String> set = new HashSet<>();

        for (String s : phone_book) {
            set.add(s);
        }

        // 모든 번호를 서로 검사
        for (String s : phone_book) {
            for (int i = 1; i < s.length(); i++) {      // 매문자를 점점 포함시키며 검사
                String prefix = s.substring(0, i);      // i까지 자르기
                if (set.contains(prefix)) {             // 존재한다면
                    return false;
                }
            }
        }

        return true;
    }
}