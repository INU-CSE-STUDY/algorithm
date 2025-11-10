package programmers.lv3;

/* 프로그래머스 214289번 에어컨 문제

[문제 풀이]
끝나는 시간을 빠른 순서로 정렬을 해서 사용해야해서 pq를 사용하며
숫자가 적어 완탐이 될거 같아 단계별로 재귀로 돌려서 해결
-> 그리디로 유형중에 어디에 멘토를 추가하면 더 낮은 대기시간이 나오는지 확인하며 푸는 방법도 있을거 같음
*/


import java.util.*;

class Q214288_lth {
    public int solution(int k, int n, int[][] reqs) {
        int m = reqs.length;
        int remain = n - k; // 남은 멘토 수
        List<int[]>[] byType = new ArrayList[k];

        // 유형별 요청 분류
        for (int i = 0; i < k; i++) byType[i] = new ArrayList<>();
        for (int[] r : reqs) {
            int start = r[0];
            int duration = r[1];
            int type = r[2] - 1; // 0-based
            byType[type].add(new int[]{start, duration});
        }
        // 각 유형별로 시작 시간 기준 정렬
        for (int i = 0; i < k; i++) {
            byType[i].sort(Comparator.comparingInt(a -> a[0]));
        }

        int[] alloc = new int[k]; // 각 유형별 상담원 수
        Arrays.fill(alloc, 1);    // 최소 1명씩 배정
        int[] best = {Integer.MAX_VALUE};

        distribute(0, remain, alloc, byType, best);

        return best[0];
    }

    // 남은 멘토를 유형별로 배분하는 완전탐색
    private void distribute(int idx, int remain, int[] alloc, List<int[]>[] byType, int[] best) {
        int k = alloc.length;

        if (idx == k) {
            if (remain == 0) {
                int totalWait = 0;
                for (int t = 0; t < k; t++) {
                    totalWait += waitTime(byType[t], alloc[t]);
                    if (totalWait >= best[0]) return;
                }
                best[0] = Math.min(best[0], totalWait);
            }
            return;
        }

        // 현재 유형 idx에 몇 명 더 배정할지 시도
        for (int i = 0; i <= remain; i++) {
            alloc[idx] += i;
            distribute(idx + 1, remain - i, alloc, byType, best);
            alloc[idx] -= i; // 백트래킹 재귀를 위해
        }
    }

    // 특정 유형의 총 대기시간 계산
    private int waitTime(List<int[]> requests, int num) {
        if (requests.isEmpty()) return 0;

        // 멘토별로 끝나는 시간 관리하는 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < num; i++){
            pq.offer(0);
        }

        int wait = 0;
        for (int[] r : requests) {
            int start = r[0];
            int duration = r[1];
            int end = pq.poll();

            if (end <= start) {
                pq.offer(start + duration);
            } else {
                wait += (end - start);
                pq.offer(end + duration);
            }
        }
        return wait;
    }
}
