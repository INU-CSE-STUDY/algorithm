package programmers.lv2;

/* 프로그래머스 258711번 도넛과 막대 그래프

[문제 풀이]
각 그래프를 구분하는 방법은 다음과 같다.
도넛 - 정점 = 간선 / 막대 - 정점 = 간선 + 1 / 8자 - 정점 = 간선 - 1
-> 한 정점에서 탐색을 시작해 정점의 개수와 간선의 개수를 센 다음, 해당하는 그래프에 +1 하기
(문제에서 도넛, 막대, 8자 모양 그래프가 여러 개 존재하고, 이 그래프들과 무관한 정점을 하나 생성한다고 했으니 그래프 모양은 3개로 무조건 고정이어서 가능함)

생성한 정점은 어떻게 구할 수 있을까?
- 처음에는 한 정점에서 탐색을 시작해 정점과 간선의 수를 세면 생성한 정점도 파악할 수 있다고 생각함
  -> 문제에서 주어진 그래프 종류는 딱 3개이므로 시정점과 간선의 관계가 3개의 그래프 어디에도 속하지 않는다면, 해당 탐색의 시작점이 생성한 정점일테니까
  그러나 이렇게 구하면.. 방문 배열 관리가 상당히 어려울 것처럼 느껴졌다.
  1. 그래프 모양이 맞으면 그 방문배열을 유지해야 함. -> 다신 이 그래프에 한 정점을 방문하지 않아야 개수를 잘못 셀 문제가 없음
  2. 그래프 모양이 안맞으면(생성한 정점이면) 그 방문 배열을 초기화해야 함. -> 여기서 방문한 건 어차피 신경쓸 부분이 아니니까 초기화해야지 다음 그래프 셀 때 지장이 없음
     -> 지금 생각해보면 clone해서 저장하고 뭐 이런 식으로 하면 될 거 같긴한데.. 문제 푸는 당시에는 생각도 안났고 어렵게 느껴졌당..~
  => 어쨌든 질문하기를 확인하게 됐음. 질문하기를 확인하니까, 새로 생성한 정점은 들어오는 간선이 없다는 아주 당연하지만 내가 생각하지 않는 내용을 발견할 수 있었음
     문제를 꼼꼼히 읽지 않았다는 증거이기도 하다. 문제만 꼼꼼히 읽었으면 바로 이렇게 풀었을 듯..?
     여러 그래프가 있는데 무관한 정점을 생성해 각 그래프의 한 정점으로 향하는 간선을 연결한 거라서.. 당연히 나가는 간선만 있고 들어오는 간선은 없음
     따라서, 각 정점에 대하여 나가는 간선과 들어오는 간선의 수를 세서 나가는 간선 >= 2(문제 조건)이면서 들어오는 간선이 0인 친구가 생성된 간선임을 알게 됐다.

생성된 정점을 바로 알게되면 문제 풀이가 조금 더 간단해진다고 생각
왜냐하면, 생성된 정점이 가리키는 정점들에게서만 탐색을 하면 됨
1. 도넛과 8자 모양 그래프는 어디 정점에서 시작해도 항상 똑같은 정점의 수와 간선의 수를 얻어올 수 있음
2. 막대의 경우 아예 방문하지 않는 정점은 있을지라도, 특정 정저에서 시작하면 항상 정점의 수와 간선의 수 관계가 일정함(정점의 수 = 간선의 수 + 1)

탐색 자체는 BFS 탐색을 사용해 생성된 정점이 가리키는 그래프 전체(도넛, 8자) 또는 일부(막대)를 탐색할 수 있게 했다.
해당 정점부터 시작해, 그 정점에서 갈 수 있는 간선의 개수는 모두 더하고 방문하지 않았던 정점만 queue에 넣어 반복하면 문제 없이 동작한다.
- 한번 방문한 정점은 다시 방문하지 않게 코드를 짤 거기 때문에(visited 배열 사용) queue에서 빼서 나온 정점에서 나가는 간선의 개수는 한번에 더하고, 정점의 개수는 하나씩 더하게 했음(이미 방문한 노드는 큐에 저장하지 않으니까, 간선의 수마냥 더하면 방문했는지 안했는지 확인하면서 더해야 해서 귀찮아가지고;)
이렇게 간선의 수와 정점의 수를 계산했으면 가장 위에 적어둔 수식에 맞춰서 그래프 개수를 세주면 된다.
*/

import java.util.*;

class Q258711_kj {
    static int maxNode;
    static List<List<Integer>> graph;
    static int[][] edgeNum;

    static boolean[] visited;

    public int[] solution(int[][] edges) {

        graph = new ArrayList<>();
        initGraph(edges);
        int nodeNum = getNodeNum();

        visited = new boolean[maxNode + 1];
        int[] graphNum = getGraphNum(nodeNum);
        return new int[]{ nodeNum, graphNum[0], graphNum[1], graphNum[2] };
    }

    private void initGraph(int[][] edges) {
        maxNode = 0;
        for (int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }

        edgeNum = new int[maxNode + 1][2];

        for (int i = 0; i <= maxNode; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            edgeNum[edge[0]][0]++; // 해당 정점에서 나가는 간선의 수
            edgeNum[edge[1]][1]++; // 해당 정점으로 들어오는 간선의 수
        }
    }

    private int getNodeNum() {
        int nodeNum = 0;
        for (int i = 1; i <= maxNode; i++) {
            int out = edgeNum[i][0];
            int in = edgeNum[i][1];

            if (out >= 2 && in == 0) {
                nodeNum = i;
                break;
            }
        }

        return nodeNum;
    }

    private int[] getGraphNum(int nodeNum) {
        int donut = 0, bar = 0, eight = 0; // 각 그래프의 수

        List<Integer> g = graph.get(nodeNum);

        for (int node : g) {
            int[] ve = {0, 0};
            bfs(node, ve);

            int v = ve[0], e = ve[1];
            if (v == e) donut++;
            else if (v == e + 1) bar++;
            else if (v == e - 1) eight++;
        }

        return new int[]{ donut, bar, eight };
    }

    private void bfs(int node, int[] ve) {
        visited[node] = true;
        ve[0]++;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            List<Integer> g = graph.get(queue.poll());
            ve[1] += g.size();

            for (int next : g) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    ve[0]++;
                }

            }
        }
    }
}