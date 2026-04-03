package programmers.lv2;

/* 프로그래머스 12951번 JadenCase 문자열 만들기 문제

[문제 풀이]
공백 기준으로 문자열을 자르는 데 -1옵션으로 마지막에 공백이 있다면 공백도 유지한다
자른 문자열을 소문자로 바꾸고 첫번째가 문자면 대문자로 변환
*/

class Q12951_lth {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        String[] arr = s.split(" ", -1); // 공백 유지

        for (int i = 0; i < arr.length; i++) {
            String word = arr[i];

            if (word.length() > 0) {
                word = word.toLowerCase();
                char[] chars = word.toCharArray();

                if (Character.isLetter(chars[0])) {
                    chars[0] = Character.toUpperCase(chars[0]);
                }

                answer.append(chars);
            }

            // 마지막 단어가 아니면 공백 추가
            if (i < arr.length - 1) {
                answer.append(" ");
            }
        }

        return answer.toString();
    }
}