package programmers.lv2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * stack을 쓰면 된다. 과제가 있는지 확인하고 끝낼 수 있으면 끝내면 된다.
 */
public class Q176962_jhg {
    public String[] solution(String[][] plans) {

        Stack<Plan> stack = new Stack<>();
        List<String> answer = new ArrayList<>();
        List<Plan> planList = Arrays.stream(plans)
                .map(Plan::new)
                .sorted(Comparator.comparing(Plan::getStartTime))
                .collect(Collectors.toList());

        for (int i = 0; i < planList.size() - 1; i++) {
            Plan now = planList.get(i);
            stack.push(now);
            int gap = planList.get(i + 1).getGap(now);

            while (!stack.isEmpty() && gap > 0) {
                if (stack.peek().isFinished(gap)) {
                    Plan fistPlan = stack.pop();
                    gap -= fistPlan.getProductionTime();
                    answer.add(fistPlan.getName());
                } else {
                    stack.peek().updateProductionTime(gap);
                    break;
                }
            }
        }

        answer.add(planList.get(planList.size() - 1).getName());

        while (!stack.isEmpty()) {
            answer.add(stack.pop().getName());
        }

        return answer.toArray(String[]::new);
    }

    public static class Plan {
        String name;
        int startTime;
        int productionTime;

        public Plan(String[] plan) {
            this.name = plan[0];
            String[] startTime = plan[1].split(":");
            this.startTime = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
            this.productionTime = Integer.parseInt(plan[2]);
        }

        public int getStartTime() {
            return startTime;
        }

        public boolean isFinished(int gap) {
            return productionTime <= gap;
        }

        public String getName() {
            return this.name;
        }

        public int getGap(Plan nowPlan) {
            return this.startTime - nowPlan.startTime;
        }

        public int getProductionTime() {
            return this.productionTime;
        }

        public void updateProductionTime(int gap) {
            this.productionTime -= gap;
        }
    }
}
