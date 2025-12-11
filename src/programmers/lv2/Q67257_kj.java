package programmers.lv2;

/* 프로그래머스 67257번 수식 최대화 문제

[문제 풀이]
완전탐색으로 수식의 결과값의 절댓값이 최대인 친구를 구하면 되나..?

1. 3가지 연산자로만 이루어진 중위표기법이니까, 일단 우선순위로 될 수 있는 6가지 모두 표현
-> 이 우선순위를 전부 다 돌면서 최댓값을 찾으면 됨
2. 우선순위 순서대로 앞에서부터 연산하기
-> indexOf로 해당 연산자가 존재하는지 확인하고 있으면 계산하고 없으면 continue로 넘어가기
-> 존재한다면 앞에서부터 순서대로 계산하기.
   정규표현식으로 매칭 시도하면 가장 앞에 부분만 매칭되니까!! 여기서 가지고 와가지고 연산하고 다시 String 문자열에 합치기
   => 이 과정 반복하면 String 형태로 된 숫자만 남으니까.. 이거 long으로 변환하고 절댓값 씌우면 되지 않을까 하는 생각 ㅎㅎ..
=> ㅠㅠ 이렇게 푸니까 우선순위에 따라서 (-100 * -220) 같은 결과가 남아서.. 중간중간 예외처리 등의 문제가 너무 많이 발생해서 폐기해야 할 듯...

List에 숫자랑 연산자를 따로 담아서 순서대로 계산해서 리스트 업데이트 해주는 방식으로 변경
이렇게 하면 연산 결과가 음수로 나와도 long 타입에 저장되니까 다음 연산자 이용하는 계산에서도 문제 없음!

*/

import java.util.*;

class Q67257_kj {

    // 가능한 우선순위 6가지
    static char[][] priorities = new char[][]{
            { '+', '-', '*' },
            { '+', '*', '-' },
            { '-', '+', '*' },
            { '-', '*', '+' },
            { '*', '+', '-' },
            { '*', '-', '+' },
    };

    public long solution(String expression) {

        List<Long> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        initList(numbers, operators, expression);

        return getMaxValue(numbers, operators);
    }

    private void initList(List<Long> numbers, List<Character> operators, String expression) {

        // 피연산자(operand) 따로 저장
        String[] numStrs = expression.split("[^0-9]");
        for (String num : numStrs) {
            numbers.add(Long.parseLong(num));
        }

        // 연산자(opearator) 따로 저장
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                operators.add(c);
            }
        }
    }

    private long getMaxValue(List<Long> numbers, List<Character> operators) {

        long maxValue = Long.MIN_VALUE;

        for (char[] priority : priorities) {
            // 모든 우선순위 전체 탐색

            List<Long> copyNumbers = new ArrayList<>(numbers);
            List<Character> copyOperators = new ArrayList<>(operators);

            for (char op : priority) {
                while (copyOperators.contains(op)) {

                    int idx = copyOperators.indexOf(op);
                    long result = calculate(op, copyNumbers.get(idx), copyNumbers.get(idx + 1));

                    copyNumbers.set(idx, result);
                    copyNumbers.remove(idx + 1);
                    copyOperators.remove(idx);
                }
            }

            maxValue = Math.max(maxValue, Math.abs(copyNumbers.get(0)));
        }

        return maxValue;
    }

    private long calculate(char op, long num1, long num2) {

        if (op == '+') return num1 + num2; // + 연산
        if (op == '-') return num1 - num2; // - 연산
        return num1 * num2;                // * 연산
    }
}
