package programmers.lv3;

/*  프로그래머스 150367번 표현 가능한 이진트리 문제

[문제 풀이]
이진수로 일단 변경하고 포화이진트리가 안 되면 될때까지 앞에 0을 채움
재귀로 부모가 0인데 자식이 있는지 확인한다
되면 1 리턴 안 되면 0 리턴

*/

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++){
            String fullBinary = toFullBinary(numbers[i]);
            answer[i] = isValidTree(fullBinary) ? 1 : 0;
        }
        
        
        return answer;
    }
    
    private String toFullBinary(long number) {
        String bin = Long.toBinaryString(number);
        int len = bin.length();

        // 포화 이진트리 크기 구하기
        int h = 1;
        while ((1 << h) - 1 < len) {
            h++;
        }

        int fullLength = (1 << h) - 1;  // 최종 노드 수 (2^h - 1)

        // 앞에 0을 채워 맞추기
        return String.format("%" + fullLength + "s", bin).replace(' ', '0');
    }
    
    private boolean isValidTree(String bin) {
        return check(bin, 0, bin.length() - 1);
    }

    private boolean check(String bin, int start, int end) {
        // 마지막 노드면 무조건 가능
        if (start == end) {
            return true;
        }

        int mid = (start + end) / 2;    // 부모 인덱스
        char root = bin.charAt(mid);    // 부모 숫자

        // 왼쪽 서브트리, 오른쪽 서브트리 재귀 검사
        boolean isValidLeft = check(bin, start, mid - 1);
        boolean isValidRight = check(bin, mid + 1, end);

        if (!isValidLeft || !isValidRight) return false;

        // root가 0이면 1이 있으면 안 된다
        if (root == '0') {
            for (int i = start; i <= end; i++) {
                if (bin.charAt(i) == '1') {
                    return false; // 0인 부모 아래에 1이 있는 경우
                }
            }
        }

        return true;
    }
    
    
}