package programmers.lv2;

/* 프로그래머스 17687번 n진수 게임

[문제 풀이]
그으냥 반복문 돌리면서 숫자를 n진수로 변환하고 자기 차례일 때만 말하게 하면 될 듯하네용
완전탐색 느낌

*/

class Q17687_kj {
    public String solution(int n, int t, int m, int p) {

        int turn = 0; // 현재 턴
        int nowNumber = -1; // 현재 차례에 해당하는 10진수 수

        StringBuilder answer = new StringBuilder();
        while (answer.length() < t) {

            nowNumber++;
            String nowNumberStr = Integer.toString(nowNumber, n);

            for (int i = 0; (i < nowNumberStr.length() && answer.length() < t); i++) {

                if (turn % m == (p - 1)) {
                    // 현재 튜브 차례라면 말해야 함
                    answer.append(nowNumberStr.charAt(i));
                }

                turn++;
            }
        }

        return answer.toString().toUpperCase();
    }
}
