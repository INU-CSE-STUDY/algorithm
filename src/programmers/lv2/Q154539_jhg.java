package programmers.lv2;

import java.util.*;

// 완탐은 시간초과 나니깐 자료구조 잘 선택해야하는데 느낌적인 느낌으로는 큐가 안될 거 같아서 스택으로 도전.
public class Q154539_jhg {

    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> s = new Stack<>();

        for (int i = numbers.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && s.peek() <= numbers[i]) {
                s.pop();
            }

            answer[i] = -1;

            if (!s.empty()) {
                answer[i] = s.peek();
            }

            s.push(numbers[i]);
        }

        return answer;
    }
}
