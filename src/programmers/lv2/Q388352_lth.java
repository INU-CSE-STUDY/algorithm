package programmers.lv2;
/* 프로그래머스 388352번 비밀 코드 해독 문제

[문제 풀이]
1 - n까지의 숫자를 넣은 배열을 만들고
2 - ans배열에서 0인 경우 q배열에서 해당 숫자를 제거
3 - ans배열에서 최댓값의 인덱스를 찾고 q배열에서 해당 인덱스의 숫자들을 뽑아서 조합을 만든다
4 - 남은 숫자들에서 5 - n개를 뽑아서 조합을 만든다
5 - 3,4에서 만든 조합을 합쳐서 result배열에 넣는다
6 - result배열에서 ans배열의 조건에 맞는 것만 filtered배열에 넣는다
7 - filtered배열의 크기를 반환한다
*/

import java.util.*;

class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        //최종 남은 조합
        List<int[]> filtered = new ArrayList<>();
        
        //숫자 배열
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i + 1);
        }
        
        //0일 경우 숫자배열에서 제거
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == 0) {
                for (int val : q[i]) {
                    list.remove(Integer.valueOf(val));
                }
            }
        }
        
        //최댓값 인덱스 구하기
        int maxIndex = 0; 
        for (int i = 1; i < ans.length; i++) {
            if (ans[i] > ans[maxIndex]) {
                maxIndex = i;
            }
        }

        
        //포함된 정수
        List<int[]> contain = new ArrayList<>();
        combine(q[maxIndex], ans[maxIndex], 0, new ArrayList<>(), contain);
        
        for (int val : q[maxIndex]) {
            list.remove(Integer.valueOf(val));
        }
        
        
        List<int[]> result = new ArrayList<>();
        for (int[] chosen : contain) {
            // remain에서 5-n개 고르기
            List<int[]> remain = new ArrayList<>();
            int m = 5 - ans[maxIndex];
            
            int[] listArr = list.stream().mapToInt(Integer::intValue).toArray();
            combine(listArr, m, 0, new ArrayList<>(), remain);

            //contain과 remain 합쳐서 숫자조합 만들고 result에 저장
            for (int[] r : remain) {
                int[] merged = new int[5];
                System.arraycopy(chosen, 0, merged,0, chosen.length);
                System.arraycopy(r, 0, merged, chosen.length, r.length);
                Arrays.sort(merged);                    // 오름차순 저장
                result.add(merged);
            }
        }
        
        //result배열에 맞는 것만 남기기
        for(int i = 0; i < result.size(); i++){
            Set<Integer> set = new HashSet<>();
            int[] cand = result.get(i);
            
            for (int x : cand){
                set.add(x);
            }
            boolean ok = true;
            for(int j = 0; j < ans.length; j++){
                int count = 0;
                for (int x : q[j]) {
                    if (set.contains(x)) count++;
                }
                if (count != ans[j]) {   // 제약 불만족 시 탈락
                    ok = false;
                    break;
                }
            }
            if (ok) filtered.add(cand);
        }
        answer = filtered.size();
        return answer;
    }
    
    //
    private static void combine(int[] arr, int r, int start, List<Integer> cur, List<int[]> out)
    {
        if (cur.size() == r) {  //현재까지 뽑은 숫자의 개수가 r과 같으면
            out.add(cur.stream().mapToInt(Integer::intValue).toArray()); //배열로 변환해서 out에 저장
            return;
        }
        //재귀로 조합생성
        for (int i = start; i < arr.length; i++) {
            cur.add(arr[i]);    // 현재 원소 선택
            combine(arr, r, i + 1, cur, out);   // 다음 인덱스부터 다시 선택 (중복 방지)
            cur.remove(cur.size() - 1);     // 방금 넣은 원소 빼고 다음 반복 준비
        }
    }

}