package programmers.lv2;

/* 프로그래머스 60058번 괄호 변환 문제

[문제 풀이]
1단계부터 4단계까지 차근차근 구현
substring이 중요, 문자열을 자르는 함수

*/

class Q60058_lth {
    public String solution(String p) {
        return convert(p);
    }

    private String convert(String p) {
        // 1단계
        if (p.isEmpty()) return "";

        // 2단계
        String[] parts = split(p);
        String u = parts[0];
        String v = parts[1];

        // 3단계
        if (isCorrect(u)) {
            return u + convert(v);
        }

        // 4단계
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(convert(v));
        sb.append(")");

        // u의 첫, 마지막 제거 후 뒤집기
        for (int i = 1; i < u.length() - 1; i++) {
            sb.append(u.charAt(i) == '(' ? ')' : '(');
        }

        return sb.toString();
    }

    private boolean isCorrect(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '('){
                cnt++;
            }else{
                cnt--; 
            } 
            if (cnt < 0) return false;
        }
        return cnt == 0;
    }

    private String[] split(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                cnt++;
            }else{
                cnt--;
            } 
            if (cnt == 0) {
                return new String[]{
                    s.substring(0, i + 1),
                    s.substring(i + 1)
                };
            }
        }
        return new String[]{"", ""};
    }
}
