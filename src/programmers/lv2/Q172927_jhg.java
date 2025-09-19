package programmers.lv2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 그냥 조건 자체가 한 번에 5개를 캘 수 있는거 부터 보고 그리디를 생각.
 */
public class Q172927_jhg {
    public int solution(int[] picks, String[] minerals) {
        int availableMineral = Math.min(minerals.length, Arrays.stream(picks).sum() * 5);
        List<Group> groups = Group.of(minerals, availableMineral);
        List<Mineral> pickList = Mineral.ofPicks(picks);
        return getPower(groups, pickList);
    }

    private int getPower(List<Group> groups, List<Mineral> picks) {
        int power = 0;

        for (int i = 0; i < groups.size() && i < picks.size(); i++) {
            Group group = groups.get(i);
            Mineral pick = picks.get(i);
            power += group.getPower(pick);
        }

        return power;
    }

    public static class Group extends HashMap<Mineral, Integer> {

        public static List<Group> of(String[] minerals, int availableMineral) {
            List<Group> groups = new ArrayList<>();
            for (int i = 0; i < availableMineral; i += 5) {
                Group group = new Group();
                for (int j = i; j < i + 5 && j < availableMineral; j++) {
                    Mineral mineral = Mineral.valueOfName(minerals[j]);
                    group.put(mineral, group.getOrDefault(mineral, 0) + 1);
                }
                groups.add(group);
            }

            return groups.stream()
                    .sorted(Comparator.comparing(Group::getWeight).reversed())
                    .collect(Collectors.toList());
        }

        public int getWeight() {
            return this.entrySet().stream()
                    .map(entry -> {
                        Mineral mineral = entry.getKey();
                        Integer count = entry.getValue();
                        return mineral.hardness * count;
                    })
                    .reduce(0, Integer::sum);
        }

        public int getPower(Mineral pick) {
            return this.entrySet().stream()
                    .map(entrySet -> {
                        Mineral mineral = entrySet.getKey();
                        Integer count = entrySet.getValue();
                        return mineral.getHardness(pick) * count;
                    })
                    .reduce(0, Integer::sum);
        }
    }

    public enum Mineral {
        DIAMOND(25, "diamond"),
        IRON(5, "iron"),
        STONE(1, "stone"),
        NONE(0, "none");
        ;

        private final int hardness;
        private final String name;

        Mineral(int hardness, String name) {
            this.hardness = hardness;
            this.name = name;
        }

        public static Mineral valueOfName(String name) {
            for (Mineral mineral : values()) {
                if (mineral.name.equals(name)) {
                    return mineral;
                }
            }

            return NONE;
        }

        public static List<Mineral> ofPicks(int[] picks) {
            List<Mineral> minerals = new ArrayList<>();
            for (int i = 0; i < picks[0]; i++) {
                minerals.add(Mineral.DIAMOND);
            }
            for (int i = 0; i < picks[1]; i++) {
                minerals.add(Mineral.IRON);
            }
            for (int i = 0; i < picks[2]; i++) {
                minerals.add(Mineral.STONE);
            }
            return minerals;
        }

        public int getHardness(Mineral pick) {
            return (int) Math.ceil((double) this.hardness / pick.hardness);
        }
    }
}
