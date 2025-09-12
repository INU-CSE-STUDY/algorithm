package programmers.lv2;

import java.util.Arrays;
import java.util.Comparator;

// 그리디이다 매번 최선만 생각하면 된다..
public class Q181188_jhg {

    static int pos = 0;

    public int solution(int[][] targets) {
        return (int) Arrays.stream(targets)
                .sorted(Comparator.comparingInt(target -> target[1]))
                .filter(target -> {
                    if (target[0] >= pos) {
                        pos = target[1];
                        return true;
                    } else {
                        return false;
                    }
                })
                .count();
    }
}
