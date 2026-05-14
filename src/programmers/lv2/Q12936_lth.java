import java.util.*;

class Q12936_lth {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> list = new ArrayList<>();
        
        // 1 ~ n 초기화
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        k--; // 0-based index로 변환

        for (int i = 0; i < n; i++) {
            long fact = 1;
            for (int j = 1; j < n - i; j++) {
                fact *= j;
            }

            int idx = (int)(k / fact);
            answer[i] = list.get(idx);
            list.remove(idx);

            k %= fact;
        }

        return answer;
    }
}