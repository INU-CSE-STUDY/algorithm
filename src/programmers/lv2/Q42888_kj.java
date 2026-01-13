package programmers.lv2;

/* 프로그래머스 42888번 오픈채팅방 문제

[문제 풀이]
split해서 ["입장/퇴장/변경", "유저 아이디", "닉네임"] 이렇게 나눌 수 있음
길이 100,000이니까 2번 돌려도 완전탐색하는데는 문제가 없고,,

첫 반복 때 map에 key - userId, value - nickname으로 저장해서 입장 또는 변경 때마다 업데이트해주게 하고
두번째 반복 때 입장/퇴장이랑 유저 아이디만 식별해서 result 배열 만들면 될듯

*/

import java.util.*;

class Q42888_kj {
    public String[] solution(String[] record) {

        List<String> answer = new ArrayList<>();
        Map<String, String> userInfo = new HashMap<>(); // 유저 아이디와 닉네임을 저장할 map

        makeUserInfo(record, userInfo);
        printAllMessage(record, userInfo, answer);

        return answer.toArray(new String[0]);
    }

    private void makeUserInfo(String[] records, Map<String, String> userInfo) {

        for (String record : records) {

            String[] recordArr = record.split(" ");

            String status = recordArr[0]; // 입장/퇴장/변경 등 상태
            String userId = recordArr[1]; // 유저 아이디

            // 퇴장일 경우, 닉네임과 아무런 상관이 없기 때문에 continue
            if (status.equals("Leave")) continue;

            // 입장/변경의 경우, 닉네임에 영향을 주기 때문에 map에 저장
            String nickname = recordArr[2]; // 유저 닉네임
            userInfo.put(userId, nickname);
        }
    }

    private void printAllMessage(String[] records, Map<String, String> userInfo, List<String> answer) {

        for (String record : records) {

            String[] recordArr = record.split(" ");

            String status = recordArr[0]; // 입장/퇴장/변경 등 상태
            String userId = recordArr[1]; // 유저 아이디

            // 변경일 경우, 채팅방에 남는 메시지가 없으므로 continue
            if (status.equals("Change")) continue;

            String nickname = userInfo.get(userId);

            if (status.equals("Enter")) {
                // 입장의 경우, [ㅇㅇ님이 들어왔습니다.] 메시지가 저장됨
                answer.add(nickname + "님이 들어왔습니다.");
            }

            if (status.equals("Leave")) {
                // 퇴장의 경우, [ㅇㅇ님이 나갔습니다.] 메시지가 저장됨
                answer.add(nickname + "님이 나갔습니다.");
            }
        }
    }
}
