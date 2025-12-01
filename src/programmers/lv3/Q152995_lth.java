
package programmers.lv3;

/* 프로그래머스 152995번 인사고과 문제

[문제 풀이]
0번쨰를 제외하고 근무태도 기준으로 정렬후 첫번째랑 0번쨰 비교해서 -1인지 체크
첫번쨰가[4,3]이라 치면 다음에 오는 것이 두 점수가 모두 낮은지 체크해서 낮지 않은 경우에만 새 배열에 
다음오는 것이 [3,4]라면 앞으로 오는 것은 4보다 작기에 비교대상을 뒷 숫자가 더 큰 3,4로 변경해서 비교 실시 변경될때마다 0번쨰와 비교해서 -1이 되는지 체크
인센티브를 받는 사람들의 배열이 되었다면 두값을 더해서 다시 정렬 후 0번째가 몇번쨰인지 체크
-> 22번 테케에서 실패를 하는데 인사 고과: 7 6 6 동료 평가: 5 6 4 이런경우에서 실패를 한다고 한다
-> 고치려면 동료평가를 오름차순으로 바꾸거나 best를 여러개 만들어서 비교해야할거 같다 
Pareto frontier(지배되지 않는 경계선)이라는 그래프를 보면 best가 여러개여야 할것 같아 BestList로 두고 똑같이 내림차순정렬로 풀어보았다
-> 아직도 실패해서 동료평가를 오름차순으로 변경도 같이 적용했다

*/

import java.util.*;

class Q152995_lth {
    public int solution(int[][] scores) {

        int[] wanho = scores[0];
        int wanhoSum = wanho[0] + wanho[1];

        // 정렬: 근태 내림차순, 실적 오름차순
        Arrays.sort(scores, (a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0];
            return a[1] - b[1];
        });

        int maxPerf = 0;  // Pareto frontier 동평
        int rank = 1;     // 완호 등수: 자기보다 sum 높은 사람 수 + 1

        for (int[] s : scores) {

            // 완호가 지배당하면 -1
            if (s[0] > wanho[0] && s[1] > wanho[1]) {
                return -1;
            }

            // 현재 사람이 지배당하는지 확인
            // 동평 < maxPerf라면 이미 앞에서 나온 사람이 지배함
            if (s[1] < maxPerf) {
                continue;
            }

            // 지배당하지 않으면 frontier 확장
            maxPerf = s[1];

            // 완호보다 점수 합이 큰 사람의 수를 센다
            if (s[0] + s[1] > wanhoSum) {
                rank++;
            }
        }

        return rank;
    }
}
