package programmers.lv2;

import java.util.*;

/**
 * 그냥 시뮬레이션이다
 * 최악의 경우가 100*100*100이므로 1백만 번 언저리라.. 걍 다 찾자!
 */
public class Q340211_jhg {

    static Map<Integer, Map<Pair, Integer>> infos = new HashMap<>();

    public int solution(int[][] points, int[][] routes) {

        setInfo(points, routes);
        return resolved();
    }

    private int resolved() {
        int answer = 0;

        for (Map<Pair, Integer> values : infos.values()) {
            for (int count : values.values()) {
                if (count > 1) {
                    answer++;
                }
            }
        }
        return answer;
    }

    private void setInfo(int[][] points, int[][] routes) {
        for (int[] route : routes) {
            checkRoute(route, points);
        }
    }

    private void checkRoute(int[] route, int[][] points) {
        int time = 0;
        Pair now = new Pair(points[route[0]-1]);

        put(now, time);

        for (int i = 1; i < route.length; i++) {
            Pair nxt = new Pair(points[route[i]-1]);

            while (!now.equals(nxt)) {
                time++;

                now = now.move(nxt);

                put(now, time);
            }
        }
    }

    public void put(Pair now, int time) {
        infos.putIfAbsent(time, new HashMap<>());
        Map<Pair, Integer> map = infos.get(time);
        map.put(now, map.getOrDefault(now, 0) + 1);
    }

    public static class Pair {
        int x;
        int y;

        public Pair(int[] pos) {
            this.x = pos[0];
            this.y = pos[1];
        }

        public Pair move(Pair p) {
            if (x < p.x) {
                return new Pair(new int[]{x+1, y});
            }
            else if (x > p.x) {
                return new Pair(new int[]{x-1, y});
            }
            else if (y < p.y) {
                return new Pair(new int[]{x, y+1});
            }
            else {
                return new Pair(new int[]{x, y-1});
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
