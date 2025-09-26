/* 프로그래머스 250136번 지게차와 크레인 문제

[제약 조건]
n * m 크기의 격자모양 컨테이너들
requests[i]의 길이가 1이면 지게차를 이용한 출고 요청을, 2이면 크레인을 이용한 출고 요청을 의미

[문제 풀이]
0. 0으로 둘러싸인 배열 만들기
1. 지게차 출고 요청
찾은 컨테이너 근처에 0이 존재하면 찾은 컨테이너를 0으로 바꾸고 answer -1
찾은 컨테이너 근처에 1이 존재하면 그 1을 스택에 넣고 그 1근처에 0이 존재하는지 반복 찾으면 0으로 고치고 answer -1
2. 크레인 출고 요청
찿은 컨테이너를 1로 바꾸고 answer -1
*/

import java.util.*;

//좌표용 클래스
class Point {
    int j, x;
    Point(int j, int x) {
        this.j = j;
        this.x = x;
    }
}

class Q388353_lth {
    public int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();
        int answer = n*m;

        // 0으로 둘러싸인 배열 만들기
        char[][] container = new char[n + 2][m + 2];
        for (int j = 0; j < m + 2; j++){
            container[0][j] = '0';
        }
        for (int i = 0; i < n; i++) {
            container[i + 1][0] = '0';  
            for (int j = 0; j < m; j++) {
                container[i + 1][j + 1] = storage[i].charAt(j);
            }
            container[i + 1][m + 1] = '0';
        }
        for (int j = 0; j < m + 2; j++){
            container[n + 1][j] = '0';
        }

        
        for(int i = 0; i < requests.length; i++){
            if(requests[i].length() == 1){
                char target = requests[i].charAt(0);
                List<int[]> toRemove = new ArrayList<>();
                for(int j = 0; j < container.length; j++){
                    for (int x = 0; x < container[j].length; x++) {
                        if (container[j][x] == target) {
                            if (dfs(container, j, x)) {
                                toRemove.add(new int[]{j, x});
                            }
                        }
                    }
                }
                for (int[] pos : toRemove) {
                    int rj = pos[0], rx = pos[1];
                    container[rj][rx] = '0';
                }
                answer -= toRemove.size();
            }
            else{
                char target = requests[i].charAt(0);
                for(int j = 0; j < container.length; j++){
                    for (int x = 0; x < container[j].length; x++) {
                        if (container[j][x] == target) {
                            container[j][x] = '1';
                            answer--;
                        }
                    }
                }
            }
        }

        return answer;
    }
    private boolean dfs(char[][] container, int sj, int sx) {
        int n = container.length;
        int m = container[0].length;
        char ch = container[sj][sx];
        int[] dj = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        // deque를 스택처럼 사용해서 dfs 구현
        boolean[][] visited = new boolean[n][m];
        Deque<Point> stack = new ArrayDeque<>();
        stack.push(new Point(sj, sx));
        visited[sj][sx] = true;

        while (!stack.isEmpty()) {
            Point p = stack.pop();
            for (int d = 0; d < 4; d++) {
                int nj = p.j + dj[d];
                int nx = p.x + dx[d];
                if (nj >= 0 && nj < n && nx >= 0 && nx < m) {
                    if (visited[nj][nx]) continue;
                    if (container[nj][nx] == '0') {
                        return true;
                    } else if(container[nj][nx] == '1'){
                        visited[nj][nx] = true;
                        stack.push(new Point(nj, nx));
                    }
                }
            }
        }
        return false;
    }
}
