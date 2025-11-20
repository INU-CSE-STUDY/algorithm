package programmers.lv2;

/* 프로그래머스 87390번 n^2 배열 자르기 문제

[문제 풀이]

n = 3이면 아래와 같은 2차원 배열이 만들어지고
1 2 3
2 2 3
3 3 3

n = 4일 때
1 2 3 4
2 2 3 4
3 3 3 4
4 4 4 4

(0, 0) = 1
(0, 1) = (1, 1) = (1, 0) = 2
(0, 2) = (1, 2) = (2, 2) = (2, 1) = (2, 0) = 3
... 이렇게 가는거니까
Math.max(row, col) + 1 = 해당 칸의 숫자값이라는 규칙이 있음

또, 1차원 배열로 만들었을 때 index와 row/column 과의 관계는
row = index / n, col = index % n 이거!

*/

class Q87390_kj {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left) + 1];

        int answerIdx = 0;
        for (long index = left; index <= right; index++) {
            long row = index / n;
            long col = index % n;

            int num = (int) Math.max(row, col) + 1;
            answer[answerIdx++] = num;
        }

        return answer;
    }
}