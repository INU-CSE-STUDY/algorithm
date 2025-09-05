package programmers.lv2;

/* 프로그래머스 340212번 퍼즐 게임 챌린지 문제

[문제 풀이]
이분탐색으로 풀었는데 integer 범위를 벗어나는 경우가 있어서인지 오류가 나서 long으로 바꿔서 풀었다
가중치로 좀 더 빠르게 풀 수 있을까 했지만 실패  
*/

class Q340212_lth {
    public int solution(int[] diffs, int[] times, long limit) {
        int n = diffs.length;

        int lo = 0;
        int hi = 0;
        for (int d : diffs) hi = Math.max(hi, d);  // 레벨의 상한은 난이도 최대값
        int answer = hi;

        //이분 탐색
        while (lo <= hi) {
            int mid = (lo + hi) / 2; 
            long total = totalTime(diffs, times, mid, limit);

            if (total <= limit) {   //시간 충분
                answer = mid;
                hi = mid - 1;
            } else {    //시간 초과
                lo = mid + 1;
            }
        }
        return answer;
    }

    //전체 시간을 계산
    private long totalTime(int[] diffs, int[] times, int level, long limit) {
        long sum = 0;
        for (int i = 0; i < diffs.length; i++) {
            int need = diffs[i] - level;

            if (need <= 0) {
                sum += times[i];
            } else {
                if (i == 0) {
                    sum += (long) need * times[i] + times[i];
                } else {
                    sum += (long) need * (times[i] + times[i - 1]) + times[i];
                }
            }
        }
        return sum;
    }
}