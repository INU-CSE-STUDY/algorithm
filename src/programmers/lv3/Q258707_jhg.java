package programmers.lv3;

import java.util.PriorityQueue;

// PQ 같았다.
public class Q258707_jhg {

    public int solution(int coin, int[] cards) {
        int answer = 1;
        CardPriorityQueue pq = initQueue(cards);

        for (int i = cards.length / 3; i < cards.length; i += 2) {
            int firstCard = cards[i];
            int secondCard = cards[i + 1];
            pq.add(new Card(firstCard, 1, cards.length + 1));
            pq.add(new Card(secondCard, 1, cards.length + 1));

            Card top = pq.peek();

            if (top == null || !top.isMaxNumber() || coin < top.getCost()) {
                break;
            }

            pq.poll();
            coin -= top.getCost();
            answer++;
        }

        return answer;
    }

    private CardPriorityQueue initQueue(int[] cards) {
        CardPriorityQueue pq = new CardPriorityQueue();

        for (int i = 0; i < cards.length / 3; i++) {
            pq.add(new Card(cards[i], 0, cards.length + 1));
        }

        return pq;
    }

    public static class CardPriorityQueue extends PriorityQueue<Card> {

        @Override
        public boolean add(Card card) {
            Card complement = null;
            for (Card c : this) {
                if (card.isPair(c)) {
                    complement = c;
                    break;
                }
            }

            if (complement != null) {
                super.remove(complement);

                Card combined = card.add(complement);

                return super.add(combined);
            } else {
                return super.add(card);
            }
        }
    }

    public static class Card implements Comparable<Card> {
        int number;
        int cost;
        int maxNumber;

        public Card(int number, int cost, int maxNumber) {
            this.number = number;
            this.cost = cost;
            this.maxNumber = maxNumber;
        }

        public Card add(Card card) {
            return new Card(this.number + card.number, this.cost + card.cost, maxNumber);
        }


        public boolean isPair(Card card) {
            return this.maxNumber - this.number == card.number;
        }

        public boolean isMaxNumber() {
            return this.maxNumber == this.number;
        }

        public int getCost() {
            return this.cost;
        }

        @Override
        public int compareTo(Card o) {
            if (this.number == this.maxNumber && o.number == o.maxNumber) {
                return Integer.compare(this.cost, o.cost);
            }

            if (this.number == this.maxNumber) return -1;

            if (o.number == o.maxNumber) return 1;

            return Integer.compare(this.cost, o.cost);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Card card = (Card) o;

            if (this.isMaxNumber() || card.isMaxNumber()) {
                return false;
            }

            return number == card.number && cost == card.cost;
        }
    }
}
