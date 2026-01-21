package programmers.lv2;

/* 프로그래머스 42883번 큰 수 만들기 문제

[문제 풀이]
앞에서부터 차곡차곡 스택에 넣어서 비교하기
stack에 들어있는 값이랑 비교하는 거니까 비교할 때 기본 조건
- stack이 비어있지 않아야함
- k > 0 이어야 함(그래야 뺄 수 있으니까)
- 이제 제대로 된 비교 조건은 아래 예시대로!

1924 같은 경우에 1 넣고 그 다음에 9랑 비교했을 때, 1 < 9니까 1은 pop하고 9는 push하고.. 이런 식으로 해서
자기 자신보다 뒤에 있는 친구랑 비교해서 크기가 더 큰 친구가 남아있다면 앞에 있는 친구를 빼는게 큰 수 만드는데 도움이 되니까!

4177252841 같은 경우에도
4 push하고 그 다음 1이랑 비교해보면 4 > 1이므로 4를 pop하지 않고 1을 그대로 push
7 들어올 때 비교하면 1 < 7 이니까 1 pop 하고, 4 < 7 이니까 4 pop하고.. 7만 push된 상태! (k = 4 -> k = 2) (stack: 7)
그 다음 7 또 push될 거고, 2도 push되고, 5 들어왔을 때 또 비교시작! (stack: 7 7 2)
2 < 5니까 2 pop하고 (k = 1) 7 > 5니까 5 그냥 push됨 (stack: 7 7 5)
계속 2 들어왔을 때 2 push (stack: 7 7 5 2), 그 다음에 8 들어왔을 때 2 < 8이니까 2 pop되고, k = 0 되니까 그 뒤에 숫자들은 그냥 쭉쭉 stack에 push만 될 거임

그럼 stack에 7 7 5 8 4 1 들어가서
하나씩 빼내면 148577 되니까 reverse해서 반환하면 끝!

---
이렇게 하니까 마지막 테스트케이스가 틀림.. 찾아보니까 k = 0이 된다는 보장이 없다는 그런 말이 었당,,
-> 이렇게 상황이 발생한다는 이유 자체가 while 문에 들어가지 않아서 k--를 못해서 그런거임
   stack.peek() >= nowNum을 항상 만족한다는 뜻!
   => 결론적으로 그냥 만들어진 문자열을 number.length() - k 길이로 자르기만 하면 된다!
*/

import java.util.*;

class Q42883_kj {
    public String solution(String number, int k) {

        int targetLength = number.length() - k;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < number.length(); i++) {

            char nowNum = number.charAt(i);

            while (!stack.isEmpty() && k > 0 && stack.peek() < nowNum) {

                stack.pop();
                k--;
            }

            stack.push(nowNum);
        }

        StringBuilder newNumber = new StringBuilder();
        while (!stack.isEmpty()) {
            newNumber.append(stack.pop());
        }

        // 스택은 후입선출이니까~~ reverse()해야지 내가 원하는 순서대로 나옴
        return newNumber.reverse().substring(0, targetLength);
    }
}