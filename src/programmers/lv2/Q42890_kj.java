package programmers.lv2;

/* 프로그래머스 42890번 후보키 문제

[문제 풀이]
앞에서부터 set에 넣어가지고 특정 조합이 후보키 조건을 만족하는지 확인하면 될듯

1. 학번부터 시작해서 100 200 300 400 500 600 => 6개가 set에 들어가고 이건 relation 길이(= row)와 같음. 얘는 후보키 가능
- 그러면 학번을 포함한 모든 조합은 이제 확인해볼 필요가 없음, 최소성을 만족하지 않으니까!!
  학번 방문 체크해서 다음 조합에 넣지 않게 하기
2. 다음 이름으로 넘어가서 ryan apeach tube con muzi => 총 5개가 들어간거라 row랑 다름, 얘는 후보키 불가능
- 조합의 개수를 두개로 늘려서 이름/전공, 이름/학년 조합을 확인해봐야함
  이름/전공은 총 6개가 들어가니까 만족 good
  이름/학년은 5개 들어가서 만족 ㄴㄴ
  이름/전공 조합으로 최소성 만족했으니까 방문 체크하기
3. 이렇게 반복해서 방문하지 않는 애들 반복하면서 후보키 개수 세주기

생각해보니까 학번처럼, 만약 전공이 식별가능한 거였으면 이름+전공을 먼저 확인하면 안되는 거잖아.
전공이 최소성인건데 이름+전공이 최소성인게 아니잖아..
1개씩 다 돌려보고, 그 다음은 visited 배열 방문하지 않은 애들 대상으로 2개씩 다 돌려보고, 그 다음에 3개씩 돌려보고... 이런 식은 어떨까
-> 이 생각 자체는 좋았는데 visited 배열로 아예 접근하지 못하게 막는게 문제였음
최소성을 만족한다는 생각을 너무 단순하게 했던 것 같당...
한개만 만족하는 경우는 ㅇㅈ인데 (예시에서 학번의 경우), 두개 이상부터는 좀 문제가 됐다
예를 들어 학번, 이름, 전공, 학년, 전화번호 이런거라 치면
- 학번은 학번 자체로도 후보키였으니까 학번 빼고 생각하기
- 이름/전공 조합이 후보키가 됐으니 이름/전공을 빼고 생각해본다 치면..
  나중에 3개 조합할 때 이름/학년/전화번호 이렇게 3개 조합으로 후보키 구성할 수도 있었을텐데 (이름/전공이라는 후보키랑은 다른 조합이니까 최소성을 만족못하는 경우가 아님. 바람직한 경우임) 이름이 visited 배열에 막혀버리니까 틀리는 테케가 존재했던 것...

만족하는 후보키 조합을 저장하고(내 코드의 경우 무조건 조합 개수를 1, 2, 3, .. 이 순으로 하기때문에 앞에 나온게 무조건 최소성을 만족함. 이거 순서에 대해선 전혀 고려할 필요가 없음) 그 후보키를 그대로 담은 새로운 후보키 후보가 나오면 continue 할 수 있게 하기

아직도 조합.. 만드는게 어렵다 ㅠㅠㅠ 생각도 부족한 거 같고..

*/

import java.util.*;

class Q42890_kj {

    int row, column; // 릴레이션의 튜플과 속성 수

    public int solution(String[][] relation) {

        row = relation.length; // 릴레이션의 튜플
        column = relation[0].length; // 릴레이션의 속성

        List<Set<Integer>> candidateKeys = new ArrayList<>();

        for (int combinationNum = 1; combinationNum <= column; combinationNum++) {

            List<List<Integer>> combinationList = new ArrayList<>();
            combination(0, combinationNum, combinationList, new ArrayList<Integer>(), 0);

            if (combinationList.isEmpty()) break; // 개수에 맞는 조합이 없을 경우 종료(앞에서부터 차례대로 하는거라 한번 개수 모자르면 그 뒤에는 영원히 모자름)

            for (List<Integer> combList : combinationList) {

                // 최소성 체크
                Set<Integer> currentSet = new HashSet<>(combList);
                boolean isMinimal = true;

                for (Set<Integer> key : candidateKeys) {
                    if (currentSet.containsAll(key)) { // 이미 찾은 후보키가 현재 조합에 포함되어 있는지 확인
                        isMinimal = false;
                        break;
                    }
                }

                if (!isMinimal) continue; // 최소성을 만족하지 않으면 유일성 검사 생략

                // 유일성 체크
                Set<String> attributeZipSet = new HashSet<>();
                for (String[] tuple : relation) {

                    StringBuilder attributeZip = new StringBuilder();
                    for (int attributeIdx : combList) {
                        attributeZip.append(tuple[attributeIdx] + " ");
                    }
                    attributeZipSet.add(attributeZip.toString());
                }

                if (attributeZipSet.size() == row) {
                    candidateKeys.add(currentSet);
                }
            }

        }

        return candidateKeys.size();
    }

    private void combination(int depth, int combinationNum, List<List<Integer>> combinationList, List<Integer> currentList, int idx) {

        if (depth == combinationNum) {
            // 원하는 개수에 도달하면 종료
            combinationList.add(new ArrayList<>(currentList));
            return;
        }

        for (int attributeIdx = idx; attributeIdx < column; attributeIdx++) {
            // 모든 조합에 대해 생성
            currentList.add(attributeIdx);
            combination(depth + 1, combinationNum, combinationList, currentList, attributeIdx + 1);
            currentList.remove(currentList.size() - 1);
        }
    }
}