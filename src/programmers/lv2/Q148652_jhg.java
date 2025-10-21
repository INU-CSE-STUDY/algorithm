package programmers.lv2;

// 이미 김진 풀이 봐버려서 어쩔수가 없다...
public class Q148652_jhg {
    public int solution(int n, long l, long r) {
        int answer = 0;

        for (long i = l - 1; i < r; i++) {
            String base5Num = Long.toString(i, 5);

            if (base5Num.contains("2")) continue;
            answer++;
        }

        return answer;
    }
}
