package programmers.lv2;

/* 프로그래머스 181188번 요격 시스템 문제

[문제 풀이]
1. 2차원 배열
2. 미사일은 x축에 평행한 직선 형태의 모양이며, (s, e) 형태로 표현
3. 우리는 특정 x 좌표에서 y축에 수평이 되도록 미사일 발사(해당 x좌표에 걸쳐있는 모든 미사일을 한번에 요격 가능)
- (s, e)일 경우 s 또는 e에서 발사하는 미사일로는 요격 불가능 = 그 사이값으로만 가능
- 요격 미사일은 실수 x 좌표에서 가능

드는 생각은.. targets 배열을 일단 정렬한 다음에, s랑 e 비교해가면서 겹치는 구간 있으면 넘어가고 없으면 정답 한 개 증가시킨 다음에 e 지점 바꿔서 하고.. 그런 식으로 하면 안되나?
*/
import java.util.*;

class Q181188_kj {
    public int solution(int[][] targets) {
        Arrays.sort(targets,
                Comparator.comparingInt((int[] a) -> a[1])
        );

        int answer = 0;
        int pos = 0;
        for (int i = 0; i < targets.length; i++) {
            int nextS = targets[i][0];
            int nextE = targets[i][1];

            // 새로운 폭격 미사일의 시작지점이 현재 끝지점과 같거나 큰 경우, 현재 요격 미사일로는 요격 불가
            if (nextS >= pos) {
                answer++; // 새로운 미사일 추가
                pos = nextE; // 새로운 미사일이 쏠 쑤 있는 최댓값은 폭격 미사일의 e보다 작은 실수값
            }
        }

        return answer;
    }
}