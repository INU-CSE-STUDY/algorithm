package programmers.lv3;
/*
[문제 풀이]
처음에는 빨강을 먼저 bfs로 돌리고 파랑을 돌리는데 point객체에 루트를 저장해서 파랑을 돌릴때 n번째 턴에 빨강의 n번째 루트와 같으면
그 칸을 못가게 하며 돌렸으며 빨강은 첫 bfs에서 파랑의 시작지점을 지나가지 못하게 했지만 3,4,10,15,16에서 실패를 해서
예외를 생각해보니 빨강보다 파랑을 먼저 돌려야할수도 있고 도착지점에 멈춰있는것은 어떻게 지나갈 것이며 하면서 한쪽을 먼저 돌리는 것은
잘못된 접근인 것 같아서
visited배열을 4차원으로 만들어서 빨강과 파랑의 위치를 같이 저장하며 bfs를 돌리며 각각의 방향을 4방향씩 조합하여 16가지 경우를 모두 탐색하며
둘다 도착지점에 도달했을때의 거리를 반환하도록 하였다 교차 하거나 같은 칸에 머무르는 경우도 제외하였다.
이렇게 풀면 13,14번에서 오류가 발생해 질문하기를 보니 시작지점을 재방문하는 문제라 하여 시작지점 방문을 금지하는 조건을 추가하여 통과하였다.
이 코드는 각 색깔이 자신이 방문했던 칸을 다시 방문하는 것은 허용한다는 점에서 문제있는 코드지만 이렇게라도 풀었다.
*/

import java.util.*;

class Q250134_lth {
    // 상, 하, 좌, 우
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    // 상태 클래스(빨강x, 빨강y, 파랑x, 파랑y, 거리)
    static class State {
        int rx, ry, bx, by, dist;
        State(int rx, int ry, int bx, int by, int dist) {
            this.rx = rx; this.ry = ry; this.bx = bx; this.by = by; this.dist = dist;
        }
    }

    public int solution(int[][] board) {
        int n = board.length, m = board[0].length;

        // 시작/목표 좌표
        int rsx=-1, rsy=-1, bsx=-1, bsy=-1, rgx=-1, rgy=-1, bgx=-1, bgy=-1;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                int v = board[i][j];
                if (v==1){ rsx=i; rsy=j; }
                else if (v==2){ bsx=i; bsy=j; }
                else if (v==3){ rgx=i; rgy=j; } // 빨강 목표
                else if (v==4){ bgx=i; bgy=j; } // 파랑 목표
            }
        }

        boolean[][][][] visited = new boolean[n][m][n][m];
        ArrayDeque<State> q = new ArrayDeque<>();
        visited[rsx][rsy][bsx][bsy] = true;
        q.offer(new State(rsx, rsy, bsx, bsy, 0));


        while(!q.isEmpty()){
            State cur = q.poll();

            // 둘 다 각자 목표 도달
            if (cur.rx==rgx && cur.ry==rgy && cur.bx==bgx && cur.by==bgy) return cur.dist;

            // 각자 자유 방향(16조합)
            for(int rdir=0;rdir<4;rdir++){
                for(int bdir=0;bdir<4;bdir++){
                    int nrx = cur.rx, nry = cur.ry;
                    int nbx = cur.bx, nby = cur.by;

                    boolean redArrived  = (cur.rx==rgx && cur.ry==rgy);
                    boolean blueArrived = (cur.bx==bgx && cur.by==bgy);

                    if (!redArrived) {
                        int tr = cur.rx + dx[rdir], tc = cur.ry + dy[rdir];
                        // 범위 밖/벽/시작칸 금지
                        if (!inBounds(tr, tc, n, m) || board[tr][tc] == 5 || board[tr][tc] == 1) continue;
                        nrx = tr; nry = tc;
                    }
                    
                    if (!blueArrived) {
                        int tr = cur.bx + dx[bdir], tc = cur.by + dy[bdir];
                        if (!inBounds(tr, tc, n, m) || board[tr][tc] == 5 || board[tr][tc] == 2) continue;
                        nbx = tr; nby = tc;
                    }

                    // 같은 칸 충돌 금지
                    if (nrx==nbx && nry==nby) continue;

                    // 교차(자리 바꿈) 금지
                    if (nrx==cur.bx && nry==cur.by && nbx==cur.rx && nby==cur.ry) continue;

                    if(!visited[nrx][nry][nbx][nby]){
                        visited[nrx][nry][nbx][nby] = true;
                        q.offer(new State(nrx, nry, nbx, nby, cur.dist+1));
                    }
                }
            }
        }
        // 도달 불가
        return 0;
    }

    // 범위 체크
    private boolean inBounds(int x,int y,int n,int m){
        return 0<=x && x<n && 0<=y && y<m;
    }
}
