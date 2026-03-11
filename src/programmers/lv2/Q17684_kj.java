package programmers.lv2;

/* 프로그래머스 17684번 압축 문제

[문제 풀이]
문제에서 주어준 LZW 압축 과정 순서대로 해보기
1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화
- index 위치가 1부터 시작이니까,, 그냥 그거 맞추기 위해서 0번째 위치에 공백 문자 넣고 A-Z 차례대로 넣기
2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾기
- 어차피 완전탐색해야한다고 생각함, 문자열이 어떻게 생겨먹었는지 난 알턱이 없기 때문!!
- w를 길이가 1인 문자부터 시작해서, 2, 3, ... 인 문자열로 늘려가면서 가장 긴 문자열 w를 찾기
  > 반복문 반복하면서 w를 하나씩 업데이트해주기. K -> KA -> KAO 이 순서대로하기!!
    i 위치 값 업데이트는,, 사전에 존재하는 거면 이미 그거에 해당하는 색인 번호를 넣었을테니 i + w 문자열 길이 해서 업데이트해주면 됨!
3. 문자열 끝날 때까지 반복!

*/

import java.util.*;

class Q17684_kj {
    public int[] solution(String msg) {

        // 1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
        List<String> dictionary = new ArrayList<>(
                List.of(
                        "", "A", "B", "C", "D", "E", "F",
                        "G", "H", "I", "J", "K", "L", "M",
                        "N", "O", "P", "Q", "R", "S", "T",
                        "U", "V", "W", "X", "Y", "Z"
                )
        );

        int msgLength = msg.length();
        List<Integer> answer = new ArrayList<>(); // 정답 배열

        int index = 0;
        while (index < msgLength) {

            int wLength = 1;
            String w = msg.substring(index, index + wLength);

            while (index + wLength < msgLength) {

                // 가능한 w 문자열 길이를 하나씩 늘려가며 사전에 존재하는 가장 긴 문자열 w를 찾기!
                wLength++;
                String nextStr = msg.substring(index, index + wLength);

                if (!dictionary.contains(nextStr)) {
                    // 사전에 존재하지 않는 문자열이면 해당 문자열을 사전에 추가하고 종료
                    dictionary.add(nextStr);
                    break;
                }

                w = nextStr; // 해당 문자열이 사전에 존재할 경우 w 업데이트 후 다음 문자를 추가시키기 반복!
            }

            // 모두 확인하면 w 색인 번호를 추가하고 다음 인덱스 탐색
            answer.add(dictionary.indexOf(w));
            index += w.length();
        }

        return answer.stream()
                .mapToInt(Integer::intValue).toArray();
    }
}
