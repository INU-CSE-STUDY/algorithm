
/* 프로그래머스 77485번 행렬 테두리 회전하기 문제

[문제 풀이]
그냥 맵 만들어놓고 회전시키면 된다
*/

class Q77485_lth {
    int[][] map;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
        map = initmap(rows, columns);
        int[] list = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int temp = calculate(queries[i]);
            list[i] = temp;
        }
        
        return list;
    }
    
    private int[][] initmap(int rows, int columns) {
        int[][] temp = new int[rows][columns];
        int num = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp[i][j] = num++;
            }
        }   
        return temp;
    }
    
    private int calculate(int[] q){
        int sx = q[0] - 1; // 0-based
        int sy = q[1] - 1;
        int ex = q[2] - 1;
        int ey = q[3] - 1;

        int temp = map[sx][sy];
        int min = temp;

        // → 오른쪽
        for (int y = sy + 1; y <= ey; y++) {
            int temp2 = map[sx][y];
            map[sx][y] = temp;
            temp = temp2;
            min = Math.min(min, temp);
        }

        // 아래
        for (int x = sx + 1; x <= ex; x++) {
            int temp2 = map[x][ey];
            map[x][ey] = temp;
            temp = temp2;
            min = Math.min(min, temp);
        }

        // 왼쪽
        for (int y = ey - 1; y >= sy; y--) {
            int temp2 = map[ex][y];
            map[ex][y] = temp;
            temp = temp2;
            min = Math.min(min, temp);
        }

        // 위
        for (int x = ex - 1; x >= sx; x--) {
            int temp2 = map[x][sy];
            map[x][sy] = temp;
            temp = temp2;
            min = Math.min(min, temp);
        }

        return min;
    }
}