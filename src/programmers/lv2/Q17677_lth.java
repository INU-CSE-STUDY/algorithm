package programmers.lv2;

/* 프로그래머스 17677번 뉴스 클러스터링

[문제 풀이]
문자열 2개를 각각 문자열 리스트로 변환해서 문자만 포함된 것을 추가시키고
새로운 리스트 3을 만들어서 2를 복사한뒤 1과 3을 비교하면 중복된것을 제거하면서 카운트를 올린다
합집합은 1과 2의 모든 개수에서 겹치는 카운트를 뺀값이고
교집합은 겹치는 카운트이다

*/

import java.util.*;

class Q17677_lth {
    public int solution(String str1, String str2) {
        List<String> list1 = makeList(str1);
        List<String> list2 = makeList(str2);
        List<String> list3 = new ArrayList<>(list2);
        int intersection = 0;
        
        for(String s : list1){
            if(list3.contains(s)){
                intersection++;
                list3.remove(s);
            }
        }
        
        int union = list1.size() + list2.size() - intersection;

        if (union == 0) return 65536;
        
        
        return (int)((double)intersection / union * 65536);
    }
    
    private List<String> makeList(String str){
        List<String> list = new ArrayList<>();
        str = str.toUpperCase();
        
        for (int i = 0; i < str.length() - 1; i++) {
            char a = str.charAt(i);
            char b = str.charAt(i + 1);

            if (Character.isLetter(a) && Character.isLetter(b)) {
                // ""가 없으면 int로 계산됨
                list.add("" + a + b);
            }
        }
        return list;
    }
}