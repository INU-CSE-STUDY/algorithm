package programmers.lv2;

/* 프로그래머스 250135번 아날로그 시계 문제

[문제 풀이]
시침: 12시간 * 60분 * 60초 = 43,200초마다 한바퀴
- 1초에 1/120도(360 / (12 * 60 * 60) = 1/120)
분침: 60분 * 60초 = 3,600초마다 한바퀴
- 1초에 1/10도 (360 / (60 * 60) = 1/10)
초침: 60초 = 60초마다 한바퀴
- 1초에 6도 (360 / 60)

1초씩 움직이면서 둘이 일치하는지 찾으면 되나....
한바퀴 돌면 다시 0부터 시작하고
한바퀴(360도) 돌려면 대략 1초에 몇도씩 돌아야하는진 아는데.. 이게 딱 떨어지는 숫자가 아닐텐데 그렇게 계산해도 되나?
아무리 생각해도 이거 아닌데.. 0시 6분 0.501초에 만나는지 어떻게 알건데...

---
당연한거지만 위에 써둔 것처럼 1초에 몇도씩 도느냐 이거 자체로는 0시 6분 0.501초에 만났다 이런 사실을 절대 알지 못함..
1초에 한번씩 돌았기 때문임.. 당연함.. 나는 1초씩 휙휙 늘리니까 단순 연산 자체로는 저 0.501초를 만들어낼 수 없음
그렇기 때문에 내 풀이가 틀렸던 것이었음

1초씩 휙휙 증가하면서 겹치는지 알 수 있는 방법은
시침/분침보다 초침이 뒤에 있었는데 1초 지나니까 앞에 있음 또는 시침/분침보다 초침이 앞에 있었는데 1초 지나니까 뒤에 있음
이 상황이라고 생각할 수 있다..
근데 시침/분침보다 초침이 앞에 있는 경우에 1초 지났다고 초침이 둘보다 뒤로 가는 경우가 있나? 시침 분침은 1초에 0.xx도씩 움직이는데 초침은 6도씩 움직이잖아.. 뒤에 경우는 없는 경우다; 무조건 초침이 뒤에 있다가 시침/분침을 앞질러가는 경우밖에 없다.

그냥 단순히 현재와 미래를 비교해서 변동사항 있으면 더해주는 방식으로 해보자 ..ㅎㅎ
그리고 예외상황인 12시 정각(00시와 12시)만 고려해주면 된당..! (43200초마다 12시 정각되니까 그거로 고려하면 됨.)

++) 각도가 넘어가서 0도가 되는 순간은 잘못 체크할 수도 있으니 그거 따로 생각
*/

class Q250135_kj {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

        int start = h1 * 60 * 60 + m1 * 60 + s1;
        int end = h2 * 60 * 60 + m2 * 60 + s2;
        int answer = getAlarmCnt(start, end);

        return answer;
    }

    private int getAlarmCnt(int start, int end) {
        int answer = 0;

        if (start % 43200 == 0) answer++;

        while (start < end) {

            double hDegree = (start % 43200) * (360.0 / 43200);
            double mDegree = (start % 3600) * (360.0 / 3600);
            double sDegree = (start % 60) * (360.0 / 60);

            double nextHDegree = ((start + 1) % 43200) * (360.0 / 43200);
            double nextMDegree = ((start + 1) % 3600) * (360.0 / 3600);
            double nextSDegree = ((start + 1) % 60) * (360.0 / 60);

            if (nextHDegree == 0) nextHDegree = 360;
            if (nextMDegree == 0) nextMDegree = 360;
            if (nextSDegree == 0) nextSDegree = 360;

            if (sDegree < hDegree && nextSDegree >= nextHDegree) answer++;
            if (sDegree < mDegree && nextSDegree >= nextMDegree) answer++;

            // 12시 정각인 경우는 빼주기
            if ((start + 1) % 43200 == 0) answer--;

            start++;
        }

        return answer;
    }
}