package programmers.lv2;

/* 프로그래머스 17686번 파일명 정렬 문제

[문제 풀이]
문자열을 앞에서부터 한 글자씩 돌면서 처음 숫자가 등장하는 위치를 찾고 그 뒤로 최대 5자리의 숫자를 읽는다
숫자 전은 head 숫자부터 number로 잘라서 정렬
-> 정규 표현식을 배워야 할 거 같아서 정규표현식으로 바꿈

*/

import java.util.*;
import java.util.regex.*;

class Q17686_lth {
    public String[] solution(String[] files) {

        Arrays.sort(files, (a, b) -> {
            Pattern p = Pattern.compile("^([^0-9]+)([0-9]{1,5})(.*)$");
            /*  
            ^ : 문자열 “시작”
            [^0-9] : 대괄호 []는 “문자 1개”를 의미하는 범위
            ^가 대괄호 안에 있으면 부정(Not)
            + : “1개 이상 반복”
            ([^0-9]+) : 숫자가 나오기 전까지의 문자들을 1개 이상 쭉 잡는다

            {1,5} : “1개 이상 5개 이하 반복”

            . : “아무 문자 1개”
            * : “0개 이상 반복”
            (.*) : 남은 문자열 전부
            */

           // 정규식 p를 문자열 a에 적용할 준비를 하는 객체를 만든다
            Matcher m1 = p.matcher(a);
            Matcher m2 = p.matcher(b);

            m1.matches();
            m2.matches();

            // 대소문자 동일하게
            String head1 = m1.group(1).toLowerCase();
            String head2 = m2.group(1).toLowerCase();

            // HEAD 비교 (대소문자 무시)
            int headCompare = head1.compareTo(head2);
            if (headCompare != 0) {
                return headCompare;
            }

            // NUMBER 비교
            int num1 = Integer.parseInt(m1.group(2));
            int num2 = Integer.parseInt(m2.group(2));

            return num1 - num2;
        });

        return files;
    }
}