package programmers.lv2;

/* 프로그래머스 150368번 이모티콘 할인행사 문제

[문제 풀이]

users와 emoticons 길이 모두 작은 수라 완전탐색으로 구하기

*/

class Q150368_kj {
    final int[] discountRate = { 10, 20, 30, 40 }; // 이모티콘에 적용될 수 있는 할인율
    int[] max = new int[2];

    public int[] solution(int[][] users, int[] emoticons) {

        // 각 조합별로 계산해 가능한 최댓값을 구함
        recur(0, users, emoticons, new int[emoticons.length]);

        return max;
    }

    private void recur(int depth, int[][] users, int[] emoticons, int[] discount) {
        if (depth == emoticons.length) {
            int[] result = calculate(users, emoticons, discount);

            if ((result[0] > max[0]) || (result[0] == max[0] && result[1] > max[1])) {
                // 이모티콘 플러스 서비스 가입자 수가 더 많거나,
                // 서비스 가입자 수가 동일하면서 이모티콘 판매액이 더 높은 경우 업데이트
                max[0] = result[0];
                max[1] = result[1];
            }

            return;
        }

        for (int rate : discountRate) {
            discount[depth] = rate;
            recur(depth + 1, users, emoticons, discount);
        }
    }

    private int[] calculate(int[][] users, int[] emoticons, int[] discount) {
        int subscribers = 0;
        int totalPrice = 0;

        for (int[] user : users) {
            int wantRate = user[0];  // 사용자가 원하는 할인 비율
            int wantValue = user[1]; // 사용자가 생각하는 마지노선 금액

            int purchaseValue = 0;
            for (int i = 0; i < emoticons.length; i++) {
                int price = emoticons[i];
                int rate = discount[i];

                if (rate >= wantRate) {
                    // 사용자가 원하는 할인율 이상일 경우, 해당 이모티콘을 구매함
                    purchaseValue += (int) (price - (price * rate / 100.0));
                }
            }

            if (wantValue <= purchaseValue) {
                // 사용자의 이모티콘 구매 비용의 합이 일정 가격 이상일 때, 이모티콘을 구매하지 않고 이모티콘 플러스 가입
                subscribers++;
            } else {
                // 이모티콘 플러스에 가입하지 않고, 원하는 이모티콘만 구매
                totalPrice += purchaseValue;
            }
        }

        return new int[]{ subscribers, totalPrice };
    }
}