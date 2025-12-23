package programmers.lv2;

/* 프로그래머스 49994번 방문 길이 문제

[문제 풀이]
방문한 길을 저장하는게 포인트인 문제로 시작점과 도착점을 문자열로 저장해서 양방향 모두 저장해줬다.
좌표가 -5~5 사이를 벗어나면 무시해주는 것도 잊지말기!
String builder를 이용해서 문자열을 만들며 문자 사이 ,를 넣는 이유는 두자리 이상 숫자가 붙어서 헷갈리는 경우를 방지하기 위함이다.

*/

import java.util.*;

class Q49994_lth {
    public int solution(String dirs) {
        Set<String> visited = new HashSet<>();
        int x = 0, y = 0;
        int answer = 0;
        
        for (char d : dirs.toCharArray()) {
            int nx = x;
            int ny = y;

            if (d == 'U'){
                ny++;
            }
            else if (d == 'D'){
                ny--; 
            } 
            else if (d == 'L'){
                nx--; 
            } 
            else if (d == 'R'){
                nx++; 
            } 

            // 좌표 벗어나면 무시
            if (nx < -5 || nx > 5 || ny < -5 || ny > 5){
                continue; 
            } 

            // 양방향 모두 저장
            StringBuilder sb1 = new StringBuilder();
            sb1.append(x).append(',').append(y)
               .append(',').append(nx).append(',').append(ny);

            StringBuilder sb2 = new StringBuilder();
            sb2.append(nx).append(',').append(ny)
               .append(',').append(x).append(',').append(y);

            String path1 = sb1.toString();
            String path2 = sb2.toString();

            // 처음간 길만 카운트
            if (!visited.contains(path1)) {
                visited.add(path1);
                visited.add(path2);
                answer++;
            }

            // 좌표 이동
            x = nx;
            y = ny;
        }
        
        return answer;
    }
}