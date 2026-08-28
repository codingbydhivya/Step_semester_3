
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

public class Problem5_WordFrequency {

    static void printFilteredWordFrequency(String feedback) {

        String[] stopWords = {
            "the", "was", "and", "a", "is", "of", "in"
        };

        String cleaned = feedback.toLowerCase();

        cleaned = cleaned.replace(".", "");
        cleaned = cleaned.replace(",", "");

        String[] words = cleaned.split("\\s+");

        HashMap<String, Integer> frequency =
            new HashMap<>();


        // Count words
        for (int i = 0; i < words.length; i++) {

            String word = words[i];

            boolean isStopWord = false;

            for (int j = 0; j < stopWords.length; j++) {

                if (word.equals(stopWords[j])) {

                    isStopWord = true;
                    break;
                }
            }

            if (isStopWord) {
                continue;
            }

            if (frequency.containsKey(word)) {

                frequency.put(
                    word,
                    frequency.get(word) + 1
                );

            } else {

                frequency.put(word, 1);
            }
        }


        // Convert map to list
        ArrayList<Map.Entry<String, Integer>> entries =
            new ArrayList<>(frequency.entrySet());


        // Sort by frequency in descending order
        Collections.sort(
            entries,
            new Comparator<Map.Entry<String, Integer>>() {

                @Override
                public int compare(
                    Map.Entry<String, Integer> a,
                    Map.Entry<String, Integer> b
                ) {

                    return b.getValue() - a.getValue();
                }
            }
        );


        // Print result
        for (Map.Entry<String, Integer> entry : entries) {

            System.out.println(
                entry.getKey() + ": "
                + entry.getValue()
            );
        }
    }


    public static void main(String[] args) {

        printFilteredWordFrequency(
            "The mentor was great, the session was great and clear."
        );
    }
}
