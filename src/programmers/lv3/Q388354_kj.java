package programmers.lv3;

/* 프로그래머스 388354번 홀짝트리 문제

[문제 풀이]
홀(짝) 노드 -> 노드의 번호와 자식 노드의 개수 모두 홀(짝)
역홀(짝) 노드 -> 노드의 번호는 홀(짝), 자식 노드의 개수는 짝(홀)
홀짝 트리 -> 홀수 노드와 짝수 노드로만 이루어진 트리
역홀짝 트리 -> 역홀수 노드와 역짝수 노드로만 이루어진 트리

각 트리에 대해 루트 노드를 설정했을 때, 홀짝 트리와 역홀짝 트리의 개수 구하기

1. 간선 정보를 이용해서 tree 구하기
- 입출력 예 #2처럼 트리가 여러 개가 나올 수 있기 때문!
- dfs 탐색으로 개별 트리 구하기
2. 각 tree별로 루트 노드를 설정하며 홀짝 트리/역홀짝 트리 개수 구하기
- 각 tree 별로 존재하는 모든 노드를 다 루트 노드로 설정해서 만들어지는 트리가 조건에 맞는지 구하기
-> 각 트리별로 루트 노드가 정해지면 나머지 노드들의 자식 수가 결정남.
   이 점을 이용해서, 각 노드의 번호와 자식의 수를 보고 홀짝 트리인지 역홀짝 트리인지 아무 것도 아닌지 확인 가능
   - 루트일 경우: node % 2와 graph.get(node).size() % 2 비교
   - 루트 아닐 경우: node % 2와 graph.get(node).size() -1 % 2 비교
     이 경우가 == 이면 홀짝트리, != 이면 역홀짝 트리임을 이용해서.. 전체 순회 돌리기!
=> 이렇게 하니까 몇몇 케이스에서 시간 초과가 나서 70/100 이라는 스코어가 나옴.. 살짝 아쉬움 ㅠㅠ

질문하기 확인해봤는데, 상당히 인상적인 판별 방법이 있었다
[이 아이디어](https://school.programmers.co.kr/questions/85366)인데..!
루트 노드를 제외한 나머지 노드들은 자식 수가 전부 다 -1이 되니까, 홀짝 <=> 역홀짝이 바뀌게 됨
- 원래 홀짝이 맞던 노드들은 그 개수에서 -1 되니까 역홀짝이 되고, 역홀짝이 맞던 노드는 홀짝이 됨
이 점을 이용
=> 전체 트리에서 딱 한개만 홀짝인 경우
   걔를 루트 노드로 하는 경우에 홀짝 노드가 됨 (원래 홀짝 안맞던 애들이 홀짝이 맞아지게 되니까)
=> 전체 트리에서 딱 한개만 역홀짝인 경우
   얘도 똑같이 걔를 루트 노드로 하는 경우에 역홀짝 노드가 됨 (원래 역홀짝이 안맞던 애들이 역홀짝이 되니까)
이 두 경우 제외하고는 어떻게 해도 홀짝/역홀짝 노드가 안되고, 각 트리별로 딱 한번씩만 순회하면 되니까 당연히 시간초과 안남

중요한 점은 하나의 트리가 홀짝 트리이면서 역홀짝 트리가 될 수도 있으니!! else if 이런거로 두지 않기..
2 - 3 같은 트리 에시로 하면 2를 루트로 하면 역홀짝 트리, 3을 루트로 하면 홀짝 트리이니까! 꼭꼭 if 두개로 조건 비교하기
이걸 else if로 둬가지고 왜 틀렸나 고민했음;
*/

import java.util.*;

class Q388354_kj {
    static List<List<Integer>> graph;
    static List<List<Integer>> trees;

    public int[] solution(int[] nodes, int[][] edges) {
        int maxNode = 0;
        for (int node : nodes) {
            maxNode = Math.max(maxNode, node);
        }

        graph = new ArrayList<>();
        getGraph(edges, maxNode);

        trees = new ArrayList<>();
        boolean[] visited = new boolean[maxNode + 1];
        for (int node : nodes) {
            if (!visited[node]) {
                List<Integer> tree = new ArrayList<>();
                getTreeDFS(node, visited, tree);
                trees.add(tree);
            }
        }

        int treeCnt = 0;
        int rTreeCnt = 0;
        for (List<Integer> tree : trees) {

            int oddEvenCnt = 0;

            for (int node : tree) {
                int child = graph.get(node).size();

                // 홀짝 조건에 맞는 애들의 수를 셈
                if (node % 2 == child % 2) oddEvenCnt++;
            }

            if (oddEvenCnt == 1) {
                // 주석에서 설명했던 것처럼, 홀짝을 만족하는 애가 단 한개라면 걔를 루트 노드로 했을 때 홀짝 트리 완성
                treeCnt++;
            }
            if (oddEvenCnt == tree.size() - 1) {
                // 역홀짝을 만족하는 애가 단 한개일 때, 걔를 루트 노드로 하면 역홀짝 트리가 됨
                rTreeCnt++;
            }
        }

        return new int[]{ treeCnt, rTreeCnt };
    }

    private static void getGraph(int[][] edges, int max) {
        for (int i = 0; i <= max; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
    }

    private static void getTreeDFS(int node, boolean[] visited, List<Integer> tree) {
        visited[node] = true;
        tree.add(node);

        for (int n : graph.get(node)) {
            if (!visited[n]) {
                getTreeDFS(n, visited, tree);
            }
        }
    }
}