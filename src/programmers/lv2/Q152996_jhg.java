package programmers.lv2;


// 모든 경우의 수를 구하자.

public class Q152996_jhg {

    public long solution(int[] weights) {
        long answer = 0;

        long[] count = new long[100001];

        for (int weight : weights) {
            count[weight]++;
        }

        for (int i = 1; i < 100001; i++) {
            answer += count[i]*(count[i] - 1) / 2;
            answer += count[i*2%3 == 0 ? i*2/3 : 0] * count[i];
            answer += count[i*2%4 == 0 ? i*2/4 : 0] * count[i];
            answer += count[i*3%4 == 0 ? i*3/4 : 0] * count[i];
        }

        return answer;
    }
}
