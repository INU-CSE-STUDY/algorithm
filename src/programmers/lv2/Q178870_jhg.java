package programmers.lv2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 투 포인터였ㄱ고,,, 객체로 작성하는 거 까먹어서 오래 걸림 ㅎ..
 */
public class Q178870_jhg {

    public int[] solution(int[] sequence, int k) {
        List<Integer> sequenceList = Arrays.stream(sequence)
                .boxed()
                .collect(Collectors.toList());

        TwoPointer<Integer> twoPointer = new CustomTwoPointer(k, sequenceList.size());
        TwoPointer<Integer> answer = new CustomTwoPointer();

        while (!twoPointer.isEnd()) {

            twoPointer = twoPointer.move(twoPointer.getLeftValue(sequenceList), twoPointer.getRightValue(sequenceList));
            if (twoPointer.isAccrual() && answer.isBetterThan(twoPointer)) {
                answer = twoPointer;
            }
        }

        return answer.getPosition();
    }

    public static class CustomTwoPointer extends TwoPointer<Integer> {
        final int result;
        final int k;

        public CustomTwoPointer() {
            this.left = 0;
            this.right = Integer.MAX_VALUE;
            this.result = 0;
            this.k = 0;
            this.l = 0;
        }

        public CustomTwoPointer(int k, int l) {
            this.left = 0;
            this.right = -1;
            this.result = 0;
            this.k = k;
            this.l = l;
        }

        public CustomTwoPointer(int left, int right, int result, int k, int l) {
            this.left = left;
            this.right = right;
            this.result = result;
            this.k = k;
            this.l = l;
        }

        @Override
        public TwoPointer<Integer> moveLeft(Integer value) {
            return new CustomTwoPointer(left + 1, right, result - value, k, l);
        }

        @Override
        public TwoPointer<Integer> moveRight(Integer value) {
            return new CustomTwoPointer(left, right+1, result + value, k, l);
        }

        @Override
        public boolean isMoveLeft(Integer leftValue, Integer rightValue) {
            return left <= right && this.result + rightValue > k;
        }

        @Override
        boolean isAccrual() {
            return this.result == k;
        }

        @Override
        public boolean isBetterThan(TwoPointer<Integer> than) {
            return this.getLength() > than.getLength();
        }
    }


    public static abstract class TwoPointer<T> {
        protected int left;
        protected int right;
        protected int l;

        // 이동
        public TwoPointer<T> move(T leftValue, T rightValue) {
            if (isMoveLeft(leftValue, rightValue)) {
                return moveLeft(leftValue);
            } else {
                return moveRight(rightValue);
            }
        }

        // 현재 해당하는 왼쪽 윈도우 값 찾기
        public T getLeftValue(List<T> array) {
            return array.get(left);
        }

        // 현재 해당하는 오른쪽 윈도우 오른쪽 값 찾기
        public T getRightValue(List<T> array) {
            return array.get(right + 1);
        }

        // 현재 윈도우 위치 찾기
        public int[] getPosition() {
            return new int[]{left, right};
        }

        // 윈도우 길이 확인
        public int getLength() {
            return this.right - this.left;
        }

        // 종료 확인
        public boolean isEnd() {
            return this.right + 1 == this.l;
        }

        // 왼쪽 윈도우 이동
        abstract TwoPointer<T> moveLeft(T value);

        // 오른쪽 윈도우 이동
        abstract TwoPointer<T> moveRight(T value);

        // 왼쪽 윈도우 이동 가능한 지 체크
        abstract boolean isMoveLeft(T leftValue, T rightValue);

        // 원하는 시점을 찾는 메서드
        abstract boolean isAccrual();

        // 두 객체 비교
        abstract boolean isBetterThan(TwoPointer<T> than);
    }
}
