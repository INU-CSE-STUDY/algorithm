package programmers.lv2;

/* 프로그래머스 43165번 타겟 넘버 문제

[문제 풀이]
문제 보고 딱 든 생각은,, 백트래킹??
0으로 시작해서 인덱스 위치에 있는 숫자를 더하거나 빼기 하는거임
그렇게 쭉쭉 진행해서 마지막 인덱스에 도달했을 때 타겟이랑 같은지 확인해서 answer 증가시키기?

*/

class Q43165_kj {

    int answer = 0;

    public int solution(int[] numbers, int target) {

        backtrack(numbers, target, 0, 0);

        return answer;
    }

    private void backtrack(int[] numbers, int target, int index, int sum) {

        if (index == numbers.length) {
            // 종료지점일 경우, 정수의 계산이 타겟 넘버와 일치하는지 확인해야 함

            if (sum == target) answer++;
            return;
        }

        // 인덱스를 하나씩 더하거나 빼면서 진행
        backtrack(numbers, target, index + 1, sum + numbers[index]);
        backtrack(numbers, target, index + 1, sum - numbers[index]);

    }
}
