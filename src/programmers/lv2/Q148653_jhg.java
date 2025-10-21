package programmers.lv2;
// 수학인거 같다.
public class Q148653_jhg {

    public int solution(int storey) {

        int answer = 0;

        while (storey > 0) {
            int remainder = storey % 10;

            if (remainder == 5) {
                storey += (storey / 10) % 10 > 4 ? 10 : 0;
                answer += remainder;
            } else if (remainder < 5) {
                answer += remainder;
            } else {
                answer += (10 - remainder);
                storey += 10;
            }

            storey /= 10;
        }

        return answer;
    }
}
