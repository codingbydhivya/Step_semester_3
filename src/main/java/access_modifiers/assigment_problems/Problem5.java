
class Entry {
    String name;
    int marks;

    Entry(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class Problem5 {

    static Entry[] leaderboard(Entry[] entries) {
        int n = entries.length;

        int[] count = new int[101];
        Entry[] output = new Entry[n];

        // Count occurrences
        for (int i = 0; i < n; i++) {
            int key = 100 - entries[i].marks;
            count[key]++;
        }

        // Build cumulative count
        for (int i = 1; i < 101; i++) {
            count[i] += count[i - 1];
        }

        // Place entries from right to left
        for (int i = n - 1; i >= 0; i--) {
            int key = 100 - entries[i].marks;

            output[count[key] - 1] = entries[i];
            count[key]--;
        }

        return output;
    }

    public static void main(String[] args) {

        Entry[] entries = {
            new Entry("Asha", 90),
            new Entry("Ravi", 75),
            new Entry("Neha", 90),
            new Entry("Kiran", 100),
            new Entry("Meera", 75)
        };

        Entry[] result = leaderboard(entries);

        for (Entry e : result) {
            System.out.println(e.name + " " + e.marks);
        }
    }
}
