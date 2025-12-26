package programmers.lv2;

/* 프로그래머스 43165번 타켓 넘버 문제

[문제 풀이]
그냥 완탐문제
*/



class Solution {
    int answer = 0;

    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }

    private void dfs(int[] numbers, int target, int idx, int sum) {
        // 종료 조건
        if (idx == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }

        // + 선택
        dfs(numbers, target, idx + 1, sum + numbers[idx]);

        // - 선택
        dfs(numbers, target, idx + 1, sum - numbers[idx]);
    }
}
