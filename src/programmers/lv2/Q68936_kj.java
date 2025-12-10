package programmers.lv2;

/* 프로그래머스 68936번 쿼드압축 후 개수 세기

[문제 풀이]
분할정복 문제

S 내부에 있는 모든 수가 같은 수라면 압축하고(= 더 이상 나누지 않음), 아니라면 다시 4등분하면서 진행하는 방식 => 분할 정복
이거 분할을 어떻게 하는거지??
i, j 같은거 넣어서 하는거겠지

arr 하나 가지고 균등하게 4분할 하려면 가로도 절반 세로도 절반해서 해야 함
그러면 쉽다!

zip(arr, row, column, length);
zip(arr, row + length, column, length);
zip(arr, row, column + length, length);
zip(arr, row + length, column + length, length);

딱 이 형태로 분할정복하면 됨!
*/

class Q68936_kj {

    int[] answer;

    public int[] solution(int[][] arr) {

        answer = new int[2];

        zip(arr, 0, 0, arr.length);

        return answer;
    }

    private void zip(int[][] arr, int row, int column, int length) {

        int number = arr[row][column]; // 현재 정사각형의 가장 앞에 있는 수

        if (allSameNumber(arr, row, column, length, number)) {

            answer[number]++; // S 내부에 있는 모든 수가 같은 수인 경우 값 증가 (어차피 숫자는 0 또는 1이고, return 해야 하는 값도 0, 1 순서니까 이렇게 쓸 수 있음ㅎㅎ)

            // 모두 같은 수라면 압축하기(더 이상 나눌 필요 없으므로 return)
            return;
        }

        // 하나라도 다른 수라면 압축이 필요

        length /= 2; // 균일한 정사각형 4개로 나누려면 가로 세로 모두 절반씩 잘라야 함

        zip(arr, row, column, length);
        zip(arr, row + length, column, length);
        zip(arr, row, column + length, length);
        zip(arr, row + length, column + length, length);
    }

    private boolean allSameNumber(int[][] arr, int row, int column, int length, int number) {

        for (int i = row; i < row + length; i++) {
            for (int j = column; j < column + length; j++) {
                // 하나라도 다른 숫자일 경우 return false
                if (arr[i][j] != number) return false;
            }
        }

        // 모두 같은 수인 경우 return true
        return true;
    }
}