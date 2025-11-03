package programmers.lv3;

/* 프로그래머스 214289번 에어컨 문제

[문제 풀이]
1. 승객이 탑승하지 않았을 경우, 실내공조 제어 시스템(t1 ~ t2 사이의 온도 유지)이 동작하지 않음
2. 에어컨 ON/OFF 상태에 따라 목표 온도가 다름
   - 에어컨 ON
     1) 희망 온도를 원하는 값으로 변경 가능
     2) 실내 온도와 희망 온도가 다른 경우, 같아지는 방향으로 1도 상승 또는 하강하며 전력 a만큼 소비
     3) 실내 온도와 희망 온도가 같을 경우, 실내 온도가 변하지 않으며 전력 b만큼 소비
   - 에어컨 OFF
     1) 전력을 소비하지 않음
     2) 실내 온도와 실외 온도가 다를 경우, 같아지는 방향으로 1도 상승 또는 하강
     3) 실내 온도와 실외 온도가 같을 경우, 실내 온도가 변하지 않음

2차원 DP 배열 생성
- DP[i][j] = i 분에 j 온도가 되는데 필요한 전력량
  j 온도를 맞춰야 하는데 온도의 최솟값은 -10이므로 10을 더해줘야 배열에서 정상적으로 사용할 수 있음(arr[-10] 이런건 안되니까)
  최솟값을 찾아야 하므로, 가능한 배열 내부의 값은 전력량 최대로 초기화
  현재(0분) 실내 온도 = 실외 온도이므로 DP[0][현재 온도] = 0(현재는 전력량이 0인 시점)

DP 배열 관리
- 승객이 탑승 중인 시간에 쾌적한 실내 온도를 유지하는 것이 불가능한 경우는 주어지지 않음
  = 승객이 탑승 중인 시간엔 무조건 쾌적한 실내 온도를 유지해야 함
- 아래 케이스에서 최솟값인 경우를 만족해야 함
  - 에어컨 ON의 경우
    1) 희망 온도 = 실내 온도 -> 온도 그대로 + 전력 b 소모
    2) 희망 온도 < 실내 온도 -> 온도 -1 + 전력 a 소모
    3) 희망 온도 > 실내 온도 -> 온도 +1 + 전력 a 소모
  - 에어컨 OFF의 경우
    1) 실외 온도 = 실내 온도 -> 온도 그대로
    2) 실외 온도 < 실내 온도 -> 온도 -1
    3) 실외 온도 > 실내 온도 -> 온도 +1
*/

import java.util.*;

class Q214289_kj {

    static final int MAX = 100 * 1000; // 온도를 맞추기 위해 필요한 최대 전력
    static final int BASE = 10; // 배열 인덱스 맞추기용

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {

        // DP 배열으로 만들어서 사용해야 하는데 온도 최솟값이 -10이므로 일정값을 더해줘야 함!
        temperature += BASE;
        t1 += BASE;
        t2 += BASE;

        int[][] DP = new int[onboard.length][51];
        for (int i = 0; i < onboard.length; i++) {
            Arrays.fill(DP[i], MAX);
        }

        DP[0][temperature] = 0; // 시작 온도 = 실외 온도
        for (int i = 0; i < DP.length - 1; i++) {
            for (int j = 0; j < 51; j++) {

                // 승객이 탑승 중인 시간에 쾌적한 실내 온도를 유지하지 못하는 경우는 불가능한 경우이므로 continue
                if (onboard[i] == 1 && (j < t1 || t2 < j)) continue;

                // 에어컨 ON
                DP[i + 1][j] = Math.min(DP[i + 1][j], DP[i][j] + b); // 희망 온도 = 실내 온도
                if (j > 0) {
                    // 희망 온도 < 실내 온도 && 인덱스 범위 넘어가지 않음
                    DP[i + 1][j - 1] = Math.min(DP[i + 1][j - 1], DP[i][j] + a);
                }
                if (j < 50) {
                    // 희망 온도 > 실내 온도 && 인덱스 범위 넘어가지 않음
                    DP[i + 1][j + 1] = Math.min(DP[i + 1][j + 1], DP[i][j] + a);
                }

                // 에어컨 OFF
                if (temperature == j) {
                    // 실외 온도 = 실내 온도
                    DP[i + 1][j] = Math.min(DP[i + 1][j], DP[i][j]);
                }
                if (temperature < j) {
                    // 실외 온도 < 실내 온도
                    DP[i + 1][j - 1] = Math.min(DP[i + 1][j - 1], DP[i][j]);
                }
                if (temperature > j && j < 50) {
                    // 실외 온도 > 실내 온도 && 인덱스 범위 넘어가지 않음
                    DP[i + 1][j + 1] = Math.min(DP[i + 1][j + 1], DP[i][j]);
                }
            }
        }

        int answer = MAX;
        int last = onboard.length - 1;
        for (int temp = 0; temp < 51; temp++) {
            // 마지막이 승객이 탑승 중인 시간이라면 쾌적한 실내 온도를 유지하는지 확인 필요
            if (onboard[last] == 1 && (temp < t1 || t2 < temp)) continue;

            answer = Math.min(answer, DP[last][temp]);
        }

        return answer;
    }
}
