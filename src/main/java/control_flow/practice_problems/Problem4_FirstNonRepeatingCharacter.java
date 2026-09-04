
import java.util.HashMap;

class FirstNonRepeatingCharacter {

    char findFirstNonRepeatingChar(String text) {

        HashMap<Character, Integer> frequency = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            frequency.put(
                    ch,
                    frequency.getOrDefault(ch, 0) + 1
            );
        }

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (frequency.get(ch) == 1) {
                return ch;
            }
        }

        return '\0';
    }
}

public class Problem4_FirstNonRepeatingCharacter {

    public static void main(String[] args) {

        FirstNonRepeatingCharacter finder =
                new FirstNonRepeatingCharacter();

        String text = "swiss";

        char result = finder.findFirstNonRepeatingChar(text);

        if (result == '\0') {
            System.out.println("No Non-Repeating Character Found");
        } else {
            System.out.println(
                    "First Non-Repeating Character: '" + result + "'"
            );
        }
    }
}