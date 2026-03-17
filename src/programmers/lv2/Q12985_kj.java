package programmers.lv2;

/* 프로그래머스 12985번 예상 대진표 문제

[문제 풀이]
1 2 / 3 4 / 5 6 / 7 8
1 "2" / 3 "4"
"1" "2"
이런 식으로 만나니까 Math.round(현재 번호 / 2) 해서 반올림 처리하면 다음 라운드 때 할당받는 번호가 나오지 않나?
이렇게 라운드 계속 진행하면서 2로 나눴을 때 결과가 똑같아질 때가 이미 마지막 라운드 끝났을 경우니까
0부터 시작해서 하나씩 증가시켜나가면 마지막 라운드 끝났을 경우 때랑 딱 맞음!

*/

class Q12985_kj {
    public int solution(int n, int a, int b) {
        int answer = 0;

        while (a != b) {

            int nextA = (int) Math.round(a / 2.0);
            int nextB = (int) Math.round(b / 2.0);

            a = nextA;
            b = nextB;

            answer++;
        }

        return answer;
    }
}
