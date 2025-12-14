package programmers.lv2;

/* 프로그래머스 67257번 수식 최대화 문제

[문제 풀이]
6가지 우선순위에 대해서 각각 계산을 수행
리스트를 이용해서 숫자와 연산자를 따로 저장
그리고 우선순위에 따라서 연산을 수행하면서 리스트를 갱신
최종적으로 남은 숫자의 절댓값을 비교해서 최대값을 구함
*/

import java.util.*;

class Solution {
    List<Long> nums = new ArrayList<>();
    List<Character> ops = new ArrayList<>();
    char[][] priorities = new char[][]{
        { '+', '-', '*' },
        { '+', '*', '-' },
        { '-', '+', '*' },
        { '-', '*', '+' },
        { '*', '+', '-' },
        { '*', '-', '+' },
    };
    
    public long solution(String expression) {
        // 리스트 만들기
        init(expression);
        
        // 우선 순위 6가지 계산해서 max만 남기기
        long answer = maxPrize();
        
        return answer;
    }
    
    private void init(String expression){
        long num = 0;
        for (char c : expression.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else { // 연산자
                nums.add(num);
                ops.add(c);
                num = 0;
            }
        }
        nums.add(num);
    }
    
    private long calculate(long a, long b, char oper) {
        // 연산자에 따라 계산 수행
        return switch (oper) {
            case '*' -> a * b;
            case '+' -> a + b;
            case '-' -> a - b;
            default -> 0;
        };
    }
    
    // 우선순위따라 계산
    private long maxPrize() {
        long max = 0;

        // 6가지 우선순위 각각 계산
        for (char[] priority : priorities) {

            // 원본 보존을 위해 복사
            List<Long> tmpNums = new ArrayList<>(nums);
            List<Character> tmpOps = new ArrayList<>(ops);

            // 우선순위 순서대로 연산
            for (char op : priority) {
                for (int i = 0; i < tmpOps.size(); i++) {
                    if (tmpOps.get(i) == op) {
                        long a = tmpNums.get(i);
                        long b = tmpNums.get(i + 1);

                        long result = calculate(a, b, op);

                        // 계산 결과 반영
                        tmpNums.set(i, result);
                        tmpNums.remove(i + 1);
                        tmpOps.remove(i);

                        i--; // 리스트가 줄었으므로 인덱스 보정
                    }
                }
            }

            // 하나 남은 값의 절댓값으로 최대 갱신
            max = Math.max(max, Math.abs(tmpNums.get(0)));
        }

        return max;
    }
    
}