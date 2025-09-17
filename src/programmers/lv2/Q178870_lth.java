package programmers.lv2;

/* 프로그래머스 178870번 연속된 부분 수열의 합 문제

[문제 풀이]
배열을 순회하면서 sum이 k보다 작으면 오른쪽 포인터를 늘리고, sum이 k보다 크면 왼쪽 포인터를 늘림
객체를 만들어서 수열의 시작, 끝, 길이를 저장하고 더 좋은 수열이 나오면 갱신
오른쪽 포인터만 써서 i부터 j까지의 합을 구하는 방식으로 했다가 시간복잡도문제로 수정

*/  

import java.util.*;

class Q178870_lth {
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        int left = 0;
        int sum = 0;

        Arrangement best = null;

        // 오른쪽 포인터를 움직이면서 합을 구함
        for (int right = 0; right < n; right++) {
            sum += sequence[right];

            // sum이 k보다 크면 왼쪽 포인터를 움직여서 sum을 줄임
            while (sum > k && left <= right) {
                sum -= sequence[left++];
            }

            // sum이 k와 같으면 결과를 갱신
            if (sum == k) {
                Arrangement recent = new Arrangement(left, right);
                if (best == null || recent.isBetterThan(best)) {
                    best = recent;
                }
            }
        }
        
        return new int[]{best.s, best.e};
    }
    
    //수열 객체 생성
    class Arrangement{
        int s;
        int e;
        int l;
        public Arrangement(int s, int e){
            this.s = s;
            this.e = e;
            this.l = e - s;
        }
        
        //더 좋은 수열인지 확인
        boolean isBetterThan(Arrangement best) {
            if (this.l != best.l){
                return this.l < best.l;
            } 
            return this.s < best.s;
        }
    }
}