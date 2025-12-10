package programmers.lv2;

/* 프로그래머스 68936번 쿼드압축 후 개수 세기

[문제 풀이]
첫줄의 첫 칸을 temp로 두고 비교하다가 다른 것이 나오면 4등분
모두 같은 수라면 해당 answer의 temp값 ++
*/
class Q68936_lth {
    int[] answer = new int[2];
    int[][] arr;

    public int[] solution(int[][] arr) {
        this.arr = arr;
        divide(0, 0, arr.length);
        return answer;
    }

    private void divide(int x, int y, int size) {
        // 모두 같은 값인지 확인
        if (check(x, y, size)) {
            answer[arr[x][y]]++;
            return;
        }

        int half = size / 2;

        divide(x, y, half);
        divide(x + half, y, half);
        divide(x, y + half, half);
        divide(x + half, y + half, half);
    }

    private boolean check(int x, int y, int size) {
        int first = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != first) return false;
            }
        }
        return true;
    }
}
