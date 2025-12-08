package programmers.lv2;

/* 프로그래머스 70129번 이진 변환 반복하기 문제

[문제 풀이]
문자열을 char단위로 세서 1이 나오면 다음 길이 0부터 +1씩해서
문자열의 길이가 1이 될때까지 반복

*/

class Q70129_lth {
    public int[] solution(String s) {
        int[] answer = new int[2];
        int l = s.length();
        
        while(l != 1){
            int temp = 0;
            for(int i = 0; i < l; i++){
                int c = s.charAt(i) - '0';
                if(c == 1){
                    temp++;
                }else{
                    answer[1]++;
                }
            }
            s = Integer.toBinaryString(temp);
            l = s.length();
            answer[0]++;
        }
        
        
        
        return answer;
    }
}