package programmers.lv3;

/* 프로그래머스 258709번 주사위 고르기 문제

[문제 풀이]
조합 구해서, 나올 수 있는 주사위합을 싹 다 저장한 다음에 정렬하기
A 합, B 합 정렬된 배열 가지고 이분탐색해서 A의 합에 해당하는 거 B 몇 개 이길 수 있는지 직접 다 세기 -> 다 합산해서 나온게 승리 횟수
이 승리 횟수 가장 높은 조합 반환하면 됨
*/

import java.util.*;

class Q258709_kj {
    List<List<Integer>> combA = new ArrayList<>();
    List<List<Integer>> combB = new ArrayList<>();

    public int[] solution(int[][] dice) {
        int[] answer = {};
        getDiceCombination(dice, 0, new ArrayList<Integer>());

        int maxCntIdx = getSolution(dice);
        List<Integer> bestComb = combA.get(maxCntIdx);

        // 리스트에는 index로 저장되어있을테니, 각각 +1씩 해줘서 1번 주사위, 2번 주사위 이런 식으로 나올 수 있게!
        return bestComb.stream().mapToInt(i -> i + 1).toArray();
    }

    // 가능한 전체 A, B 조합을 구함
    private void getDiceCombination(int[][] dice, int index, List<Integer> comb) {
        if (comb.size() == (dice.length / 2)) {
            // 종료조건: A가 가질 수 있는 최대 주사위 수에 도달
            List<Integer> A = new ArrayList<>(comb);
            List<Integer> B = new ArrayList<>();
            combA.add(A);

            // B가 가질 수 있는 조합도 결정되므로 B 조합도 저장
            for (int i = 0; i < dice.length; i++) {
                if (comb.contains(i)) continue;
                B.add(i);
            }
            combB.add(B);

            return;
        }

        for (int i = index; i < dice.length; i++) {
            comb.add(i);
            getDiceCombination(dice, i + 1, comb);
            comb.remove(comb.size() - 1);
        }
    }

    // 해당 조합에서 가능한 합을 모두 구함
    private void getSumList(int[][] dice, List<Integer> list, int diceIdx, int sum, List<Integer> sumList) {
        if (list.size() == diceIdx) {
            sumList.add(sum);
            return;
        }

        int nowDice = list.get(diceIdx);
        for (int i = 0; i < 6; i++) {
            getSumList(dice, list, diceIdx + 1, sum + dice[nowDice][i], sumList);
        }
    }

    // 해당 조합에서 가능한 승리 횟수를 구함
    private int getSolution(int[][] dice) {
        int maxWinCnt = -1;
        int maxWinIdx = -1;

        for (int i = 0; i < combA.size(); i++) {
            List<Integer> sumAList = new ArrayList<>();
            List<Integer> sumBList = new ArrayList<>();

            // 각각의 조합에서 합을 구함
            getSumList(dice, combA.get(i), 0, 0, sumAList);
            getSumList(dice, combB.get(i), 0, 0, sumBList);

            // 이분탐색을 위해 정렬
            Collections.sort(sumAList);
            Collections.sort(sumBList);

            int winCnt = getWinCount(sumAList, sumBList);

            // 더 승리횟수가 많은 조합이 있다면 값 변경
            if (winCnt > maxWinCnt) {
                maxWinCnt = winCnt;
                maxWinIdx = i;
            }
        }

        // 해당 조합의 위치값(index)을 반환
        return maxWinIdx;
    }

    private int getWinCount(List<Integer> sumA, List<Integer> sumB) {
        int result = 0;

        for (int aSum : sumA) {
            int left = 0;
            int right = sumB.size();

            // sumB에도 중복 값이 있을 가능성이 있기 때문에.. 중복 값을 발견한다고 끝내면 안됨!
            while (left < right) {
                int mid = (left + right) / 2;

                if (sumB.get(mid) < aSum) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            result += left;
        }

        return result;
    }
}