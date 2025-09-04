package programmers.lv2;

/**
 * 물구나무 서서 봐도 완전 탐색 문제이다.
 * 최대 수행이 30c5 * 10 * 5 이다
 */
public class Q388352_jhg {

    static boolean[] visited;
    static int answer = 0;
    static int N;

    public int solution(int n, int[][] q, int[] ans) {
        N = n;
        visited = new boolean[N+1];
        dfs(q, ans, 0, 0);

        return answer;
    }

    private void dfs(int[][] q, int[] ans, int count, int pos) {
        if (count == 5) {
            check(q, ans);
            return;
        }

        for (int i = pos+1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(q, ans, count+1, i);
                visited[i] = false;
            }
        }
    }

    private void check(int[][] q, int[] ans) {
        for (int i = 0; i < q.length; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                if (visited[q[i][j]]) {
                    count++;
                }
            }

            if (count != ans[i]) {
                return;
            }
        }

        answer++;
    }
}
