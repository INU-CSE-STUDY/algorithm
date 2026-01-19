package programmers.lv2;

/* 프로그래머스 42860번 조이스틱 문제

[문제 풀이]

A 0 / B 1 / C 2 / D 3 / E 4 / F 5 / G 6 / H 7 / I 8 / J 9 / K 10 / L 11 / M 12 / N 13
O 12 / P 11 / Q 10 / R 9 / S 8 / T 7 / U 6 / V 5 / W 4 / X 3 / Y 2 / Z 1
-> 생각해보니 이건 필요없네 ㅋㅋ 아스키코드 빼기 연산으로 min 값 쓰면 되고..

JAN -> 9번 조작해서 J + 왼쪽으로 한 칸 이동 + 13번 조작해서 만들기 = 23

JEROEN -> 9 + 1 + 4 + 1 + 9 + 1 + 12 + 1 + 4 + 1 + 13 = 10 + 5 + 10 + 13 + 5 + 13 = 30 + 26 + 56

JEROEN같은 경우에는 그냥 오른쪽으로 쭉 가는게 이득인데, JAN처럼 중간에 A가 있을 경우 그냥 역방향으로 가는게 이득인 경우가 존재함,,
각각의 위치마다 A가 연속으로 몇 개 있는지 세서 역방향으로 가는게 이득인지 정방향으로 가는게 이득인지 세야 하는 문제

*/

class Q42860_kj {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();

        int move = length - 1;
        for (int i = 0; i < length; i++) {

            char nowAlphabet = name.charAt(i); // 현재 위치의 알파벳
            answer += changeValue(nowAlphabet);

            int nextIndex = i + 1;
            while (nextIndex < length && name.charAt(nextIndex) == 'A') {
                nextIndex++;
            }

            move = Math.min(move, (i * 2) + (length - nextIndex));
            move = Math.min(move, (length - nextIndex) * 2 + i);
        }


        return answer + move;
    }

    private int changeValue(char alphabet) {

        // A에서 정방향으로 갔을 때 또는 역방향으로 갔을 때 최소 가중치인 방향으로 조이스틱 이동
        // A -> Z로 가는데 가중치 1이 소모되므로 Z - 알파벳은 +1을 해줘야 함
        return Math.min(alphabet - 'A', 'Z' - alphabet + 1);
    }
}