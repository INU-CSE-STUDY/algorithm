package programmers.lv2;

/* 프로그래머스 131704번 택배상자 문제

[문제 풀이]
컨테이너 벨트 = 선입선출 = 큐
보조 컨테이너 벨트 = 후입선출 = 스택

문제 이해를 못해서 질문하기를 보고 옴,, 나같은 사람이 쫌 있어서 다행;

컨테이너 벨트에는 1, 2, 3, 4, 5 순서로 들어있고
order에 나와있는 순서대로 해야 하는것
4, 3, 1, 2, 5 순서니까
1 -> 보조 컨테이너
2 -> 보조 컨테이너
3 -> 보조 컨테이너
4 -> 순서상 얘가 나와야하니까 트럭 / 그 다음에 3 바로 pop 해서 트럭
5 -> 보조 컨테이너 가거나 메인에 해야 하는데.. 순서상 1 나와야하는데 5도 안되고 스택 pop 해도 안되니까 답은 2

문제에서 주어진 것처럼 1번 박스부터 차례대로 넣어보고, 끝까지 다 넣으면 종료되게!

*/

import java.util.*;

class Q131704_kj {
    public int solution(int[] order) {
        int answer = 0; // 트럭에 실을 수 있는 상자 개수

        Stack<Integer> assistance = new Stack<>();
        int boxNum = 1; // 컨테이너 벨트에 일렬로 놓여있는 상자 번호
        int index = 0; // order 배열 관리용 index
        while (index < order.length && boxNum <= order.length) {
            assistance.push(boxNum++);

            while (!assistance.isEmpty()) {
                int storeBox = assistance.pop();

                if (order[index] == storeBox) {
                    // 저장된 박스가 우리가 찾는 순서의 박스인 경우
                    answer++;
                    index++;
                } else {
                    // 저장된 박스가 우리가 찾는 순서의 박스가 아닌 경우 다시 박스 넣으러가기
                    assistance.push(storeBox);
                    break;
                }
            }
        }

        return answer;
    }
}
