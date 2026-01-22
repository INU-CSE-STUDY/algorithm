package programmers.lv2;

/* 프로그래머스 42842번 카펫 문제

[문제 풀이]
가로 길이 >= 세로 길이

완전탐색 문제
1) brown + yellow 해서 나오는 격자 크기 기준으로 가능한 가로/세로 구하기
2) 해당 가로/세로 크기의 사각형에서 갈색 격자의 수가 입력과 동일한지 확인하기
   - 입력과 동일하지 않다면 다음 탐색 진행
   - 입력과 동일하다면 break 후 반환

*/

class Q42842_kj {
    public int[] solution(int brown, int yellow) {

        int size = brown + yellow; // 전체 격자의 크기

        int width = 0, height = 0;
        for (height = 2; height <= Math.sqrt(size); height++) {
            // 가로 길이가 세로 길이보다 기니까, 세로 길이 기준으로 나눠보기

            if (size % height != 0) continue; // 나누어지지 않는 수라면 확인할 필요 없음

            // 나누어지는 경우, 해당 가로/세로 조합으로 갈색 격자 수가 제대로 나오는지 확인
            width = size / height;

            int possibleBrownCount = width * 2 + (height - 2) * 2;
            if (possibleBrownCount == brown) break;
        }

        return new int[]{ width, height };
    }
}
