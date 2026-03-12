package programmers.lv2;

/* 프로그래머스 17683번 방금그곡 문제

[문제 풀이]
#을 구분하는 게 가장 중요한 문제로 두글자인 #들을 한글자로 치환해서 풀면된다
우선순위에 재생시간이 가장 긴 것부터 비교를 한다
-

*/
class Q17683_lth {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxPlayTime = -1;
        
        m = convertMelody(m);
        
        for (String info : musicinfos) {
            String[] parts = info.split(",");
            String start = parts[0];
            String end = parts[1];
            String title = parts[2];
            String melody = parts[3];
            
            int playTime = getTime(start, end);
            melody = convertMelody(melody);
            
            String played = makePlayedMelody(melody, playTime);
            
            if (played.contains(m)) {
                if (playTime > maxPlayTime) {
                    maxPlayTime = playTime;
                    answer = title;
                }
            }
        }
        
        return answer;
    }
    
    // #을 소문자로 변환
    private String convertMelody(String melody) {
        return melody
                .replace("C#", "c")
                .replace("D#", "d")
                .replace("E#", "e")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a")
                .replace("B#", "b");
    }
    
    private int getTime(String startTime, String endTime) {
        String[] start = startTime.split(":");
        String[] end = endTime.split(":");

        int startMin = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
        int endMin = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);

        return endMin - startMin;
    }
    
    // 재생시간만큼 실제 재생된 멜로디 생성
    private String makePlayedMelody(String melody, int playTime) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < playTime; i++) {
            sb.append(melody.charAt(i % melody.length()));
        }
        
        return sb.toString();
    }
}