package programmers.lv2;

public class Q132265_jhg {

    public int solution(int[] topping) {
        int answer = 0;
        int[] one = new int[10001];
        int[] two = new int[10001];

        int countOne = 0;
        int countTwo = 0;

        for (int top: topping) {
            if (two[top] == 0) {
                countTwo++;
            }

            two[top]++;
        }

        for (int top : topping) {

            if (one[top] == 0) {
                countOne++;
            }

            one[top]++;
            two[top]--;

            if (two[top] == 0) {
                countTwo--;
            }

            if (countOne == countTwo) {
                answer++;
            }
        }

        return answer;
    }
}
