package programmers.lv2;

/* 프로그래머스 118667번 두 큐 합 같게 만들기 문제

[문제 풀이]

1. 문제에서 pop, insert 등 연산을 해야 하는데 배열로 하면 너무 어려울 거 같아서 일단 queue로 변경
2. 각 큐의 합 구하기
   - 목표로 하는 값도 구할 수 있음
3. 두 큐의 합을 비교해서 작은 거에 하나씩 추가해야 함! 그래야 목표치에 가까워질 수 있으니까..
4. 시도횟수가 일정 횟수 이상이 되면 원소의 합을 같게 만들 수 없음
   - q2의 원소가 전부 q1으로 넘어갔다가, q1의 원소가 전부 q2로 넘어가는 경우부터 안되는거같음.. (큐의 길이 * 3)

*/

import java.util.*;

class Q118667_kj {

    int length; // 가장 처음 queue의 크기
    Queue<Integer> q1, q2;

    public int solution(int[] queue1, int[] queue2) {

        length = queue1.length; // 시도횟수가 length * 2가 넘어가면 불가능한 경우
        initQueue(queue1, queue2); // 배열을 큐로 변경 (직접 offer, poll 작업을 할 예정)

        // 각 큐의 원소 합과 목표로 하는 합 구하기
        long q1Sum = getSum(q1);
        long q2Sum = getSum(q2);
        long targetNum = (q1Sum + q2Sum) / 2;

        int tryCnt = 0;
        int moveNum = 0;
        while (tryCnt <= length * 3) {

            if (q1Sum == q2Sum) {
                // 두 큐의 합이 같다면 return
                return tryCnt;
            } else if (q1Sum < q2Sum) {
                // q1의 합이 q2의 합보다 작은 경우
                moveNum = q2.poll();
                q1.offer(moveNum);

                q1Sum += moveNum;
                q2Sum -= moveNum;
            } else {
                // q2의 합이 q1의 합보다 작은 경우
                moveNum = q1.poll();
                q2.offer(moveNum);

                q1Sum -= moveNum;
                q2Sum += moveNum;
            }

            tryCnt++;
        }

        // 일정 횟수를 넘겼음에도 두 큐의 합이 같지 않다면 불가능한 경우임
        return -1;
    }

    private void initQueue(int[] queue1, int[] queue2) {

        q1 = new LinkedList<>();
        q2 = new LinkedList<>();

        for (int i = 0; i < length; i++) {

            int q1Num = queue1[i];
            int q2Num = queue2[i];

            q1.offer(q1Num);
            q2.offer(q2Num);
        }
    }

    private long getSum(Queue<Integer> queue) {
        long sum = 0;

        int length = queue.size();
        for (int i = 0; i < length; i++) {
            int num = queue.poll();
            sum += num;
            queue.offer(num);
        }

        return sum;
    }
}