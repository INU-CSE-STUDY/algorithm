package programmers.lv2;

/* 프로그래머스 42842번 카펫 문제

[문제 풀이]
세로의 최소는 3이니까 3부터 1씩 늘려가며 정답과 맞는지 비교

*/




class Q42842_lth {
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow; 

        for (int height = 3; height <= Math.sqrt(total); height++) {
            // 나눠지지 않는 경우는 패스
            if (total % height != 0) continue;

            int width = total / height;

            // 노란 격자의 수가 맞는지 확인
            if ((width - 2) * (height - 2) == yellow) {
                return new int[]{width, height};
            }
        }

        return new int[0];
    }
}