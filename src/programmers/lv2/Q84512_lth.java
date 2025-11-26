package programmers.lv2;

/* 프로그래머스 84512번 모음사전 문제

[문제 풀이]
첫번째문자가 바뀌는데 드는 수부터 다섯번째문자가 바뀌는데 드는 수(가중치)를 구함
문자열을 잘라서 문자단위로 바꾸고 첫번째문자부터 계산

완전탐색문제라 했지만 수학적 규칙을 찾아서 푸는게 쉽고 빠를거 같아서 이렇게 풀었다
*/



class Q84512_lth {
    public int solution(String word) {
        int answer = 0;

        // A, E, I, O, U 인덱스 매핑
        char[] dict = {'A', 'E', 'I', 'O', 'U'};

        // 자리별 가중치
        // 5^4 + 5^3 + 5^2 + 5 + 1, 5^3 + 5^2 + 5 + 1, ...
        int[] weight = {781, 156, 31, 6, 1};

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            // 문자 인덱스 * 자리 가중치
            for (int j = 0; j < 5; j++) {
                if (dict[j] == c) {
                    answer += j * weight[i];
                    break;
                }
            }

            // 해당 자리가 포함되었으니 +1 
            // ex) eio = 1 * 781 + 2 * 156 + 3 * 31 + 3(글자수) 
            answer += 1;
        }

        return answer;
    }
}
