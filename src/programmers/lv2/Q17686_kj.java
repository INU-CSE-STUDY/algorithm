package programmers.lv2;

/* 프로그래머스 17686번 파일명 정렬 문제

[문제 풀이]
내 생각엔,, 정렬 옵션을 커스텀해서 하면 된다,,
split해서 head, number, tail로 나누고 문제에서 주어진 대로 정렬하기
-> split은 정규식으로 자르기! 그룹 나눠서 하면 되니깡,,
- head의 경우, 사전 순 정렬은 똑같은데 대소문자 구분 안한댔으니까 그냥 uppercase로 통일해서 사전순 정렬되게 하기
- number의 경우, integer로 타입 바꾸고 그거 순서대로 정렬되게 하기
- tail은 따로 정렬 기준없음. 기존 순서 유지

*/

import java.util.*;
import java.util.regex.*;

class Q17686_kj {
    public String[] solution(String[] files) {

        // 문제에서 주어진 조건대로 3가지 구간으로 나누는 정규표현식
        Pattern pattern = Pattern.compile("^([^0-9]+)([0-9]+)(.*)$");

        Arrays.sort(files, new Comparator<>() {
            @Override
            public int compare(String s1, String s2) {

                Matcher s1Matcher = pattern.matcher(s1);
                Matcher s2Matcher = pattern.matcher(s2);

                s1Matcher.matches();
                s2Matcher.matches();

                // head 부분 비교 - 대소문자 구분을 하지 않기 때문에 대문자로 통일
                String s1Head = s1Matcher.group(1).toUpperCase();
                String s2Head = s2Matcher.group(1).toUpperCase();

                // 문자열이 같은 경우에는 number 부분 정렬로 해야 하니까, 같지 않은 경우에만 정렬하기
                if (!s1Head.equals(s2Head)) return s1Head.compareTo(s2Head);

                // number 부분 비교 - 오름차순 정렬
                int s1Number = Integer.parseInt(s1Matcher.group(2));
                int s2Number = Integer.parseInt(s2Matcher.group(2));
                return s1Number - s2Number;
            }
        });

        return files;
    }
}
