package programmers.lv2;

// 설명은 생략한다.
public class Q250135_jhg {


    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        return count(getSecond(h2, m2, s2)) - count(getSecond(h1, m1, s1)) + ((s1 == 0 && m1 == 0) ? 1 : 0);
    }

    private int count(int t) {
        return t * 59 / 3600 + t * 719 / 43200 - t / 43200;
    }

    private int getSecond(int h, int m, int s) {
        return (h * 60 + m) * 60 + s;
    }
}
