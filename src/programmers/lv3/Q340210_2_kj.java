package programmers.lv3;

import java.util.*;

class Q340210_2_kj {

    List<Integer> possibleBaseList = new ArrayList<>(); // 가능한 진법을 저장할 ArrayList
    List<String> answer = new ArrayList<>(); // 정답 배열

    public String[] solution(String[] expressions) {
        // 수식에 존재하는 최댓값을 구해 가능한 진법 리스트를 생성
        int max = getMax(expressions);
        addPossibleBase(max);
        checkExpression(expressions);
        getAnswer(expressions);
        return answer.toArray(new String[0]); // 정답 배열을 array로 반환
    }

    private void getAnswer(String[] expressions) {
        for (String expression : expressions) {
            Expression exp = new Expression(expression);

            if (exp.isContainsX()) {
                if (possibleBaseList.size() == 1) {
                    int base = possibleBaseList.get(0);

                    String ans = expression.replace("X", exp.calculateExpression(base));
                    answer.add(ans);
                } else {
                    String prevResult = "";
                    boolean isMystery = false;
                    for (int base : possibleBaseList) {
                        String result = exp.calculateExpression(base);

                        if (prevResult.equals("")) {
                            prevResult = result;
                        } else if (!prevResult.equals(result)) {
                            isMystery = true;
                            break;
                        }
                    }

                    String ans;
                    if (isMystery) {
                        ans = expression.replace("X", "?");
                    } else {
                        ans = expression.replace("X", prevResult);
                    }

                    answer.add(ans);
                }
            }
        }
    }

    private void checkExpression(String[] expressions) {
        for (String expression : expressions) {
            Expression exp = new Expression(expression);

            if (!exp.isContainsX()) {
                boolean isPossibleExp;

                Iterator<Integer> iter = possibleBaseList.iterator();
                while (iter.hasNext()) {
                    int base = iter.next();

                    isPossibleExp = exp.checkExpression(base);

                    // 불가능한 수식일 경우 제거
                    if (!isPossibleExp) {
                        iter.remove();
                    }
                }
            }
        }
    }

    private int getMax(String[] expressions) {
        int max = -1;
        for (String expression : expressions) {
            Expression exp = new Expression(expression);
            max = Math.max(max, exp.getMaxDigit());
        }

        return max;
    }

    private void addPossibleBase(int max) {
        // 9진법까지 가능
        for (int i = max + 1; i <= 9; i++) {
            possibleBaseList.add(i);
        }
    }
}

class Expression {
    String A;
    String op;
    String B;
    String result;

    public Expression(String expression) {
        String[] parts = expression.split(" ");
        this.A = parts[0];
        this.op = parts[1];
        this.B = parts[2];
        this.result = parts[4];
    }

    public boolean isContainsX() {
        return result.equals("X");
    }

    public boolean checkExpression(int base) {
        int numA = Integer.parseInt(A, base);
        int numB = Integer.parseInt(B, base);
        int numC = Integer.parseInt(result, base);

        if (op.equals("+")) {
            return numA + numB == numC;
        } else {
            return numA - numB == numC;
        }
    }

    public String calculateExpression(int base) {
        int numA = Integer.parseInt(A, base);
        int numB = Integer.parseInt(B, base);
        int numC = 0;

        if (op.equals("+")) {
            numC = numA + numB;
        } else {
            numC = numA - numB;
        }

        return Integer.toString(numC, base);
    }

    public int getMaxDigit() {
        int max = -1;

        max = Math.max(max, getMaxNum(A));
        max = Math.max(max, getMaxNum(B));

        if (!isContainsX()) {
            max = Math.max(max, getMaxNum(result));
        }

        return max;
    }

    private int getMaxNum(String num) {
        int max = -1;
        int number = Integer.parseInt(num);

        while (number > 0) {
            int n = number % 10;
            max = Math.max(max, n);
            number = number / 10;
        }

        return max;
    }

    public String toString() {
        return (A + " " + op + " " + B + " = " + result);
    }
}
