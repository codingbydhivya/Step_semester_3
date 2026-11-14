
import java.util.*;

public class problem5 {

    static class Participant {
        String name;
        int score;

        Participant(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    static void insertionSort(Participant[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Participant key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].score < key.score) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Participant[] arr = new Participant[n];

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int score = sc.nextInt();

            arr[i] = new Participant(name, score);
        }

        insertionSort(arr);

        int rank = 1;

        for (int i = 0; i < n; i++) {

            if (i > 0 && arr[i].score != arr[i - 1].score) {
                rank = i + 1;
            }

            System.out.println(rank + " " +
                               arr[i].name + " " +
                               arr[i].score);
        }

        sc.close();
    }
}
