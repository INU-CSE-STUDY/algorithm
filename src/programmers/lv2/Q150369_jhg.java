package programmers.lv2;

// 그리디라는걸 문제를 읽자마자 알았다.
// 그냥 젤 뒤에서부터 하나씩 다 배송하는게 최선의 수다.
// delivery, pickup은 각각 배달헤애할, 수거해야할 박스의 수이다.
// 만약 두 변수가 0보다 작은 경우는 여유 상자를 의미
public class Q150369_jhg {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int delivery = 0;
        int pickup = 0;
        for(int i = n - 1 ; i >= 0; i--) {
            delivery += deliveries[i];
            pickup += pickups[i];

            while (delivery > 0 || pickup > 0) {
                answer += (i + 1) * 2L;
                delivery -= cap;
                pickup -= cap;
            }
        }

        return answer;
    }
}
