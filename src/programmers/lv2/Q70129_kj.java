package programmers.lv2;

/* 프로그래머스 70129번 이진 변환 반복하기 문제

[문제 풀이]
1. x의 모든 0을 제거 -> replaceAll로 제거
2. x의 길이를 c -> c = x.length();
   c를 2진법으로 표현한 문자열로 변경 -> Integer.toString(c, 2);

s가 1이 될 때까지 이진 변환을 가했을 때, 변환 횟수와 변환 과정에서 제거된 모든 0의 개수 담기
-> convertCount, removeZeroCount 변수 만들어서 변환 할 때마다 1씩 증가하고, 처음 길이 - 0 제거한 길이 빼서 제거된 0의 개수 더하면 되는 쉬운 문제인듯?

*/

class Q70129_kj {
    public int[] solution(String s) {

        int[] answer = new int[2];
        int convertCount = 0, removeZeroCount = 0;

        while (!s.equals("1")) {

            convertCount++; // 변환 횟수 증가

            String removeZeroS = s.replaceAll("0", ""); // 모든 0을 제거

            int length = s.length(); // 기존 s의 길이
            int removeZeroLength = removeZeroS.length(); // s에서 0을 제거한 만큼의 길이

            removeZeroCount += (length - removeZeroLength); // 제거된 0의 개수 더하기

            s = Integer.toString(removeZeroLength, 2); // s에서 0을 제거한 문자열의 길이를 2진법으로 표현한 문자열로 변경
        }


        return new int[]{ convertCount, removeZeroCount };
    }
}
