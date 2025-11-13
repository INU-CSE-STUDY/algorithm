package programmers.lv2;

/* 프로그래머스 92341번 주차 요금 계산

[문제 풀이]
저장된 데이터에 키 값을 찾는 것에는 map이 효율적이기에 map으로 사용하고 정렬을 위해 중복되는 번호를 지울 set을 사용하고
차량 번호를 정렬하기 위해 list를 사용하였다. 그 다음에 객체를 사용해서 입차와 출차를 구분하여 누적 주차 시간을 계산하였다.
처음 객체를 만들때는 차량번호도 넣었지만 map에서 키 값으로 사용하기에 불필요하여 제거하였다.
마지막 입차는 23:59로 계산하는 부분은 23:59에서 시간을 뺴는 방식으로 구현되어있다
-> 요금계산 함수의 변수명은 참고해왔다+
*/

import java.util.*;

class Q92341_lth {
    static final int MAX_OUT = 1439; // 23:59 → 분 단위

    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Car> map = new HashMap<>();
        Set<Integer> nums = new HashSet<>();

        for (String record : records) {
            String[] parts = record.split(" ");
            int time = toMin(parts[0]);
            int num = Integer.parseInt(parts[1]);
            String type = parts[2];

            nums.add(num);

            // 키가 없으면 새로 생성
            Car car = map.computeIfAbsent(num, key -> new Car());

            // 누적 처리
            car.update(time, type);
        }

        // 차량 번호 정렬
        List<Integer> sorted = new ArrayList<>(nums);
        Collections.sort(sorted);

        // 요금 계산
        int[] answer = new int[sorted.size()];
        int idx = 0;

        // 정렬된 차량 번호 순서대로 요금 계산
        for (int num : sorted) {
            Car car = map.get(num);
            int total = car.totalTime;

            answer[idx++] = calcFee(total, fees);
        }

        return answer;
    }

    // 분으로 변환
    private int toMin(String part) {
        String[] t = part.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }

    // 요금 계산 함수
    private int calcFee(int total, int[] fees) {
        int baseTime = fees[0];
        int baseFee  = fees[1];
        int unitTime = fees[2];
        int unitFee  = fees[3];

        if (total <= baseTime) return baseFee;

        total -= baseTime;
        // 올림 공식 : ceil(a / b) = (a + b - 1) / b
        int units = (total + unitTime - 1) / unitTime;
        return baseFee + units * unitFee;
    }

    static class Car {
        int totalTime = 0;

        public void update(int time, String type) {
            if ("IN".equals(type)) {
                totalTime += MAX_OUT - time;    // IN이면 (23:59 - 입차시간) 더함
            } else { // OUT
                totalTime -= MAX_OUT - time;    // OUT이면 (23:59 - 출차시간) 뺌
            }
        }
    }
}
