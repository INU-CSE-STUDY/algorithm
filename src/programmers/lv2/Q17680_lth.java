package programmers.lv2;

/* 프로그래머스 17680번 캐시 문제

[문제 풀이]
순서와 관련된거라 pq로 풀면 되겠지라고 생각했는데 pq는 중간에 있는 도시를 제거하는 것이 어렵고
pq에 있는 키를 찾기도 어려워서 map으로 하게되었는데 LinkedHashMap을 이용하여 순서 또한 이용하였다
LinkedHashMap의 16은 크기 0.75f는 용량늘리는 조건으로 중요하지 않고 true라는 인자가 중요한데 true일때는 map에 있는 것을
get이나 put으로 접근할때 순서가 자동으로 갱신된다. 이를 이용하여 map에 넣고 빼는것만 신경써서 풀면된다
 - LinkedHashMap은 removeEldestEntry를 오버라이드하면 가장 오래된 항목을 자동으로 제거할 수 있다고 하지만 
   오버라이드는 익숙하지 않아 사용하지 않았다

*/

import java.util.*;

class Q_17680_lth {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * 5;
        int answer = 0;
        
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>(16, 0.75f, true);
        
        for(String city : cities){
            city = city.toLowerCase();
            if(map.containsKey(city)){
                map.get(city);
                answer += 1;
            }else{
                if(map.size() < cacheSize){
                    map.put(city, 0);
                    answer += 5;
                }else{
                    // keySet()은 Map에 들어있는 모든 key를 Set 형태로 반환하는데 Linked는 순서도 유지
                    // .iterator()로 앞에서부터 접근해서 .next()로 다음 요소 하나를 반환
                    String oldest = map.keySet().iterator().next();
                    map.remove(oldest);
                    map.put(city, 0);
                    answer += 5;
                }
            }
        }
        
        return answer;
    }
}