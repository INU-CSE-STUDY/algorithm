package programmers.lv2;

/* 프로그래머스 17684번 압축 문제

[문제 풀이]
hashmap에 문자와 번호를 저장한 뒤에 읽고 있는 위치부터 substring으로 짜르면서
가장 긴 문자열을 찾아서 value를 출력하고 다음문자를 포함해서 다시 사전을 확장한다

*/

import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> lzw = new HashMap<>();
        List<Integer> answerList = new ArrayList<>();
        
        for (int i = 0; i < 26; i++) {
            lzw.put(String.valueOf((char)('A' + i)), i + 1);
        }
        
        int next = 27;   // 다음에 넣을 사전 번호
        int idx = 0;     // 현재 읽기 시작 위치
        
         while (idx < msg.length()) {
            String w = String.valueOf(msg.charAt(idx));
            int end = idx + 1;

            // 현재 위치에서 시작하는 가장 긴 문자열 찾기
            while (end <= msg.length() && lzw.containsKey(msg.substring(idx, end))) {
                w = msg.substring(idx, end);
                end++;
            }

            answerList.add(lzw.get(w));

            // 다음 글자가 있으면 w+c를 사전에 추가
            if (end <= msg.length()) {
                String newWord = msg.substring(idx, end);
                lzw.put(newWord, next++);
            }

            // 인덱스 이동
            idx += w.length();
        }
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}