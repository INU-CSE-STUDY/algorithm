package programmers.lv2;

/* 프로그래머스 42860번 조이스틱 문제

[문제 풀이]
각 알파벳은 그냥 A에서부터의 거리와 Z에서부터의 거리 중 더 작은 값으로 바꾸면 됨
위치는 그냥 처음부터 끝까지 가는 경우와 중간에 A가 연속으로 나오는 구간을 발견했을 때
그 구간을 피해서 돌아가는 경우를 비교해서 더 작은 값을 선택하면 됨
*/

class Q42860_lth {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        
        for (int i = 0; i < len; i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);
        }

        int move = len - 1; // 오른쪽만 이동한 기본상태

        for (int i = 0; i < len; i++) {
            int j = i + 1;

            // 연속된 A 찾기
            while (j < len && name.charAt(j) == 'A') {
                j++;
            }

            // 오른쪽에서 왼쪽
            move = Math.min(move, i * 2 + (len - j));

            // 왼쪽에서 오른쪽
            move = Math.min(move, (len - j) * 2 + i);
        }

        return answer + move;
    }
}
