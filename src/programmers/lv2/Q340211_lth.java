package programmers.lv2;

/* 프로그래머스 340211번 충돌위험 찾기 문제

[문제 풀이]
일단 각 로봇이 이동하는 경로를 리스트에 담고,
이동하는 시간에 맞춰서 좌표가 같은지 확인하는 방식으로 품
[코드 짜기]
루트 별로 루트의 시작 좌표를 추가하고, 루트를 모두 돌면서 다음 좌표로 이동하는 경로를 리스트에 이어붙임
그 다음에 시간 별로 좌표가 같은지 확인
경로 중 가장 긴 경로의 길이만큼 반복문을 돌면서, 각 루트가 그 시간에 어디에 있는지 확인
-> 좌표를 key로, 그 좌표에 있는 루트 번호 리스트를 value로 하는 map을 만듦
-> value의 리스트 크기가 2 이상이면 충돌이 발생한 것이므로 결과값에 +1
반복문을 도는 중 도착점에 도달한 루트는 제외
*/  


import java.util.*;

class Q340211_lth {
    public int solution(int[][] points, int[][] routes) {
        List<int[][]> routesPath = new ArrayList<>();
        
        for(int i = 0; i < routes.length; i++){
            int[] r = routes[i];             
            List<int[]> path = new ArrayList<>();

            // 루트의 시작 좌표 추가
            int[] cur = points[r[0] - 1];
            path.add(new int[]{cur[0], cur[1]});

            // 루트를 모두 들림
            for (int j = 1; j < r.length; j++) {
                int[] next = points[r[j] - 1];
                makeRoute(path, cur, next); // cur→next 이동을 path 뒤에 이어붙임
                cur = next;
            }

            routesPath.add(path.toArray(new int[path.size()][]));
        }        
        
        int answer = countCollision(routesPath);
        
        return answer;
    }
    
    private void makeRoute(List<int[]> path, int[] start, int[] end){
        int x = start[0], y = start[1];
        int ex = end[0], ey = end[1];
        
        while (x != ex) {                   // x 맞추기
            if (ex > x) {
                x = x + 1;
            } else {
                x = x - 1;
            }
            path.add(new int[]{x, y});
        }
        while (y != ey) {                   // y 맞추기
            if (ey > y) {
                y = y + 1;
            } else {
                y = y - 1;
            }
            path.add(new int[]{x, y});
        }
        
    }
    
    //hashmap은 어려워요
    private int countCollision(List<int[][]> routesPath){
        int collisions = 0;
        int maxT = 0;
        for (int[][] path : routesPath){
            maxT = Math.max(maxT, path.length);
        } 

        for (int t = 0; t < maxT; t++) {
            Map<String, List<Integer>> posToRoutes = new HashMap<>();

            for (int r = 0; r < routesPath.size(); r++) {
                int[][] path = routesPath.get(r);
                if (t >= path.length) continue; // 도착 후는 제외

                int[] pos = path[t];
                String key = pos[0] + "," + pos[1];
                posToRoutes.computeIfAbsent(key, k -> new ArrayList<>()).add(r);
            }
            for (Map.Entry<String, List<Integer>> entry : posToRoutes.entrySet()) {
                List<Integer> routeNumber = entry.getValue();
                if (routeNumber.size() >= 2) {
                    collisions++;
                }
            }
        }
        return collisions;
    }
}