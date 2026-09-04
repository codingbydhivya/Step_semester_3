class Participant {
    String name;
    String teamName;
    boolean registered;

    public Participant(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.registered = true;
    }

    public Participant(String name) {
        this(name, "Unassigned");
    }

    public void printStatus() {
        System.out.println(name + " " + teamName + " " + registered);
    }
}

public class Main {
    public static void main(String[] args) {

        String[] names = {"Arun", "Priya", "Rahul", "Meena"};
        String[] teamNames = {"CodeCrew", "", "ByteForce", ""};

        for (int i = 0; i < names.length; i++) {

            Participant p;

            if (teamNames[i].isEmpty()) {
                p = new Participant(names[i]);
            } else {
                p = new Participant(names[i], teamNames[i]);
            }

            p.printStatus();
        }
    }
}
