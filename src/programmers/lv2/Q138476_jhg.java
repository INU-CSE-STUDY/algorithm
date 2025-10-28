package programmers.lv2;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

// 음 그냥! 개수 정렬하고? 그거 바탕으로 개수 새면서 정답 찾으면 될 거 같다.
public class Q138476_jhg {

    public int solution(int k, int[] tangerine) {
        AtomicInteger limit = new AtomicInteger(0);
        return (int) Arrays.stream(tangerine)
                .boxed()
                .collect(Collectors.toMap(
                        size -> size,
                        Orange::new,
                        (exists, replacement) -> {
                            exists.count();
                            return exists;
                        }
                ))
                .values().stream()
                .sorted()
                .takeWhile(orange -> {
                    int count = orange.getCount();
                    return limit.addAndGet(count) < k;
                }).count() + 1;
    }

    static class Orange implements Comparable<Orange>  {
        public int size;
        public int count;

        public Orange(int size) {
            this.size = size;
            this.count = 1;
        }

        public void count() {
            this.count++;
        }

        public int getCount() {
            return this.count;
        }

        @Override
        public int compareTo(Orange o) {
            return Integer.compare(o.count, this.count);
        }
    }
}
