package programmers.lv2;

/* 프로그래머스 12951번 JadenCase 문자열 만들기 문제

[문제 풀이]
전체 문자열을 싹 다 소문자로 바꾼 다음에 한글자씩 순회해서 첫 글자이거나 이전 글자가 공백이면 대문자로 넣기
*/

class Q12951_kj {
    public String solution(String s) {

        s = s.toLowerCase(); // 전체 문자열을 소문자로 변경
        char[] arr = s.toCharArray();

        StringBuilder answer = new StringBuilder(); // 정답 저장용
        for (int i = 0; i < arr.length; i++) {

            char now = arr[i];

            if (i == 0 || arr[i - 1] == ' ') {
                // 공백 이후 나오는 첫 문자의 경우 대문자
                answer.append(Character.toUpperCase(now)); // 숫자든 공백이든 상관없이 다 적용됨
            } else {
                // 이외의 문자는 소문자 그대로 넣기
                answer.append(now);
            }
        }

        return answer.toString();
    }
}
