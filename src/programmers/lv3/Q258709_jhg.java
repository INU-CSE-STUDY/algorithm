package programmers.lv3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 처음에는 완탐을 생각함
 * 주사위 5개 고르는 행위 10C5
 * a, b 각각의 점수를 구함 N = (6^5), 2N
 * 이중 for문으로 승리 횟수 카운트 N^2
 * -> 10C5 * (2N + N^2) 시간초과 날 거 같다
 * 그럼 이진 탐색으로 효율적으로 접근
 * 주사위 5개 고르는 행위 10C5
 * a, b 각각의 점수를 구하면서 정렬 N = (6^5), 2NlogN
 * -> 정렬 이후 모든 N에 대하여 lower bound 시도 NlogN
 * --> 정렬하고 a의 n점 보다 낮은 b의 점수가 a가 이기는 횟수
 * -> 10C5 * (2NlogN + NlogN) 시간초과 안난다!!
 */
public class Q258709_jhg {

    public int[] solution(int[][] dice) {
        List<DiceChoiceList> choices = DiceChoiceList.of(dice);

        DiceChoiceList answer = choices.get(0);

        for (int i = 1; i < choices.size(); i++) {
            DiceChoiceList choice = choices.get(i);
            answer = answer.isAnswer(choice) ? choice : answer;
        }


        return answer.getAnswer();
    }

    public static class DiceChoiceList extends ArrayList<Integer> {

        static int[][] dices;
        static boolean[] visited;
        static int N;
        static List<DiceChoiceList> result;
        Long count;

        // 주사위 조합을 고름.
        public static List<DiceChoiceList> of(int[][] dice) {
            visited = new boolean[dice.length];
            N = dice.length;
            dices = dice;
            result = new ArrayList<>();

            setDice(-1, 0);

            return result;
        }

        // 2개의 초이스 List를 비교해서
        public boolean isAnswer(DiceChoiceList target) {
            long thisCount = this.getWinCount();
            long newCount = target.getWinCount();

            return thisCount < newCount;
        }

        // 정답을 구함
        public int[] getAnswer() {
            return this.stream()
                    .mapToInt(i -> i)
                    .sorted()
                    .toArray();
        }

        // N / 2개의 조합을 찾음
        private static void setDice(int now, int count) {
            if (count == N / 2) {
                DiceChoiceList list = new DiceChoiceList();
                for (int i = 0; i < N; i++) {
                    if (visited[i]) {
                        list.add(i+1);
                    }
                }
                result.add(list);
                return;
            }

            for (int i = now + 1; i < N; i++) {
                visited[i] = true;
                setDice(i, count + 1);
                visited[i] = false;
            }
        }

        // A가 선택하고 남은 찌거기
        private DiceChoiceList getSubList() {
            DiceChoiceList set = new DiceChoiceList();

            for (int i = 1; i <= N; i++) {
                if (!this.contains(i)) {
                    set.add(i);
                }
            }

            return set;
        }

        private long getWinCount() {
            if (count == null) {
                DiceChoiceList aSet = this;
                DiceChoiceList bSet = getSubList();
                DiceList aList = DiceList.of(dices, aSet);
                DiceList bList = DiceList.of(dices, bSet);
                count = aList.getWinCount(bList);
            }

            return count;
        }
    }

    public static class DiceList extends ArrayList<List<Integer>> {

        private DiceList(List<List<Integer>> diceNumbers) {
            this.addAll(diceNumbers);
        }

        public static DiceList of(int[][] dice, DiceChoiceList set) {
            return new DiceList(set.stream()
                    .map(index ->  Arrays.stream(dice[index - 1])
                            .boxed()
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList()));
        }

        public long getWinCount(DiceList bList) {
            List<Integer> aScore = this.getScore();
            List<Integer> bScore = bList.getScore();

            long count = 0;

            for (int score: aScore) {
                int low = 0;
                int high = bScore.size() - 1;

                while (low <= high) {
                    int mid = (low + high) / 2;

                    if (bScore.get(mid) >= score) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }

                count += high;
            }

            return count;
        }

        // 해당 주사위 셋에서 가능한 점수 계산
        private List<Integer> getScore() {
            List<Integer> score = new ArrayList<>();

            findScore(score, 0, 0);

            return score.stream()
                    .sorted()
                    .collect(Collectors.toList());
        }

        // 가능한 점수를 찾는 배열
        private void findScore(List<Integer> scores, int count, int score) {
            if (count == this.size()) {
                scores.add(score);
                return;
            }

            for (int i = 0; i < 6; i++) {
                findScore(scores, count+1, score + this.get(count).get(i));
            }
        }
    }
}
