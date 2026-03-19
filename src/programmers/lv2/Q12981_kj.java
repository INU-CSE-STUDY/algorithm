package programmers.lv2;

/* 프로그래머스 12981번 영어 끝말잇기 문제

[문제 풀이]

탈락 기준
- 이미 나온 단어를 말했을 때
- 앞사람이 말한 단어의 마지막 문자로 시작하지 않았을 때

가장 먼저 탈락하는 사람의 번호와 그 사람이 자신의 몇 번째 차례에서 탈락하는지 구하기
- 차례 상관안하고 게임 전체 턴으로 보면 그 턴을 사람수로 나눴을 때 나뉘어떨어지는 애가 마지막 번호 사람
  플레이어 번호 = 현재 턴(사람 상관없이 전체 턴 = words의 index + 1 값) % 사람 수, 나누어떨어지는 경우가 마지막 번호(= 사람 수랑 같은 번호)
  플레이어 각자의 턴 = 현재턴 / 사람수 + 1, 나누어 떨어지는 경우만 현재턴 / 사람수

=> 풀이 다하고 다른 사람들 풀이 보니까,, 걍 이렇게 턴수 1턴 시작 안하고 index로 했으면 삼항연산 안써도 되는 문제란걸 깨달음... 그래서 index로 고쳤어요..
*/

import java.util.*;

class Q12981_kj {
    public int[] solution(int n, String[] words) {

        Set<String> wordSet = new HashSet<>();
        int losePlayerNumber = 0; // 가장 먼저 탈락하는 사람의 번호
        int losePlayerTurn = 0; // 몇 번재 차례에서 탈락한지
        String lastAlphabet = "";
        for (int index = 0; index < words.length; index++) {

            // 현재 말한 단어가 이전에 등장했거나, 앞사람이 말한 단어의 마지막 문자로 시작하는 단어가 아니면 탈락
            String nowWord = words[index];
            if (wordSet.contains(nowWord) || !nowWord.startsWith(lastAlphabet)) {

                losePlayerNumber = index % n + 1; // 탈락한 사람의 번호
                losePlayerTurn = index / n + 1; // 탈락한 사람이 자신의 몇 번째 차례에서 탈락했는지

                break;
            }

            // 탈락자가 아니라면 해당 단어를 추가하고, 마지막 알파벳이 뭔지 저장해두기
            wordSet.add(nowWord);
            lastAlphabet = nowWord.substring(nowWord.length() - 1);
        }

        return new int[]{ losePlayerNumber, losePlayerTurn };
    }
}