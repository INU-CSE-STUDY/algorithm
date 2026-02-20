package programmers.lv2;

/* 프로그래머스 42577번 전화번호 목록 문제

[문제 풀이]
String 배열 정렬하면 사전순으로 정렬되니까 index, index + 1을 startsWith로 비교하면서 시작하는지 안하는지 확인하기

*/

import java.util.*;

class Q42577_kj {
    public boolean solution(String[] phone_book) {

        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {

            String number1 = phone_book[i];
            String number2 = phone_book[i + 1];

            if (number2.startsWith(number1)) return false;
        }

        return true;
    }
}