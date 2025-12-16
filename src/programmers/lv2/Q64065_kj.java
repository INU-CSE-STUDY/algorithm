package programmers.lv2;

/* 프로그래머스 64065번 튜플 문제

[문제 풀이]
들어온 n-튜플을 split해서 일단 나누기 + 길이 순으로 정렬
- { a1 } / { a1, a2 } / { a1, a2, a3 } / ... 순으로 정렬해두면 확인하기 쉬우니까


중복되는 원소가 없다고 했으니까,, 길이 순서대로 하나씩 접근해서 중복되지 않는 원소 첨 만나면 answer 배열/set에 저장하면서 계속 탐색하기

*/

import java.util.*;

class Q64065_kj {
    public int[] solution(String s) {

        // split 후, 길이 기준으로 정렬
        s = s.substring(2, s.length() - 2).replace("},{", " ");
        String[] nTuple = s.split(" ");
        Arrays.sort(nTuple, Comparator.comparingInt((String str) -> str.length()));

        Set<Integer> set = new HashSet<>();

        int[] answer = new int[nTuple.length]; // 배열 길이만큼 원소가 존재
        for (int i = 0; i < nTuple.length; i++) {

            String[] numSet = nTuple[i].split(",");
            for (String numStr : numSet) {

                int num = Integer.parseInt(numStr);
                if (set.contains(num)) continue;

                set.add(num);
                answer[i] = num;
                break; // 앞에서부터 순차 탐색하는거라 set에 포함되지 않는거 만나면 그대로 break 해도 됨
            }
        }

        return answer;
    }
}