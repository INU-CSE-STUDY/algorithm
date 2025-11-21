package programmers.lv2;

/* 프로그래머스 86971번 전력망을 둘로 나누기 문제

[문제 풀이]
오늘도 완전탐색 문제
진짜 완전탐색으로 하나씩 돌아가면서 특정 전선을 끊었을 때 기준으로 송전탑의 개수를 각각 구하면 됨

1. for문으로 순회하면서 특정 송전탑 자르기
   - 자른 거 기준으로 트리 그래프를 구성
2. 새롭게 생성한 트리 그래프 탐색하면서 각 트리마다 연결된 송전탑 개수 구하기
3. 순회 끝났으면 절댓값 차이 구해서 min 값인지 체크하고 업데이트
*/

import java.util.*;

class Q86971_kj {

    List<List<Integer>> tree; // 트리 그래프 정보 저장용
    boolean[] visited; // 각 노드별 방문 여부 체크

    List<Integer> wireCountList; // 두 개의 트리에 포함되어있는 송전탑(노드)의 개수 저장용
    int wireCount; // 각각의 트리에 포함되어있는 송전탑(노드)의 개수

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        for (int cutZone = 0; cutZone < n - 1; cutZone++) {

            // 매번 탐색한 후 송전탑의 개수를 세야 하니까 매 반복마다 초기화
            visited = new boolean[n + 1];
            wireCountList = new ArrayList<>();

            // 잘린 송전탑끼리의 연결을 고려한 트리 만들기(하나의 트리를 두 개로 나누는 과정?)
            tree = initTree(n, wires, cutZone);

            // 각 송전탑(노드) 별로 하나씩 순회하며 연결된 송전탑 개수 세기
            for (int node = 1; node <= n; node++) {
                if (!visited[node]) {
                    wireCount = 1;
                    DFS(node);
                    wireCountList.add(wireCount);
                }
            }

            // 두 전력망이 가지고 있는 송전탑 개수의 차이(절댓값)의 최솟값으로 업데이트
            // 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 = 차이가 작을 수록 비슷한 거니까
            answer = Math.min(answer, Math.abs(wireCountList.get(0) - wireCountList.get(1)));
        }

        return answer;
    }

    private void DFS(int node) {

        // 방문 체크 후, 해당 노드와 연결되어 있는 다른 노드 정보 불러오기
        visited[node] = true;
        List<Integer> graph = tree.get(node);

        // 차례차례 탐색하기
        for (int next : graph) {
            if (!visited[next]) {
                wireCount++;
                DFS(next);
            }
        }
    }

    private List<List<Integer>> initTree(int n, int[][] wires, int cutZone) {

        // 트리 초기화
        List<List<Integer>> tree = new ArrayList<>();
        for (int node = 0; node <= n; node++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < wires.length; i++) {

            if (i == cutZone) continue; // 자른 지점은 트리 정보에 추가하지 않음

            int v1 = wires[i][0];
            int v2 = wires[i][1];

            tree.get(v1).add(v2);
            tree.get(v2).add(v1);
        }

        return tree;
    }
}