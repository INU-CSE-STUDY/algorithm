package programmers.lv3;

import java.util.*;

/**
 * 음 트리 탐색하고? 조건에 부합하는 개수만 새면 될거 같다.
 * 아마 느낌적인 느낌인데 트리 하나당 역홀짝 OR 홀짝 OR 둘다 아님만 존재할 거 같다.
 * 이 느낌으로 가면 될 거 같다.
 * 느낌인 이유가 어짜피 1 ~ 10번 노드 중 1번을 선택하든, 2번을 선택하든 나머지 노드들은 자식의 수가 같아서... 느낌이 그럼..
 */
public class Q388354_jhg {
    static Map<Integer, List<Integer>> graph = new HashMap<>();
    static Map<Integer, Boolean> visited = new HashMap<>();
    static List<List<Integer>> trees = new ArrayList<>();

    public int[] solution(int[] nodes, int[][] edges) {

        setGraph(nodes, edges);
        return resolved();
    }

    private void setGraph(int[] nodes, int[][] edges) {
        for (int node : nodes) {
            graph.put(node, new ArrayList<>());
            visited.put(node, false);
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        for (int node : nodes) {
            if (!visited.get(node)) {
                List<Integer> tree = new ArrayList<>();
                dfs(node, tree);
                trees.add(tree);
            }
        }
    }

    public void dfs(int now, List<Integer> tree) {
        visited.put(now, true);
        tree.add(now);

        for (int nxt : graph.get(now)) {
            if (!visited.get(nxt)) {
                visited.put(nxt, true);
                dfs(nxt, tree);
            }
        }
    }


    private int[] resolved() {
        int nTreeCnt = 0;
        int rTreeCnt = 0;
        for (List<Integer> tree : trees) {

            int count = 0;

            for (int node : tree) {
                int childCount = graph.get(node).size();

                if (node % 2 == childCount % 2) {
                    count++;
                }
            }

            if (count == 1) {
                nTreeCnt++;
            }

            if (count == tree.size() - 1) {
                rTreeCnt++;
            }
        }

        return new int[]{nTreeCnt, rTreeCnt};
    }
}
