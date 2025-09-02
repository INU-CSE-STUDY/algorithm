/* 프로그래머스 250136번 석유 시추추 문제


[문제 풀이]
*세로 줄을 모두 스택에 넣고 스택을 dfs로 돌리면 매 세로줄마다 묻힌 석유의 양을 구할 수 있을 거라 생각했음
boolen[][] visited로 방문처리하고 스택에 넣을 때마다 visited 처리
-> 시간 초과로 실패
*배열의 내용을 0과1이 아닌 dfs로 탐색한 석유의 수로 바꿔서 시도
-> 중복처리 문제
*석유별로 라벨링을 하여 전체에서 한번만 dfs를 돌리도록 변경경

*/

import java.util.*;

//좌표용 클래스
class Point {
    int i, j;
    Point(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        int n = land.length;
        int m = land[0].length;
        
        //석유 덩어리 id 저장 배열
        int[][] oliId = new int[n][m];
        // -1로 초기화
        for (int[] row : oliId) Arrays.fill(row, -1);
        
        // 석유 크기 저장 리스트
        List<Integer> oliSize = new ArrayList<>();
        // 석유 덩어리 id
        int id = 0;
        
        // 석유 덩어리 id 매기기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && oliId[i][j] == -1) {
                    int size = dfs(land, oliId, i, j, id);
                    oliSize.add(size);
                    id++;
                }
            }
        }
        
        // 각 열마다 묻힌 석유의 양 구하기
        for (int j = 0; j < m; j++) {
            // 중복된 석유 덩어리 id를 저장할 집합
            HashSet<Integer> seen = new HashSet<>();
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int oid = oliId[i][j];
                if (oid != -1 && seen.add(oid)) {
                    sum += oliSize.get(oid);
                }
            }
            // 최대값 갱신
            if (sum > answer) answer = sum;
        }
        return answer;
    }
    
    // dfs로 석유 덩어리 크기 구하기
    private int dfs(int[][] land, int[][] oliId, int si, int sj, int id) {
        int n = land.length;
        int m = land[0].length;
        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};

        // deque를 스택처럼 사용해서 dfs 구현
        Deque<Point> stack = new ArrayDeque<>();
        stack.push(new Point(si, sj));
        oliId[si][sj] = id;

        int area = 0;
        while (!stack.isEmpty()) {
            Point p = stack.pop();
            area++;

            for (int d = 0; d < 4; d++) {
                int ni = p.i + di[d];
                int nj = p.j + dj[d];
                if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                    if (land[ni][nj] == 1 && oliId[ni][nj] == -1) {
                        oliId[ni][nj] = id;
                        stack.push(new Point(ni, nj));
                    }
                }
            }
        }
        return area;
    }
}