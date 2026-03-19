package programmers.lv2;

/* 프로그래머스 12981번 영어 끝말잇기 문제

[문제 풀이]
중복되는지와 단어가 이어지는지 확인을 하면 되는데 중복은 set으로 체크하며 이어지는지는
charAt으로 글자가 이어지는지 체크를 해서 확인한 뒤에 조건에 안 맞으면 중간에 리턴
끝까지 가면 0,0 반환
이전단어의 마지막글자를 저장해서 비교를 하려했지만 첫 단어를 시작할때 그 전 글자를 ""로 해야하는데
그러려면 char가 아닌 String으로 해야해서 저장을 하지 않기로 함
*/

import java.util.*;

class Q12981_lth {
    public int[] solution(int n, String[] words) {
        Set<String> used = new HashSet<>();
        used.add(words[0]);
        
        for (int i = 1; i < words.length; i++) {
            String prev = words[i - 1];
            String cur = words[i];
            
            // 이전 단어 끝 문자와 현재 단어 첫 문자 비교
            if (prev.charAt(prev.length() - 1) != cur.charAt(0) || used.contains(cur)) {
                return new int[]{i % n + 1, i / n + 1};
            }
            used.add(cur);
        }
        return new int[2];
    }
}