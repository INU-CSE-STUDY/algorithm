package programmers.lv2;

/* 프로그래머스 68645번 삼각 달팽이 문제

[문제 풀이]
문제 보고 느낀건,, 그냥 n*n 사이즈 행렬로 만든 다음에, 직접 하나씩 다 채우기! 그러고 하나씩 다 출력하기!
n이 1000까지라 무리 없다고 생각..

top = 0, bottom = n - 1, left = 0, right = n - 1으로 시작해서 빙글뱅글 돌면서 채울 수 있게 하기??

입출력 예시에 있는 애들은 다 잘 돌아갔는데 제출했을 때 틀림 -> 큰 수에 대해서 처리를 제대로 못해준다는 뜻
n = 7일 때부터 가장 바깥의 삼각형안에 작은 삼각형이 있고, 그 안에 어떤 수가 있을 때 right 값 처리를 제대로 못해줘서 틀렸던 것..!!
나머지는 다 제대로 바뀌고 있는데 삼각형 안의 삼각형 안의 ... 이런 식으로 들어갈 수록 right의 값이 더 줄어야 하는데 그냥 -1로만 해서 틀렸던 것.
삼각형 하나 돌 때마다 minusValue를 1씩 증가시켜서 빼주는 거로 해결했다!
*/

import java.util.*;

class Q68645_kj {
    public int[] solution(int n) {

        int[][] triangle = new int[n][n];

        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;

        int num = 1;
        int minusValue = 1;
        while (top < bottom || left <= right) {
            // for문 세 개 -> 삼각형 한 개, 바깥쪽 삼각형부터 안쪽 삼각형 완성하기

            for (int i = top; i <= bottom; i++) {
                // 왼쪽 빗변 채우기
                triangle[i][left] = num++;
            }
            top++; // 삼각형의 위쪽 꼭지점은 이미 채워졌음!
            left++; // 삼각형의 왼쪽 빗변은 다 채워졌음!

            for (int i = left; i <= right; i++) {
                // 밑변 채우기
                triangle[bottom][i] = num++;
            }
            bottom--; // 삼각형의 밑변은 다 채워졌음!

            for (int i = bottom; i >= top; i--) {
                triangle[i][--right] = num++;
            }
            top++; // 삼각형의 오른쪽 빗변을 다 채움
            right = bottom - (minusValue++);
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer.add(triangle[i][j]);
            }
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}