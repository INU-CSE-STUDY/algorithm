package programmers.lv2;

/* 프로그래머스 77885번 2개 이하로 다른 비트

[문제 풀이]
일단 2진수로 바꾸는건 Long.toString으로 가능! 10^15을 넣었을 때도 정상적으로 2진수 문자열로 변환이 됨.

주어진 수보다 큰 수 중에서 비트가 다른 지점이 2개 이하이면서 제일 작은 수는 어떻게 구할까?
- 단순히 생각해보면 직접 비트 하나하나 비교한다거나, XOR 연산 등을 통해서 구할 수는 있지만 이렇게 하면 시간초과가 발생한다.

1. 주어진 수가 짝수일 때
- 2(10), 4(100), 6(110), ... 처럼 맨 뒤에 1을 나타내는 비트가 항상 0임
  => 가장 마지막 비트가 1이 되면 주어진 수보다 큰 수 중에 제일 작으면서 비트가 다른 지점이 2개 이하(1개)가 되는 수가 됨!
     ∴ 짝수일 때는 + 1한 값을 반환하면 된다.
2. 주어진 수가 홀수일 때
- 1(1), 3(11), 5(101), 7(111), 9(1001), ...
  => 얘는 가장 마지막 비트가 1이기 때문에 +1을 해버리면 정말 많은 비트가 바뀔 가능성이 있음.. 우수수 바뀜(7 같은거 봐도 111 -> 1000 되기 때문에 4개나 달라짐)
     그렇기 때문에 가장 뒤에 있는 "01" 부분에 1을 더해서 "10"으로 바꿔버리면 비트가 다른 지점이 2개 이하이면서 제일 작은 수가 될 수 있음

*/

class Q77885_kj {

    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            answer[i] = getAnswer(numbers[i]);
        }

        return answer;
    }

    private long getAnswer(long number) {

        // 짝수일 경우, +1한 값이 항상 비트가 다른 지점이 2개 이하이면서, 제일 작은 수가 됨
        if (number % 2 == 0) return number + 1;

        // 홀수일 경우, 가장 뒤에 있는 01 을 10 으로 변경하면 비트가 다른 지점이 2개 이하이면서 제일 작은 수가 됨
        String binaryStr = "0" + Long.toString(number, 2); // 7처럼 0이 포함되지 않을 수도 있기 때문에 가장 앞자리에 0을 추가해주기

        int index = binaryStr.lastIndexOf("01");
        String answerStr = binaryStr.substring(0, index) + "10" + binaryStr.substring(index + 2);
        return Long.parseLong(answerStr, 2);
    }
}
