package programmers.lv2;

/* 프로그래머스 172927번 광물 캐기 문제

[문제 풀이]
광물 5개는 무조건 세트메뉴로 캐야 하니까 광물 그룹을 생성!
- 각 광물에 가중치를 줘서(다이아 25, 철 5, 돌 1 / 돌곡괭이 비율 따라서!) 우리가 생성한 그룹을 가중치 순으로 정렬
그러면 가중치 높은 애들을 순서대로 좋은 곡괭이로 캘 수 있음! 그리디 문제?

캘 수 있는 광물 수: min(곡괭이로 캘 수 있는 광물 수, 전체 광물 수) // 모든 광물을 캐거나, 더 사용할 곡괭이가 없을 때까지 광물을 캐니까
저만큼 그룹을 생성한 뒤에, 정렬 후 단순 계산으로 풀이하기!
*/

import java.util.*;

class Q172927_kj {
    static List<Group> groupLists;
    public int solution(int[] picks, String[] minerals) {
        int totalPicks = getTotalPicks(picks);

        // 전체 광물 수와 곡괭이로 캘 수 있는 광물 수를 비교, 더 작은 값까지만 광물을 캘 수 있음
        int possibleMineralsCnt = Math.min(minerals.length, totalPicks * 5);

        groupLists = new ArrayList<>();
        makeGroupLists(possibleMineralsCnt, minerals);
        sortGroupLists();

        return getAnswer(picks);

    }

    // 전체 곡괭이 개수를 구하는 메서드
    private int getTotalPicks(int[] picks) {
        int totalPicks = 0;
        for (int num : picks) {
            totalPicks += num;
        }

        return totalPicks;
    }

    // 캘 수 있는 광물을 그룹으로 만드는 메서드
    private void makeGroupLists(int possibleMineralsCnt, String[] minerals) {
        for (int i = 0; i < possibleMineralsCnt; i += 5) {

            Group group = new Group(); // 초기 그룹 생성

            int diamond = 0, iron = 0, stone = 0;
            for (int j = i; j < i + 5; j++) {
                if (j == minerals.length) break; // 더 이상 캘 수 있는 광물이 존재하지않으면 종료

                // 각 그룹에 속한 광물의 개수 세기
                if (minerals[j].equals("diamond")) diamond++;
                else if (minerals[j].equals("iron")) iron++;
                else stone++;
            }

            group.diamond = diamond;
            group.iron = iron;
            group.stone = stone;
            group.value = (diamond * 25) + (iron * 5) + (stone * 1); // 광물별로 가중치를 다르게 줘서 계산

            groupLists.add(group);
        }
    }

    // 그룹 리스트를 가중치 기준으로 내림차순 정렬
    private void sortGroupLists() {
        Collections.sort(groupLists,
                Comparator.comparingInt(
                        (Group g) -> g.value).reversed()
        );
    }

    // 피로도를 구하는 함수
    private int getAnswer(int[] picks) {
        int dia = picks[0];
        int iron = picks[1];
        int stone = picks[2];

        int answer = 0;
        for (Group group : groupLists) {
            if (dia > 0) {
                dia--;
                answer += group.diamond * 1;
                answer += group.iron * 1;
                answer += group.stone * 1;
            } else if (iron > 0) {
                iron--;
                answer += group.diamond * 5;
                answer += group.iron * 1;
                answer += group.stone * 1;
            } else {
                stone--;
                answer += group.diamond * 25;
                answer += group.iron * 5;
                answer += group.stone * 1;
            }
        }

        return answer;
    }
}

class Group {
    int value;
    int diamond;
    int iron;
    int stone;

    public Group() {
        this.value = 0;
        this.diamond = 0;
        this.iron = 0;
        this.stone = 0;
    }
}
