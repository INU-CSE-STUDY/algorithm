package programmers.lv2;

/* 프로그래머스 12949번 행렬의 곱셈 문제

[문제 풀이]
아니 근데 행렬의 곱 뭐 어떻게 하는지도 안알려주네
A 행렬 X B 행렬을 할 때 중요한 점 => A 행렬의 열의 수 == B 행렬의 행의 수 여야지 행렬의 곱이 성립함. 아니면 안곱해짐
그리고 곱해지는 순서가 있어서 행렬의 크기는 A 행렬의 행 수 X B 행렬의 열 수 크기로 나옴

이 사실을 적용하면 arr1[i][column] * arr2[column][j] 뭐 이런 느낌으로 곱해서 더하면 나온다는뜻!

*/

class Q12949_kj {
    public int[][] solution(int[][] arr1, int[][] arr2) {

        int arr1Row = arr1.length;
        int arr1Column = arr1[0].length;
        int arr2Row = arr2.length;
        int arr2Column = arr2[0].length;

        // 행렬 곱하면 앞에 행렬 모양대로 나오니까 크기는 arr1과 똑같게 초기화
        int[][] answer = new int[arr1Row][arr2Column];
        for (int i = 0; i < arr1Row; i++) {
            for (int j = 0; j < arr2Column; j++) {
                for (int k = 0; k < arr2Row; k++) {
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return answer;
    }
}