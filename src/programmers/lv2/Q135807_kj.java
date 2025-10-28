package programmers.lv2;

/* 프로그래머스 135807번 숫자 카드 나누기 문제

[문제 풀이]
마음 같아서는! 그냥 완전 탐색하고 싶지만...ㅎㅎ 제한사항 보면 당연히 안될것이다.

각 배열 별로 최대공약수를 구하고(자기가 가진 카드들에 적힌 모든 숫자를 나눌 수 있어야 함), 그 최대공약수가 남의 카드를 못 나눠야 함!
- 둘 다 문제 조건을 만족할 경우, Math.max로 최댓값 구하면 됨

*/

class Q135807_kj {
    public int solution(int[] arrayA, int[] arrayB) {
        return getAnswer(arrayA, arrayB);
    }

    private int getAnswer(int[] arrayA, int[] arrayB) {
        int answer = 0;

        int gcdA = getArrayGCD(arrayA); // arrayA의 최대공약수
        int gcdB = getArrayGCD(arrayB); // arrayB의 최대공약수

        // 상대방 배열의 최대공약수로 나눌 수 있는지 여부 확인, 나눌 수 없다면 최댓값을 저장
        if (!canDivide(arrayA, gcdB)) answer = Math.max(answer, gcdB);
        if (!canDivide(arrayB, gcdA)) answer = Math.max(answer, gcdA);

        return answer;
    }

    private int getArrayGCD(int[] array) {
        int resultGCD = array[0];

        for (int i = 1; i < array.length; i++) {
            resultGCD = getGCD(resultGCD, array[i]);
        }

        return resultGCD;
    }

    private int getGCD(int a, int b) {

        // 유클리드 호제법
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    private boolean canDivide(int[] array, int divideNum) {

        for (int num : array) {
            // 상대방의 최대공약수로 나눠지는 경우
            if (num % divideNum == 0) return true;
        }

        // 상대방의 최대공약수로 나눠지지 않는 경우
        return false;
    }
}