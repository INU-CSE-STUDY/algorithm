package programmers.lv2;

import java.util.*;

/**
 * 진짜 나쁜 습관인데.. LV2라는걸 감안해서 쉽게 접근하자고 생각함.
 * 무조건 정점을 찾는 규칙이 있을것이다 생각함.
 * 조금 생각해보니 규칙이 보임
 * 기본적으로 최소 2개의 그래프가 생긴다는걸 보고 생각이 남.
 * 도넛 -> IN: 2, OUT: 1
 * 막대 -> IN: 0~2, OUT: 0~1
 * 8자 -> IN: 1~3, OUT: 1~2
 * 추가한 정점 -> IN: 0, OUT: 2 이상
 * 즉 어떤 정점에 대해서 OUT - IN 이 2이상이면 무조건 새로운 정점이란 것.
 *
 */
public class Q258711_jhg {

    static Map<Integer, Node> nodes = new HashMap<>();

    public int[] solution(int[][] edges) {
        for (int[] edge : edges) {
            int out = edge[0];
            int in = edge[1];

            nodes.putIfAbsent(out, new Node(out));
            nodes.get(out).outCount();
            nodes.putIfAbsent(in, new Node(in));
            nodes.get(in).inCount();
        }

        Node newNode = findNewNode();
        int eightCount = 0;
        int stickCount = 0;

        for (Node node : nodes.values()) {
            if (node.isEightGraph()) {
                eightCount++;
            }

            if (node.isStickGraph()) {
                stickCount++;
            }
        }

        int donutsCount = newNode.outCount - stickCount - eightCount;

        return new int[] {newNode.nodeNumber, donutsCount, stickCount, eightCount};
    }

    private Node findNewNode() {
        for (Node node : nodes.values()) {
            if (node.isNewNode()) {
                return node;
            }
        }

        return new Node(0);
    }


    public static class Node {
        int inCount;
        int outCount;
        int nodeNumber;

        public Node(int nodeNumber) {
            this.nodeNumber = nodeNumber;
        }

        public void inCount(){
            this.inCount++;
        }

        public void outCount(){
            this.outCount++;
        }

        public boolean isNewNode() {
            return this.outCount - this.inCount > 1;
        }

        public boolean isEightGraph() {
            return this.inCount >= 2 && this.outCount == 2;
        }

        public boolean isStickGraph() {
            return this.inCount >= 1 && this.outCount == 0;
        }
    }
}
