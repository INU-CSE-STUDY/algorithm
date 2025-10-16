package programmers.lv2;

/* 프로그래머스 150368번 이모티콘 할인행사 문제

[문제 풀이]

알고리즘이 안 보이고 수가 적어보인다 = 완탐
경우의 수를 어떻게 만들까하다가 김진의 의견으로 백트래킹을 찾아봤습니다
gpt의 도움을 받아 solution위에 선언을 했는데 굉장히 편하네요

*/

class Q150368_lth {
    int n, m;
    int bestSubscribers = 0;
    int bestRevenue = 0;
    int[] discounts = {10, 20, 30, 40};
    int[][] users;
    int[] emoticons;
    int[] picked;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        this.users = users;
        this.emoticons = emoticons;
        this.n = users.length;
        this.m = emoticons.length;
        this.picked = new int[m];

        dfs(0);
        
        return new int[]{bestSubscribers, bestRevenue};
    }
    
    // 이모티콘 idx에 할인율을 할당하는 백트래킹
    private void dfs(int idx) {
        if (idx == m) {
            calculate(); // 모든 이모티콘에 할인율을 배정했으면 결과 계산
            return;
        }
        for (int d : discounts) {
            picked[idx] = d;
            dfs(idx + 1);
        }
    }
    
    // 현재 picked로 계산
    private void calculate() {
        int subscribers = 0;
        int revenue = 0;

        for (int i = 0; i < n; i++) {
            int minDisc = users[i][0];    
            int threshold = users[i][1];
            int sum = 0;

            // 이모티콘 금액 합산
            for (int j = 0; j < m; j++) {
                if (picked[j] >= minDisc) {
                    // 할인 적용 가격 = 원가 * (100 - 할인) / 100
                    sum += emoticons[j] * (100 - picked[j]) / 100;
                }
            }

            if (sum >= threshold) {
                subscribers++;  // 구독 전환
            } else {
                revenue += sum; // 매출에 합산
            }
        }

        // 가입 우선, 같다면 매출로 비교
        if (subscribers > bestSubscribers) {
            bestSubscribers = subscribers;
            bestRevenue = revenue;
        } else if (subscribers == bestSubscribers && revenue > bestRevenue) {
            bestRevenue = revenue;
        }
    }
}