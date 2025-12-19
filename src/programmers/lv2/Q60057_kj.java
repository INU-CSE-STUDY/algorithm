package programmers.lv2;

/* 프로그래머스 60057번 문자열 압축 문제

[문제 풀이]
s 길이가 되게 작아서.. 완전탐색으로 가능할 거 같음

압축길이 딱 절반까지 반복해야 유의미한 압축이 되니까 절반 길이까지 압축시도하고!
그 내부에서는 그냥 단순 비교로 비교해줬다.
이거 뒤에 문자열 남는 경우를 어떻게 처리할 지 고민이 많았는데.. 그냥 그 다음 인덱스 어디인지 체크해서 반복문 끝났는데 문자열 길이랑 같지 않을 때(딱딱 들어맞는 경우에는 nextIdx = length임) 남는 문자열 그냥 뒤에 이어붙여주는 방식으로 했다.

딱 테케 5번만 틀려서 질문하기 보니까,, s가 1일 때 예외처리가 안되어있다는 것이다.
왜냐면 내 코드 상으로 answer은 MAX_VALUE니까,, s가 1일때는 걍 MAX_VALUE가 나와버려서이다. 이런걸 생각할 줄 알아야하는데 ㅠㅠ
*/

class Q60057_kj {
    public int solution(String s) {

        // s가 한글자일 때는 이러나저러나 무조건 길이 1 나옴.
        if (s.length() == 1) return 1;

        int answer = Integer.MAX_VALUE;

        int length = s.length();
        for (int zipLength = 1; zipLength <= length / 2; zipLength++) {

            // 압축길이만큼 반복 (딱 절반까지해야지 뭐 압축이 몇개 되는지 알 수 있으니까..)
            String prev = "";
            String now = "";
            int zipCount = 1;
            StringBuilder sb = new StringBuilder();

            int nextIdx = 0;
            for (int index = 0; index <= length - zipLength; index += zipLength) {

                now = s.substring(index, index + zipLength);

                if (index == 0) {
                    // 시작지점이면 비교할 대상이 없으니까!
                    prev = now;
                    continue;
                }

                if (prev.equals(now)) {
                    // 이전 문자열과 같을 경우
                    zipCount++;
                } else {

                    // 이전 문자열과 다른 경우, 이전 문자열의 압축 개수와 문자열을 저장해야 함
                    if (zipCount == 1) {
                        // 문자가 반복되지 않아 한번만 나타난 경우 1은 생략함
                        sb.append(prev);
                    } else {
                        sb.append(zipCount).append(prev);
                    }

                    // 이번 문자열로 초기화
                    prev = now;
                    zipCount = 1;
                }

                nextIdx = index + zipLength; // 반복 끝나고 남는 문자열 있을 경우 체크해야 하니까..
            }

            // 반복문 종료됐을 때 안들어간 문자열 넣어줘야 함!
            if (zipCount == 1) {
                sb.append(now);
            } else {
                sb.append(zipCount).append(now);
            }

            if (nextIdx < length) {
                // 반복 끝나고 남는 문자열이 있는 경우 뒤에 그냥 잘라서 붙여주기
                sb.append(s.substring(nextIdx));
            }

            answer = Math.min(answer, sb.length());
        }

        return answer;
    }
}