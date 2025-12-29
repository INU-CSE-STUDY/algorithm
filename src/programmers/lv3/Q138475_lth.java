package programmers.lv3;

/* 프로그래머스 138475번 억억단을 외우자 문제

[문제 풀이]
처음 든 생각이 곱셈으로 e까지 각각 몇번 나오는 지 카운트 하는거였는데 너무 오래걸릴거 같아서
약수의 개수를 세는 걸로 바꿈 뒤에서부터 누적 최댓값을 저장하는 이유는 계산을 s 계산을 한 번만 하기 위해서다
cnt배열과 best배열에서 인덱스를 0을 사용하지 않도록 e+1크기로 잡아주었다
*/


import java.util.*;

class Q138475_lth {
    public int[] solution(int e, int[] starts) {
        // 약수 개수 계산
        int[] cnt = new int[e + 1];
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                cnt[j]++;
            }
        }

        // 뒤에서부터 최대 약수 개수 누적
        int[] best = new int[e + 2];
        best[e] = e;

        for (int i = e - 1; i >= 1; i--) {
            if (cnt[i] >= cnt[best[i + 1]]) {
                best[i] = i;
            } else {
                best[i] = best[i + 1];
            }
        }

        // s 계산
        int[] answer = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            answer[i] = best[starts[i]];
        }

        return answer;
    }
}
