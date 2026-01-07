package programmers.lv2;

/* 프로그래머스 49993번 스킬트리 문제

[문제 풀이]
index 관리로 완전탐색..?? 느낌?? 하나씩 다 확인해보기!

*/

class Q49993_kj {
    public int solution(String skill, String[] skill_trees) {

        int answer = 0;
        for (String skill_tree : skill_trees) {

            int skillSequenceIndex = 0; // 스킬트리 순서가 맞는지 확인하는 인덱스
            boolean isImpossible = false; // 스킬트리 배열의 가능 여부

            for (int i = 0; i < skill_tree.length(); i++) {

                // 선행 스킬 순서를 모두 만족한 경우, 더 이상 확인할 필요가 없음
                if (skillSequenceIndex == skill.length()) break;

                char nowSkill = skill_tree.charAt(i); // 배울 스킬

                if (skill.contains(String.valueOf(nowSkill))) {
                    // 선행 스킬이 필요한 스킬이 포함된 경우, 제대로 되어있는지 확인해야 함

                    if (nowSkill == skill.charAt(skillSequenceIndex)) {
                        // 순서가 맞는 경우, 다음 스킬 확인을 위해 index 증가
                        skillSequenceIndex++;
                    } else {
                        // 순서가 맞지 않는 경우, 불가능한 스킬 트리이므로 탐색 종료
                        isImpossible = true;
                        break;
                    }
                }
            }

            if (isImpossible) continue;
            answer++;
        }

        return answer;
    }
}