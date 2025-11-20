package programmers.lv2;

/* 프로그래머스 87946번 피로도 문제

[문제 풀이]
완전탐색이라고 문제에 적혀있다..
visited 배열로 방문 관리하면서 백트래킹 탐색

*/

class Q87946_kj {

    boolean[] visited;
    int answer = -1;

    public int solution(int k, int[][] dungeons) {

        visited = new boolean[dungeons.length];
        DFS(0, k, dungeons);
        return answer;
    }

    private void DFS(int possibleCount, int remain, int[][] dungeons) {

        answer = Math.max(answer, possibleCount);

        for (int i = 0; i < dungeons.length; i++) {

            if (visited[i]) continue; // 이미 방문한 던전이면 넘어가기

            int need = dungeons[i][0];
            int use = dungeons[i][1];

            if (remain < need) continue; // 최소 필요 피로도가 부족한 경우 넘어가기

            visited[i] = true;
            DFS(possibleCount + 1, remain - use, dungeons);
            visited[i] = false; // 방문 처리 해제해서 백트래킹되게 하기
        }
    }
}
