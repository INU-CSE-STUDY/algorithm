package programmers.lv3;

/* 프로그래머스 258707번 n + 1 카드게임 문제

[문제 풀이]

1 ~ n 사이의 수가 적힌 카드가 존재하는 카드 뭉치(원소는 중복되지 않음)와 일정량의 코인을 가지고 시작
카드에 적힌 수의 합이 n + 1이 되도록 카드 두장을 내면서 라운드를 진행하는 게임
=> 중복 원소가 없으므로 (1, n), (2, n - 1), (3, n - 2), ... 이런 식으로 무조건 쌍이 결정되어 있음.

조합을 완성하는데 필요한 코인 개수를 우선순위 큐에 하나씩 저장하면서 코인 개수가 가장 덜 드는 방향으로 하기
-> 이렇게 해야지 최대한 많은 라운드를 거칠 수 있다!

*/

import java.util.*;

class Q258707_kj {
    static int n, target;

    static int[] whichRound; // 해당 카드가 어느 라운드에서 뽑혔는지 확인(가지고 시작한 카드는 0으로)
    static boolean[] visited; // 이미 뽑아둔 카드인지 확인

    static PriorityQueue<Integer> pq = new PriorityQueue<>(); // 쌍을 만드는데 필요한 코인의 개수 저장

    public int solution(int coin, int[] cards) {
        n = cards.length;
        target = n + 1;

        whichRound = new int[n + 1];
        visited = new boolean[n + 1];

        initMyCards(cards);
        return gameStart(cards, coin);
    }

    // 게임 시작 전, 카드 n/3 장을 가진 채로 시작
    private void initMyCards(int[] cards) {
        for (int i = 0; i < n / 3; i++) {
            int card = cards[i];
            visited[card] = true;
            whichRound[card] = 0; // (n / 3)개까지는 시작할 때부터 내가 갖고 있는 카드

            int pair = target - card; // 현재 카드 번호와 짝이 되는 카드 번호
            if (visited[pair]) {
                // 현재 뽑은 카드와 짝이 되는 카드를 갖고 있다면, 필요한 코인 개수 삽입
                pq.add(0); // 둘 다 가지고 시작하는 것이기 때문에 코인 0개 소모
            }
        }
    }

    private int gameStart(int[] cards, int coin) {
        int round = 1;

        for (int i = n / 3; i < n; i++) {
            int card = cards[i];
            visited[card] = true;
            whichRound[card] = round;

            int pair = target - card;
            if (visited[pair]) {
                if (whichRound[pair] == 0) {
                    // 가지고 시작한 카드와 짝이라면, 새로운 카드 한 장만 뽑으면 되니까 코인 1개 필요
                    pq.add(1);
                }
                else {
                    // 둘 다 0라운드 이후에 뽑은 카드라면, 새로운 카드 두 장을 뽑아야 하는거니 코인 2개 필요
                    pq.add(2);
                }
            }


            if (i % 2 == 1) {
                // 한 라운드가 끝났을 때(카드 두 장을 모두 확인한 경우)

                if (!pq.isEmpty()) {
                    // 큐가 비어있지 않다면, target(n + 1)이 되는 카드 두 장이 존재한다는 뜻
                    int cost = pq.poll();  // 필요한 코인의 개수

                    if (coin >= cost) {
                        // 해당 조합을 만드는데 코인이 충분하다면 해당 조합을 내고 다음 라운드로 넘어감
                        coin -= cost;
                        round++;
                    } else break; // 해당 조합으로 target을 만드는데 코인이 부족할 경우 종료
                } else break; // 큐가 비어있다면 종료
            }
        }

        return round;
    }
}