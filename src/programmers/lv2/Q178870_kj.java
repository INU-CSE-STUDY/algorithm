package programmers.lv2;

/* 프로그래머스 178870번 연속된 부분 수열의 합 문제

[문제 풀이]
투포인터 알고리즘 사용하기 좋아보이는 문제 b

*/

class Q178870_kj {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        TwoPointer twoPointer = new TwoPointer(k);

        while (twoPointer.right < sequence.length) {
            if (twoPointer.isSame(sequence)) {
                answer[0] = twoPointer.left;
                answer[1] = twoPointer.right;
            } else {
                twoPointer.isSmall(sequence);
                twoPointer.isLarge(sequence);
            }
        }

        return answer;
    }
}

class TwoPointer {
    int left;
    int right;
    int length;
    int sum;
    int target;

    public TwoPointer(int k) {
        this.left = 0;
        this.right = -1;
        this.length = Integer.MAX_VALUE;
        this.sum = 0;
        this.target = k;
    }

    public void isSmall(int[] sequence) {
        if (this.sum < this.target) {
            if (++this.right < sequence.length) {
                this.sum += sequence[this.right];
            }
        }
    }

    public void isLarge(int[] sequence) {
        if (this.sum > this.target) {
            this.sum -= sequence[this.left++];
        }
    }

    public boolean isSame(int[] sequence) {
        if (this.sum == this.target) {
            int nowLength = this.right - this.left + 1;

            if (this.length > nowLength) {
                this.length = nowLength;
                return true;
            }

            this.sum -= sequence[this.left++];

            return false;
        }

        return false;
    }
}