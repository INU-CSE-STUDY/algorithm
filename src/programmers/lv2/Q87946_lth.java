package programmers.lv2;

/* 프로그래머스 87946번 피로도 문제

[문제 풀이]
완탐으로 푸는 문제라 요즘 사용해본 백트래킹으로 구현
0부터 돌면서 방문처리하고 다음 단계로 넘어가는 방식으로 구현
*/

class Solution {
    int[][] dungeons;
    boolean[] visited;
    int best = 0;

    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;
        this.visited = new boolean[dungeons.length];

        dfs(k, 0);
        return best;
    }

    void dfs(int remain, int count) {
        best = Math.max(best, count);

        for (int i = 0; i < dungeons.length; i++) {
            int need = dungeons[i][0];
            int use  = dungeons[i][1];

            // 동일한 상태 반복
            if (visited[i]) continue;

            // 최소 피로도 부족
            if (remain < need) continue;

            // 방문 처리
            visited[i] = true;

            // 다음 단계
            dfs(remain - use, count + 1);

            // 복원
            visited[i] = false;
        }
    }
}
