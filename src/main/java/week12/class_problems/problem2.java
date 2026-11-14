
import java.util.*;

public class problem2 {

    static String shiftNotice(String text, int k) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                ch = (char) ((ch - 'a' + k) % 26 + 'a');
            }
            else if (ch >= 'A' && ch <= 'Z') {
                ch = (char) ((ch - 'A' + k) % 26 + 'A');
            }

            result.append(ch);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();
        int k = sc.nextInt();

        System.out.println(shiftNotice(text, k));

        sc.close();
    }
}
