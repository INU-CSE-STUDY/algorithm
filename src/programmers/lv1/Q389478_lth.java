package programmers.lv1;

/* 프로그래머스 389478번 택배 상자 꺼내기 문제

[문제 풀이]
상자라는 객체를 만들어서 상자 번호, 줄, 현재 줄에 있는 상자 개수들을 담고
두 상자의 줄의 차이 + (같은 줄일 경우 남은 상자 개수로 비교 다른 줄일 경우 남은 상자 개수 합이 w보다 크면 +1)로 계산
*/  


class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        
        Box lastBox = findBox(n, w);
        Box selectBox = findBox(num, w);
        int lines = lastBox.line - selectBox.line;
        
        if(n <= w){
            answer = 1;
        }
        
        if(lastBox.isOdd() == selectBox.isOdd()){
            answer = lines + (lastBox.remain >= selectBox.remain ? 1 : 0);
        }else{
            answer = lines + (lastBox.remain + selectBox.remain > w ? 1 : 0);
        }
        
        return answer;
    }
    
    private static Box findBox(int boxNumber, int max){
        int line;
        int remain;
        if(boxNumber % max != 0){
            remain = boxNumber % max;
        }else{
            remain = max;
        }
        if(boxNumber % max != 0){
            line = boxNumber / max;
        }else{
            line = (boxNumber - 1) / max;
        }
        return new Box(boxNumber, line, remain);
    }
    
    
    private static class Box{
        int boxNumber;
        int remain;
        int line;
        
        Box(int boxNumber, int line, int remain) {
            this.boxNumber = boxNumber;
            this.line = line;
            this.remain = remain;
        }
        
        public boolean isOdd(){
            return this.line % 2 == 1;
        }
        
    }
    
}