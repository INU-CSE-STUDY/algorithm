package programmers.lv3;

/* 프로그래머스 340210번 수식 복원하기 문제

[문제 풀이]
이 문제는 2 ~ 9진법 중 하나로 써져있는 수식을 보고, X에 들어갈 값을 맞추는 문제

내가 생각나는 풀이법
1. 포함되어있는 숫자 보고 가능한 진법 구하기
- 포함되어있는 모든 숫자의 최댓값(입출력 예 #1의 경우 17에 포함되어있는 7)을 보면 가능한 진법이 뭔지 알 수 있음
2. X가 들어가는 수식 제외하고 하나씩 확인해보기
- 문자열을 split으로 나눠서 계산할 거니까 0, 2, 4에 숫자값이 들어가고, 1에 연산자가 들어감. / 4가 X가 아닌 경우만 검사하면 됨
- Integer.parseInt() 사용해서 진법 계산하기
  51 - 5 = 44처럼 8진수는 가능하지만 9진수는 안되는 경우도 존재하니! 천천히 가능한 경우를 모두 돌려보자 ㅎㅎ
3. 입출력 예 #1과 같이 단 하나의 진법으로만 가능하다면 그 진법으로 계산 후 출력
   입출력 예 #2와 같이 다양한 진법으로 추론되는 경우 여러 개의 진법으로 계산 후 값이 동일하다면 출력, 아니라면 X를 ?로 변경
   - 2 + 2와 5 - 5는 8진법, 9진법에 관계없이 결과가 동일하므로, X 값 그대로 집어넣기
   - 7 + 4는 8진법과 9진법의 계산 결과가 다르므로 X를 ?로 변경
*/

import java.util.*;

class Q340210_kj {
    // 가능한 진법을 담을 ArrayList, 다른 수식 계산 중 불가능한 것이 존재할 경우 삭제
    static List<Integer> possibleBaseList = new ArrayList<>();

    public String[] solution(String[] expressions) {
        int max = getMaxNumber(expressions);
        addPossibleBase(max);
        checkExpressions(expressions);
        return getAnswer(expressions).toArray(new String[0]);
    }

    private List<String> getAnswer(String[] expressions) {
        List<String> answer = new ArrayList<>();

        if (possibleBaseList.size() == 1) {
            // 가능한 진법이 1개인 경우 바로 계산 가능
            for (String expression : expressions) {
                if (expression.contains("X")) {
                    String[] exp = expression.split(" ");
                    String A = exp[0];
                    String B = exp[2];
                    String op = exp[1];

                    int base = possibleBaseList.get(0);

                    int numA = Integer.parseInt(A, base);
                    int numB = Integer.parseInt(B, base);
                    int numC = 0;

                    if (op.equals("+")) {
                        numC = numA + numB;
                    } else {
                        numC = numA - numB;
                    }

                    String ans = expression.replace("X", Integer.toString(numC, base));
                    answer.add(ans);
                }
            }
        } else {
            // 가능한 진법이 여러개일 경우 확인이 필요
            for (String expression : expressions) {
                if (expression.contains("X")) {
                    String[] exp = expression.split(" ");
                    String A = exp[0];
                    String B = exp[2];
                    String op = exp[1];

                    boolean isMystery = false;
                    String possibleAns = "";
                    for (int base : possibleBaseList) {
                        int numA = Integer.parseInt(A, base);
                        int numB = Integer.parseInt(B, base);
                        int numC = 0;
                        String result = "";

                        if (op.equals("+")) {
                            numC = numA + numB;
                        } else {
                            numC = numA - numB;
                        }

                        result = Integer.toString(numC, base);

                        // 진법 계산 결과가 일치하는 경우는 그대로 넘어가기
                        if (possibleAns.equals("")) {
                            possibleAns = result;
                        } else {
                            if (!possibleAns.equals(result)) {
                                // 진법 계산 결과가 일치하지 않는 경우, 판단할 수 없음
                                isMystery = true;
                                break;
                            }
                        }
                    }

                    String ans = "";
                    if (isMystery) {
                        // 결과값이 불확실하면 X를 ?로 변경
                        ans = expression.replace("X", "?");
                    } else {
                        // 결과값이 확실하다면 해당 값 저장
                        ans = expression.replace("X", possibleAns);
                    }
                    answer.add(ans);
                }
            }
        }

        return answer;
    }

    // 수식을 확인해 가능한 진법만 남김
    private void checkExpressions(String[] expressions) {
        for (String expression : expressions) {
            if (!expression.contains("X")) {
                String[] exp = expression.split(" ");

                String A = exp[0];
                String B = exp[2];
                String C = exp[4];
                String op = exp[1];

                // 안되는 애들은 바로바로 삭제할 거니까 for문 안쓰고 iterator로..
                Iterator<Integer> iter = possibleBaseList.iterator();
                while (iter.hasNext()) {
                    int base = iter.next();
                    int numA = Integer.parseInt(A, base);
                    int numB = Integer.parseInt(B, base);
                    int numC = Integer.parseInt(C, base);

                    boolean isPossible = false;
                    if (op.equals("+")) {
                        isPossible = (numA + numB == numC);
                    } else {
                        isPossible = (numA - numB == numC);
                    }

                    if (!isPossible) {
                        iter.remove();
                    }
                }
            }
        }
    }

    // 사용가능한 진법 계산을 위해, 수식에 존재하는 가장 큰 숫자를 구함 -> 그 숫자 이하의 진법은 다 사용 불가능이니까
    private int getMaxNumber(String[] expressions) {
        int max = Integer.MIN_VALUE;

        for (String expression : expressions) {
            String[] exp = expression.split(" ");

            for (int i = 0; i < 5; i++) {
                if (i % 2 == 0 && !exp[i].equals("X")) {
                    // X가 아닌 숫자값 중에 가장 큰 자릿수 찾기
                    max = Math.max(max, getMaxDigit(exp[i]));
                }
            }
        }

        return max;
    }

    private int getMaxDigit(String num) {
        int max = -1;
        int number = Integer.parseInt(num);

        while (number > 0) {
            int n = number % 10;
            max = Math.max(max, n);
            number /= 10;
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