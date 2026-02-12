package programmers.lv2;

/* 프로그래머스 42584번 주식가격 문제

[문제 풀이]
그냥 완전탐색 하면 되는 거 아닌가

*/

class Q42584_kj {
    public int[] solution(int[] prices) {

        int[] answer = new int[prices.length];

        // 마지막 값은 0 고정이니까 그냥 그 앞까지만 체크
        for (int i = 0; i < prices.length - 1; i++) {

            int nowPrice = prices[i];
            int second = 0;

            for (int j = i + 1; j < prices.length; j++) {

                second++; // 인덱스와 인덱스 사이가 1초니까 증가시키고 시작

                // 주식 가격이 현재 가격보다 떨어졌다면 종료
                int nextPrice = prices[j];
                if (nowPrice > nextPrice) break;
            }

            answer[i] = second;
        }

        return answer;
    }
}
