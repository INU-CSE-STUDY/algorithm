package programmers.lv2;

/* 프로그래머스 49993번 스킬트리 문제

[문제 풀이]
for문으로 skill_trees를 돌면서 스킬인덱스를 0부터 해서 skill에 속한 인덱스라면 
스킬트리의 스킬인덱스와 skill의 해당 스킬의 인덱스를 비교해서 맞다면 스킬트리의 스킬인덱스 +1
아니라면 break하고 다음 트리조사해서 성공하면 answer +1;

*/




class Q49993_lth {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String tree : skill_trees) {
            int need = 0;
            boolean ok = true;

            for (int i = 0; i < tree.length(); i++) {
                char c = tree.charAt(i);
                int idx = skill.indexOf(c); // skill에 없으면 -1

                if (idx == -1){
                    continue; // 상관없는 스킬이면 패스
                } 

                if (idx == need) {
                    need++; // 올바른 순서로 배움
                } else {
                    ok = false; // 스킵함
                    break;
                }
            }

            if (ok){
                answer++;
            } 
        }

        return answer;
    }
}
