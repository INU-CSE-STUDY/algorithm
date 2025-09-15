package programmers.lv3;

import java.util.*;

/**
 * 구현을 하면 된다 최대한 자바스럽게 만듦.
 * 김진을 위해 친절히 주석을 달아줌
 * ExpList는 그냥 수식을 담는 배열이라 생각하면 됨.
 * Exp는 단순히 이 수식이 가능한 진법을 포함하고 있다고 보면됨.
 * 즉 Exp 배열에서 가능한 진법들의 교집합을 구하고
 * 문자열로 치환하면 됨.
 */

public class Q340210_jhg {

    static final int BASE_MIN = 2;
    static final int BASE_MAX = 9;

    public String[] solution(String[] expressions) {
        return new ExpList(expressions).getAnswer();
    }

    public static class ExpList extends ArrayList<Exp> {

        public ExpList(String[] expressions) {
            for (String exp : expressions) {
                this.add(new Exp(exp));
            }
        }

        // 현재 리스트에서 가능한 진법을 찾음
        public Set<Integer> findBaseIntersection() {
            return this.stream()
                    .map(Exp::getBases)
                    .reduce((a, b) -> {
                        Set<Integer> intersection = new HashSet<>(a);
                        intersection.retainAll(b);
                        return intersection;
                    })
                    .orElse(Collections.emptySet());
        }

        // 문자열 치환
        public String[] getAnswer() {
            Set<Integer> intersection = findBaseIntersection();

            return this.stream()
                    .filter(Exp::isX)
                    .map(exp -> exp.getExp(intersection))
                    .toArray(String[]::new);
        }
    }


    public static class Exp {
        String a;
        String op;
        String b;
        String result;
        Set<Integer> bases = new HashSet<>();

        // X를 ? 로 바꾼 이유는 단지 문자열을 편하게 치환하기 위해.
        // X == ?
        public Exp(String exp) {
            String[] split = exp.split(" ");
            this.a = split[0];
            this.op = split[1];
            this.b = split[2];
            this.result = "X".equals(split[4]) ? "?" : split[4];
            this.bases = findBase();
        }

        // 모르는 수식인지 확인
        public boolean isX() {
            return "?".equals(this.result);
        }

        // 가능한 진법을 반환함.
        public Set<Integer> findBase() {
            Set<Integer> result = new HashSet<>();
            for (int base = BASE_MIN; base <= BASE_MAX; base++) {
                if (isX()) {
                    if (isSupport(base)) {
                        result.add(base);
                    }
                }
                else {
                    if (isSupport(base) && isValid(base)){
                        result.add(base);
                    }
                }
            }

            return result;
        }

        // 10진수의 결과를 반환
        public int calculateDigit(int base) {
            int digitA = Integer.parseInt(this.a, base);
            int digitB = Integer.parseInt(this.b, base);

            return "+".equals(this.op) ? digitA + digitB : digitA - digitB;
        }

        // 해당하는 진수의 계산 결과를 반환
        public String calculateNum(int base) {
            return Integer.toString(calculateDigit(base), base);
        }

        // 해당 진법이 유효한지 검사
        public boolean isSupport(int base) {
            try {
                Integer.parseInt(this.a, base);
                Integer.parseInt(this.b, base);
                if (!isX()) {
                    Integer.parseInt(this.result, base);
                }
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        // 해당 진법에 수식이 유요한지 검증
        public boolean isValid(int base) {
            int digitResult = Integer.parseInt(this.result, base);
            return digitResult == calculateDigit(base);
        }

        public Set<Integer> getBases() {
            return this.bases;
        }

        // 문자열 반환
        public String getExp(Set<Integer> bases) {
            int firstBase = bases.iterator().next();
            String firstNum = calculateNum(firstBase);

            for (Integer base : bases) {
                if (!Objects.equals(firstNum, calculateNum(base))) {
                    return toString();
                }
            }

            this.result = firstNum;

            return toString();
        }

        @Override
        public String toString() {
            return this.a + " " + this.op + " " + this.b + " = " + this.result;
        }
    }
}
