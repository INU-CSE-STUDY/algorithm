package programmers.lv2;

import java.util.*;

// 4**7 이다 그냥 완탐!
public class Q150368_jhg {

    static int[] discountRate = {10, 20, 30, 40};
    static int[] answer = {0, 0};
    static List<Emoticon> emoList = new ArrayList<>();

    public int[] solution(int[][] users, int[] emoticons) {
        recurEmoticons(users, emoticons, 0);
        return answer;
    }

    static void recurEmoticons(int[][] users, int[] emoticons, int index) {
        if (index == emoticons.length) {
            int[] result = proceedUser(users);
            if (answer[0] < result[0]) {
                answer = result.clone();
            }
            if (answer[0] == result[0] && answer[1] < result[1]) {
                answer = result.clone();
            }

            return;
        }

        for(int i = 0; i < 4; i++) {
            int rate = discountRate[i];
            int discountPrice = emoticons[index] * (100 - rate)/100;

            emoList.add(new Emoticon(rate, discountPrice));
            recurEmoticons(users, emoticons, index + 1);
            emoList.remove(emoList.size() - 1);
        }
    }

    static int[] proceedUser(int[][] users) {
        int plusMember = 0;
        int totalPrice = 0;

        for (int[] user : users) {
            int userWishRate = user[0];
            int userMaxPrice = user[1];

            int userPurchasePrice = 0;
            for (Emoticon emo : emoList) {
                if (emo.isPurchase(userWishRate)) {
                    userPurchasePrice += emo.price;
                }
            }

            if (userPurchasePrice >= userMaxPrice) {
                plusMember++;
            } else {
                totalPrice += userPurchasePrice;
            }
        }

        return new int[] {plusMember, totalPrice};
    }

    static class Emoticon {
        int rate;
        int price;

        Emoticon(int rate, int price) {
            this.rate = rate;
            this.price = price;
        }

        public boolean isPurchase(int userWishRate) {
            return userWishRate <= this.rate;
        }
    }
}
