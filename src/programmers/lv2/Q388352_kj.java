package programmers.lv2;

/* 프로그래머스 388352번 비밀 코드 해독 문제

[제약 조건]
n: 서로 다른 정수의 개수 10 <= n <= 30
m = q.length = ans.length
   1 <= m(= q.length = ans.length) <= 10 - 최대 10번까지 시도 가능 / 오름차순으로 정렬되어 있음
   0 <= ans[i] <= 5 - 당연하겠지만, 한 번의 시도횟수에는 정답이 0 ~ 5까지만 가능

1 <= 비밀 코드에 포함된 정수 <= n

[문제 풀이]
완전탐색으로 풀기?
1 - n까지 오름차순/중복 없는 5개를 뽑아서 되는 경우 횟수 더해주기(조합으로 계산)
- 조합으로 모든 경우를 다 뽑은 다음에 q와 ans 배열 결과와 일치하는지 여부를 보고 결정하면 됨!
*/
import java.util.*;

class Q388352_kj {
    static int LENGTH = 5; // 비밀 코드의 길이
    static ArrayList<int[]> combinationList;
    static int[] combination;
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;

        combinationList = new ArrayList<>();
        getCombinationList(n);
        for (int[] comb : combinationList) {
            if (isPossibleCase(comb, q, ans)) {
                answer++;
            }
        }

        return answer;
    }

    private void getCombinationList(int n) {
        combination = new int[LENGTH];
        getCombination(1, n, 0);
    }

    private void getCombination(int start, int n, int depth) {
        // 조합이 완성된 경우 종료
        if (depth == LENGTH) {
            combinationList.add(combination.clone());
            return;
        }

        for (int i = start; i <= n; i++) {
            // 아까의 5중 for문을 재귀 방식으로 변경..!
            combination[depth] = i;
            getCombination(i + 1, n, depth + 1);
        }
    }

    private boolean isPossibleCase(int[] combination, int[][] q, int[] ans) {
        for (int i = 0; i < q.length; i++) {
            int match = count(combination, q[i]);
            if (match != ans[i]) {
                return false;
            }
        }

        return true;
    }

    private int count(int[] combination, int[] question) {
        Set<Integer> qSet = new HashSet<>();
        for (int num : question) {
            qSet.add(num);
        }

        int count = 0;
        for (int num : combination) {
            // for문 사용을 줄이기 위해 set에 값을 넣고, contains 메서드로 비교
            if (qSet.contains(num)) {
                count++;
            }
        }

        return count;
    }
}

/* 진이는 for문이 좋아~~
import java.util.*;

class Solution {
    static ArrayList<int[]> combinationList;

    public int solution(int n, int[][] q, int[] ans) {
        // 가능한 조합을 구함
        combinationList = new ArrayList<>();
        combination(n);

        int answer = 0;
        for (int i = 0; i < combinationList.size(); i++) {
            int[] comb = combinationList.get(i);

            if (isPossibleCase(comb, q, ans)) {
                answer++;
            }
        }

        return answer;
    }

    private static void combination(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                for (int k = j + 1; k <= n; k++) {
                    for (int l = k + 1; l <= n; l++) {
                        for (int m = l + 1; m <= n; m++) {
                            combinationList.add(new int[]{ i, j, k, l, m });
                        }
                    }
                }
            }
        }
    }

    private static boolean isPossibleCase(int[] comb, int[][] q, int[] ans) {
        boolean isPossible = true;

        for (int i = 0; i < q.length; i++) {
            int[] question = q[i];
            int answer = ans[i];

            int count = 0;
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (comb[j] == question[k]) count++;
                }
            }

            if (count != answer) {
                // 하나라도 조건에 맞지 않으면 종료
                isPossible = false;
                break;
            }
        }

        return isPossible;
    }
}
*/