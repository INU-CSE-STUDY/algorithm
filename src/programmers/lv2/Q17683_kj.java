package programmers.lv2;

/* 프로그래머스 17683번 방금그곡 문제

[문제 풀이]
노래 재생된 시간만큼 노래를 쭉 늘린 다음에, m이 포함됐는지 확인하면 되는거 아닌가??
CDEFGAB << 얘는 지금 7분? 짜리 노랜데 총 재생시간은 14분이니까 CDEFGABCDEFGAB 이렇게 늘린 다음에(혹시 재생시간보다 길 수도 있으니 substring 해주기) 저 문자열이 m을 contains 하는지 보면 될 거 같음!!
- m contains 하면,, title 업데이트하기 + 일치하는 음악 여러 개인 경우 재생 시간, 먼저 입력 조건 이런거 체크해서 업데이트

- 틀려서 보니까;; #붙는 아이들을 하나도 생각을 안해줌,,
  그리고 B#, E#은 문제 조건에 없는데 넣어야 풀림; 이게 말이 되나,, 어쨌든 이걸 고려해서 짜 주면 된다!
  -> C#, D# 이런 두개짜리 애들을 그냥 한글자 단어나 숫자 같은거로 치환해서 하나의 음으로 고려하게 해주면 됨
- 또 틀려서 뭔가 했더니;; 조건 일치하는 음악이 없을 때에 (None) 반환하라고 되어있었다;; 이런 중요한 걸 놓치다니..
  -> answer 값 초기화를 (None)으로 해서 자동으로 반환되게 하면 됨
-

*/

import java.util.*;

class Q17683_kj {

    Map<String, String> scaleMap = new HashMap<>() {
        {
            put("C#", "1");
            put("D#", "2");
            put("E#", "3");
            put("F#", "4");
            put("G#", "5");
            put("A#", "6");
            put("B#", "7");
        }
    };

    public String solution(String m, String[] musicinfos) {

        String answer = "(None)";
        int answerPlayTime = -1;

        for (String key : scaleMap.keySet()) {
            m = m.replace(key, scaleMap.get(key));
        }

        for (String musicInfo : musicinfos) {

            String[] info = musicInfo.split(",");
            String startTime = info[0];
            String endTime = info[1];
            String title = info[2];
            String melody = info[3];

            for (String key : scaleMap.keySet()) {
                melody = melody.replace(key, scaleMap.get(key));
            }

            int melodyTime = melody.length(); // 악보 길이
            int playTime = getPlayTime(startTime, endTime); // 음악 재생시간 (분 단위)

            int repeatCount = (int) Math.ceil((double) playTime / melodyTime); // 재생시간 동안 반복해서 들어야 하니, 최대로 반복될 길이를 구함
            String repeatMelody = melody.repeat(repeatCount).substring(0, playTime); // 재생시간보다 긴 건 못 들으니까 substring으로 잘라주기

            if (repeatMelody.contains(m)) {

                if (answer.equals("(None)")) {
                    answer = title;
                    answerPlayTime = playTime;
                } else {
                    if (playTime > answerPlayTime) {
                        // 조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된 시간이 제일 긴 음악 제목 반환
                        // 재생 시간이 같을 경우, 먼저 입력된 음악 제목 반환(playTime에 equal 안넣으면 해결되는 문제)
                        answer = title;
                        answerPlayTime = playTime;
                    }
                }
            }

        }

        return answer;
    }

    private int getPlayTime(String startTime, String endTime) {
        // 음악의 총 재생시간을 구하기
        String[] startHHMM = startTime.split(":");
        String[] endHHMM = endTime.split(":");

        int startHH = Integer.parseInt(startHHMM[0]);
        int startMM = Integer.parseInt(startHHMM[1]);
        int startMinutes = startHH * 60 + startMM;

        int endHH = Integer.parseInt(endHHMM[0]);
        int endMM = Integer.parseInt(endHHMM[1]);
        int endMinutes = endHH * 60 + endMM;

        return endMinutes - startMinutes;
    }
}
