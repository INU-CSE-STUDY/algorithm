package programmers.lv2;

/* 프로그래머스 181187번 두 원 사이의 정수 쌍 문제

[문제 풀이]
수학문제이다. 원의 방정식을 풀면 답이 나온다.
올림과 내림이 헷갈리는데 그려보면 이해가 된다.
*/  

class Q181187_lth {
    public long solution(int r1, int r2) {
        long answer = 0;

        Circle inner = new Circle(r1);
        Circle outer = new Circle(r2);

        //한 사분면
        for (int x = 1; x <= r2; x++) {
            // 큰 원에서 가능한 최대 y
            long maxY = outer.maxY(x);
            // 작은 원에서 가능한 최소 y
            long minY = inner.minY(x);

            //끝점을 포함시킴
            answer += (maxY - minY + 1);
        }

        //한 사분면 * 4
        return answer * 4;
    }

    // 원 클래스
    class Circle {
        int r;

        public Circle(int r) {
            this.r = r;
        }

        // x좌표에 대해 가능한 최대 y (내림)
        public long maxY(int x) {
            return (long)Math.floor(Math.sqrt((long)r * r - (long)x * x));
        }

        // x좌표에 대해 가능한 최소 y (올림) 
        public long minY(int x) {
            return (long)Math.ceil(Math.sqrt((long)r * r - (long)x * x));
        }
    }
}
