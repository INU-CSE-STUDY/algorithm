package programmers.lv2;

/* 프로그래머스 172927번 광물 캐기 문제

[문제 풀이]
광물 5개는 무조건 세트메뉴로 캐야 하니까 광물 그룹을 생성!
객체에 곡괭이 3종류의 피로도를 저장하고 돌곡괭이 기준으로 내림차순 정렬
그리고 가중치 높은 애들을 순서대로 좋은 곡괭이로 캔다

*/

import java.util.*;

class Q172927_lth{
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int n = minerals.length;
        int totalPicks = picks[0] + picks[1] + picks[2];
        int limit = Math.min(n, totalPicks * 5);
        
        List<Mineral> arrangement = new ArrayList<>();
        
        // 광물을 5개씩 묶어서 객체를 만들어서 돌곡괭이 기준으로 피로도 측정후 저장
        for(int i = 0; i < limit; i += 5){
            int end = Math.min(i + 5, n);   // 범위 초과시 마지막은 n
            String[] slice = Arrays.copyOfRange(minerals, i, end);  // 배열 잘라서 만듬
            arrangement.add(new Mineral(slice));
        }
        
        arrangement.sort((a, b) -> b.fatigues[2] - a.fatigues[2]);
        
        for (Mineral current : arrangement) {
            int pickIdx = -1;
            if (picks[0] > 0) {            // 다이아 곡괭이 우선
                pickIdx = 0;
            } else if (picks[1] > 0) {     // 그다음 철
                pickIdx = 1;
            } else if (picks[2] > 0) {     // 마지막 돌
                pickIdx = 2;
            } else {
                break; // 곡괭이 없음
            }

            answer += current.fatigues[pickIdx];        //객체의 피로도 더하기
            picks[pickIdx]--;       // 사용한 곡괭이 줄이기
        }

        return answer;
    }
    
    class Mineral{
        int[] fatigues;

        public Mineral(String[] slice){
            this.fatigues = calculate(slice);
        }

        // 각 곡괭이로 캘 때의 피로도 계산
        private int[] calculate(String[] slice){
            int iron = 0;
            int dia = 0;
            int stone = 0;
            for (String m : slice) {
                switch (m) {
                    case "diamond":
                        dia   += 1;
                        iron  += 5;
                        stone += 25;
                        break;
                    case "iron":
                        dia   += 1;
                        iron  += 1;
                        stone += 5;
                        break;
                    case "stone":
                        dia   += 1;
                        iron  += 1;
                        stone += 1;
                        break;
                }
            }
            return new int[]{dia, iron, stone};
        }
    }
}