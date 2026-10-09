
import java.util.*;

public class Problem3MostPopular {

    static Object[] mostPopular(List<String> orders) {
        Map<String, Integer> counts = new HashMap<>();

        for (String item : orders) {
            counts.put(item, counts.getOrDefault(item, 0) + 1);
        }

        String bestItem = orders.get(0);
        int bestCount = counts.get(bestItem);

        for (String item : orders) {
            if (counts.get(item) > bestCount) {
                bestItem = item;
                bestCount = counts.get(item);
            }
        }

        return new Object[]{bestItem, bestCount};
    }

    public static void main(String[] args) {
        List<String> orders = Arrays.asList(
            "dosa", "idli", "vada", "dosa",
            "idli", "dosa", "tea"
        );

        Object[] result = mostPopular(orders);
        System.out.println("(" + result[0] + ", " + result[1] + ")");
    }
}