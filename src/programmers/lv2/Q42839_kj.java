package programmers.lv2;

/* 프로그래머스 42839번 소수 찾기 문제

[문제 풀이]
주어진 숫자 조합으로(순열) 가능한 숫자 조합 만들고
그거 isPrime 함수로 소수인지 체크하기!

*/

import java.util.*;

class Q42839_kj {
    public int solution(String numbers) {

        Set<Integer> numberSet = new HashSet<>(); // 같은 숫자가 중복으로 생성될 수 있으므로 Set에 저장
        initNumberSet(numberSet, numbers.split(""));

        return getPrimeNumberCount(numberSet);
    }

    private void initNumberSet(Set<Integer> numberSet, String[] numberList) {

        boolean[] visited = new boolean[numberList.length];

        for (int length = 1; length <= numberList.length; length++) {
            permutation(numberSet, numberList, visited, 0, length, new StringBuilder());
        }
    }

    private void permutation(Set<Integer> numberSet, String[] numberList, boolean[] visited, int depth, int length, StringBuilder numberPermutationStr) {

        if (depth == length) {
            numberSet.add(Integer.parseInt(numberPermutationStr.toString()));
            return;
        }

        for (int i = 0; i < numberList.length; i++) {

            if (!visited[i]) {

                // 방문 처리 및 숫자 추가 후, 계속 뽑기
                visited[i] = true;
                numberPermutationStr.append(numberList[i]);
                permutation(numberSet, numberList, visited, depth + 1, length, numberPermutationStr);

                // 뽑기가 끝나면 다음 반복을 위해 문자 제거 및 방문 처리 해제
                numberPermutationStr.deleteCharAt(numberPermutationStr.length() - 1);
                visited[i] = false;
            }
        }
    }

    private int getPrimeNumberCount(Set<Integer> numberSet) {

        int count = 0;
        for (int number : numberSet) {

            if (isPrime(number)) count++;
        }

        return count;
    }

    private boolean isPrime(int number) {

        if (number < 2) return false; // 2 미만은 소수 X

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false; // 1과 자기자신 이외에 나뉘어지는 수가 있을 경우 소수 X
        }

        return true; // 1과 자기자신 이외에 나뉘어지는 수가 없으면 소수 O
    }
}
