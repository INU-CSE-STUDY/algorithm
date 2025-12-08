package programmers.lv3;

/*  프로그래머스 150367번 표현 가능한 이진트리 문제

[문제 풀이]
2진수로 바꾼 수를 이진트리로 만들 수 있는지 확인하면 되는 문제인가
101 같은게 안되는 이유가 루트 노드가 더미 노드면 안되는거니까..!
-> 부모 노드들이 일단 0이면 안됨 (5 또는 95 같은 예시로,, 자식은 1인데 부모가 더미 노드이면 안되니까)

1. 일단 포화이진트리로 만들어야 하니까 2^n - 1 개수 맞춰서 0 넣기(문제에서 포화 이진트리라 했으니까)
2. 더미 노드 밑에 1이 있는지 여부 검사해야 하니까, 분할정복으로 반씩 탐색해가며 확인하기
   - 더미 노드는 가짜 노드니까.. 더미 노드 밑에 실제값이 존재하면 안됨. 따라서 분할정복하면서 루트가 0일때 왼쪽 자식이든 오른쪽 자식이든 어딘가에 1이 포함되면 불가능한 경우
   - 더 이상 쪼갤 수 없을 때까지 확인해서 가능한 거면 이진트리로 나타낼 수 있는 트리

문제 자체만 이해하면 쉬웠을 지도..
일단 분할 정복이라는 아이디어는 생각했는데 ㅠㅠ 문제를 꼼꼼히 읽지 않아 포화 이진트리를 만들지 않아 조금 헤매고.. 결국엔 질문하기를 봤던 것 같다,, (128 같은 경우를 고려를 안했었음;; 그래서 다 틀린 것도 있고,, 생각이 그냥 쫌 짧았던 코드)
포화 이진트리 만드려면 2^n - 1개로 개수 맞춘다음에, 내가 생각한 분할 정복 아이디어 했으면 될 일이었당..
왜냐면 반씩 나눠가지고 루트가 0인 경우 왼쪽이든 오른쪽이든 1이 존재하는지 여부만 확인하면 됐으니..

*/

class Q150367_kj {
    public int[] solution(long[] numbers) {

        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {

            long number = numbers[i];

            // 이진수로 변경 후, 포화 이진트리 구성에 맞게 0 삽입
            String binaryNumber = Long.toString(number, 2);
            binaryNumber = insertZero(binaryNumber);

            if (canConvertBinaryTree(binaryNumber)) {
                answer[i] = 1; // 이진트리로 해당 수를 표현할 수 있다면 1
            } else {
                answer[i] = 0; // 이진트리로 해당 수를 표현할 수 없다면 0
            }
        }

        return answer;
    }

    private boolean canConvertBinaryTree(String binaryNumber) {

        // 더 이상 나눌 수 없을 때
        if (binaryNumber.length() <= 1) return true;

        int mid = binaryNumber.length() / 2;
        char root = binaryNumber.charAt(mid);

        String left = binaryNumber.substring(0, mid);
        String right = binaryNumber.substring(mid + 1);

        if (root == '0') {
            // 쪼개진 트리에서 루트가 0(더미 노드)일 경우, 왼쪽 자식이든 오른쪽 자식이든 1이 포함되면 안됨
            if (hasOne(left) || hasOne(right)) return false;
        }

        // 모든 경우를 다 만족하는 경우에만 트리를 만들 수 있음
        return canConvertBinaryTree(left) && canConvertBinaryTree(right);
    }

    private boolean hasOne(String binaryNumber) {
        return binaryNumber.contains("1");
    }

    private String insertZero(String binaryNumber) {

        // 포화 이진트리를 만들기 위해 길이를 2^n - 1로 맞춰줘야 함(앞에 0 삽입함으로써)

        int e = 1;
        int length = binaryNumber.length();
        while (true) {

            int now = (int) Math.pow(2, e) - 1;
            if (length == now) break;
            if (length < now) {

                binaryNumber = "0".repeat(now - length) + binaryNumber;

                break;
            }
            e++;
        }

        return binaryNumber;
    }
}
