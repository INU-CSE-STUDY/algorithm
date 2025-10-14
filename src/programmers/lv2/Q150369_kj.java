package programmers.lv2;

/* 프로그래머스 150369번 택배 배달과 수거하기 문제

우선순위 큐?? 이런 거로 되나??
택배 보내는 건 다 챙겨서 짝은 집마다 다 던져주고, 수거하는건 많은 집부터 우르르 수거해야 하니까?

1 0 / 0 3 / 3 0 / 1 4 / 2 0
4개 다 배달하면
0 0 / 0 3 / 3 0 / 0 4 / 0 0 -> 5만큼 소요 / 4개 배달
0 0 / 0 3 / 3 0 / 0 0 / 0 0 -> 5만큼 소요 / 4개 수거
0 0 / 0 3 / 0 0 / .. -> 3만큼 소요 / 3개 배달
0 0 / 0 0 / 0 0 / .. -> 3만큼 소요 / 3개 수거

이런 식이 최저 거리니까, 택배 보낼 때든 수거할 때든 제일 먼 집까지 가는게 목표

그러면 굳이 큐를 쓸 필요가 없지 않나? 어차피 배달 할 때는 꽉채우든 안채우든 일단 갈 수 있는 곳 다 들리면서 끝까지 갈 거고, 돌아갈 때는 무조건 택배 상자는 다 비우고 갈 거니까 수거도 꽉 채워서 들고오면 됨

그러면 그냥 뒤에서부터 탐색하면서?! 가져오는거 들고가는거 개수만 신경써주기??
*/

class Q150369_kj {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        for (int i = deliveries.length - 1; i >= 0;) {

            if (deliveries[i] == 0 && pickups[i] == 0) {
                // 배달과 수거 둘 다 할 필요 없는 집이라면 넘어가기
                i--;
                continue;
            }

            calculateCap(deliveries, cap, i);
            calculateCap(pickups, cap, i);

            // 왔다갔다 하는데 어차피 배달 간 거리만큼 돌아오게 되어있음
            answer += (i + 1) * 2;
        }

        return answer;
    }

    private void calculateCap(int[] item, int cap, int index) {
        while (index >= 0) {
            if (cap - item[index] >= 0) {
                cap -= item[index];
                item[index] = 0;
                index--;
            } else {
                item[index] -= cap;
                break;
            }
        }
    }
}