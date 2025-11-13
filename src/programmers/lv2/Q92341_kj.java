package programmers.lv2;

/* 프로그래머스 92341번 주차 요금 계산

[문제 풀이]
1. records 정렬하기
- 차량번호 기준으로 오름차순, 시간 기준으로 오름차순
2. records 탐색하기
- 동일한 차가 입차 후 다시 입차하거나 존재하지 않는 차가 출차하는 경우는 없기 때문에 정렬한 이후 두 개씩 탐색하면 쉽게 가능
  출차된 내역이 없는 경우가 있으므로 이것만 주의해서 생각해주기!
- 한 차가 여러번 왔다갔다 할 수 있으므로 LinkedHashMap에 차량 번호와 함께 누적 주차 시간을 저장해두면 됨
3. 누적 주차 시간 다 구했으면 요금표 기준으로 요금 계산 후 반환하기
*/

import java.util.*;

class Q92341_kj {

    final int BASE_OUT_TIME = 1439; // 23:59를 분단위로 변경한 값

    public int[] solution(int[] fees, String[] records) {

        CarInfo[] carInfoArr = initCarInfoArr(records); // 객체로 변경 후 정렬

        Map<Integer, Integer> carParkingTimeMap = new LinkedHashMap<>(); // <차량 번호, 누적 주차 시간>

        int index = 0;
        while (index < records.length) {
            int timeDiff = 0;
            boolean isPair = false;

            CarInfo carInfo1 = carInfoArr[index];
            int carNumber = carInfo1.carNumber;
            if (index != records.length - 1) {
                // 마지막 기록이 아닌 경우, 짝을 맞춰서 확인해보기
                CarInfo carInfo2 = carInfoArr[index + 1];

                if (carNumber == carInfo2.carNumber) {
                    // 바로 다음 내역과 짝이 맞는 경우 = 해당 시간 출차로 계산하면 됨
                    timeDiff = calculateMinutesDiff(carInfo1.time, carInfo2.time);

                    isPair = true;
                } else {
                    // 바로 다음 내역과 짝이 맞지 않는 경우 = 23:59 출차로 계산하면 됨
                    timeDiff = calculateMinutesDiff(carInfo1.time, BASE_OUT_TIME);
                }
            } else {
                // 마지막 IN 기록만 남은 경우 = 23:59 출차로 계산하면 됨
                timeDiff = calculateMinutesDiff(carInfo1.time, BASE_OUT_TIME);
            }

            // 해당 자동차 번호에 누적 주차 시간을 더하기
            carParkingTimeMap.put(carNumber,
                    carParkingTimeMap.getOrDefault(carNumber, 0) + timeDiff
            );

            if (isPair) {
                // 짝이 맞는 경우 = IN, OUT 내역을 모두 확인한 경우 = +2 해서 다음 IN 기록 확인
                index += 2;
            }
            else {
                // 짝이 맞지 않는 경우 = IN 내역만 확인한 경우 = +1 해서 다음 IN 기록 확인
                index += 1;
            }
        }

        int[] answer = new int[carParkingTimeMap.size()];
        index = 0;
        for (int key : carParkingTimeMap.keySet()) {
            // 요금표 기준으로 누적 주차 시간의 주차 요금을 계산
            int totalParkingTime = carParkingTimeMap.get(key);
            int parkingFee = calculateParkingFee(totalParkingTime, fees);

            answer[index++] = parkingFee;
        }

        return answer;
    }

    private CarInfo[] initCarInfoArr(String[] records) {

        CarInfo[] carInfoArr = new CarInfo[records.length];
        for (int i = 0; i < records.length; i++) {
            carInfoArr[i] = new CarInfo(records[i]);
        }

        Arrays.sort(carInfoArr);

        return carInfoArr;
    }

    private int calculateMinutesDiff(int startTime, int endTime) {
        return endTime - startTime;
    }

    private int calculateParkingFee(int parkingTime, int[] fees) {
        int baseTime = fees[0]; // 기본 시간
        int baseFee = fees[1];  // 기본 요금
        int unitTime = fees[2]; // 단위 시간
        int unitFee = fees[3];  // 단위 요금

        if (parkingTime > baseTime) {
            // 누적 주차 시간이 기본 시간을 넘겼다면, 단위 요금을 적용해야 함

            return baseFee + (int) Math.ceil((double) (parkingTime - baseTime) / unitTime) * unitFee;
        } else {
            // 누적 주차 시간이 기본 시간을 넘기지 않았다면, 기본 요금만 지불하면 됨

            return baseFee;
        }
    }

    class CarInfo implements Comparable<CarInfo> {
        int time;
        int carNumber;
        String info;

        CarInfo(String record) {
            String[] parts = record.split(" ");

            this.time = getMinutes(parts[0]);
            this.carNumber = Integer.parseInt(parts[1]);
            this.info = parts[2];
        }

        private int getMinutes(String time) {
            String[] HHMM = time.split(":");

            int hh = Integer.parseInt(HHMM[0]);
            int mm = Integer.parseInt(HHMM[1]);

            return hh * 60 + mm;
        }

        @Override
        public int compareTo(CarInfo c) {

            // 차량 번호 기준으로 비교
            int carNumberCompare = Integer.compare(this.carNumber, c.carNumber);

            if (carNumberCompare == 0) {
                // 차량 번호가 같다면, 시간 기준으로 오름차순 정렬
                return Integer.compare(this.time, c.time);
            }

            // 차량 번호가 다르다면 차량 번호 기준으로 오름차순 정렬
            return carNumberCompare;
        }
    }
}