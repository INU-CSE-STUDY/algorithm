package programmers.lv2;

/* 프로그래머스 258711번 도넛과 막대 그래프 문제

[문제 풀이]
제일 먼저 정점을 찾은 뒤 각 그래프의 개수를 세는 방식으로 품
그래프 전체를 알 필요없이 이 조건이면 어떤 그래프가 된다는 것을 파악한 뒤
총 그래프의 개수를 생성된 정점과 연결 된 정점의 개수로 구하고 
막대는 끝점의 개수, 8자는 한 정점에서 2개 이상 나가는 정점의 개수 - 1(생성된 정점), 
도넛은 전체 그래프 개수에서 막대와 8자를 뺀 값으로 구함
*/  



import java.util.*;

class Q258711_lth {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        int vertex = 0;
        
        //배열 한개 생성후 모든 숫자 넣기
        Set<Integer> allNumber = new HashSet<>();
        for (int[] e : edges) {
            allNumber.add(e[0]);
            allNumber.add(e[1]);
        }
        //배열을 두개 만들어서 들어오는 배열의 숫자를 모두 각각 집어넣는다
        List<Integer> start = new ArrayList<>();
        for(int i = 0; i < edges.length; i++){
            start.add(edges[i][0]);
        }
        List<Integer> end = new ArrayList<>();
        for(int i = 0; i < edges.length; i++){
            end.add(edges[i][1]);
        }
        //생성된 정점을 찾는방법으로 배열의 두 번째 원소는 제외를 하고 2번 이상 나온 숫자
        allNumber.removeAll(new HashSet<>(end));
        
        Map<Integer, Integer> freq = new HashMap<>();
        for (int s : start) {
            freq.put(s, freq.getOrDefault(s, 0) + 1);   //freq.getOrDefault(s, 0) =  s의 값을 가져오며 없으면 0을 가져옴
                                                        //freq.put(s, …) = s의 값을 …로 바꿈(여기서는 +1)
        }
        
        for (int node : allNumber) {
            if (freq.getOrDefault(node, 0) >= 2) {      //node의 값을 가져와서 2 이상이면
                vertex = node;
                break;
            }
        }
        
        //그래프의 개수는 정점에서 보낸 숫자
        int graphCount = 0;
        for(int i = 0; i < start.size(); i++){
            if(start.get(i) == vertex){
                graphCount++;
            }
        }
        //막대 그래프 찾는 법 = 두번째 원소에만 있고 첫번째 원소에는 없는 숫자가 막대그래프의 끝, 끝의 개수만 세면 됨
        Set<Integer> startSet = new HashSet<>(start); // 출발로 등장한 정점들
        Set<Integer> endSet   = new HashSet<>(end);   // 도착으로 등장한 정점들

        int barCount = 0; // 막대 그래프(끝점) 개수
        for (int v : endSet) {
            if (!startSet.contains(v)) {
                barCount++;
            }
        }
        //8자 모양 그래프 찾는 법 = 한 숫자에서 나가는 숫자가 2개가 있으면 8자모양의 중간, 중간 개수만 세면 됨
        int eightCount = 0;
        for (int v : freq.keySet()) {
            if (freq.get(v) >= 2) { // 출발 간선이 2개 이상
                eightCount++;
            }
        }
        eightCount--; // 생성된 정점 빼기
        
        //도넛 모양 찾는 법 : 그래프의 개수 = 막대 그래프의 수 - 8자 모양 그래프의 수
        int donutCount = graphCount - barCount - eightCount;
        answer[0] = vertex;
        answer[1] = donutCount;
        answer[2] = barCount;
        answer[3] = eightCount;
        return answer;
    }
}