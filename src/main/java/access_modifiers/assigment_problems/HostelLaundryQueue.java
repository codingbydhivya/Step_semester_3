abstract class WashType {
    public abstract int getDuration();
    public abstract double getCharge();
    public abstract String getName();
}

class QuickWash extends WashType {
    public int getDuration() {
        return 30;
    }

    public double getCharge() {
        return 20;
    }

    public String getName() {
        return "Quick";
    }
}

class NormalWash extends WashType {
    public int getDuration() {
        return 45;
    }

    public double getCharge() {
        return 30;
    }

    public String getName() {
        return "Normal";
    }
}

class HeavyWash extends WashType {
    public int getDuration() {
        return 60;
    }

    public double getCharge() {
        return 45;
    }

    public String getName() {
        return "Heavy";
    }
}

class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class WashingMachine {
    private String machineId;
    private boolean busy;

    WashingMachine(String machineId) {
        this.machineId = machineId;
        this.busy = false;
    }

    public String getMachineId() {
        return machineId;
    }

    public boolean isBusy() {
        return busy;
    }

    public void startMachine() {
        busy = true;
    }

    public void completeMachine() {
        busy = false;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    WashCycle(Student student, WashingMachine machine,
              WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public void start() {
        machine.startMachine();

        System.out.printf(
            "%s wash started on %s for %s (%d min). Charge: ₹%.2f%n",
            washType.getName(),
            machine.getMachineId(),
            student.getName(),
            washType.getDuration(),
            washType.getCharge()
        );
    }

    public void complete() {
        machine.completeMachine();

        System.out.println(
            machine.getMachineId() + " cycle completed."
        );

        System.out.println(
            machine.getMachineId() + " is now free."
        );
    }
}

public class HostelLaundryQueue {

    public static void startWash(Student student,
                                 WashingMachine machine,
                                 WashType washType) {

        if (machine.isBusy()) {
            System.out.println(
                machine.getMachineId() + " is currently busy."
            );
            return;
        }

        WashCycle cycle =
            new WashCycle(student, machine, washType);

        cycle.start();
    }

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 =
            new WashingMachine("M1");

        WashingMachine m2 =
            new WashingMachine("M2");

        startWash(asha, m1, new QuickWash());

        startWash(ravi, m1, new HeavyWash());

        startWash(ravi, m2, new HeavyWash());

        WashCycle cycle =
            new WashCycle(asha, m1, new QuickWash());

        cycle.complete();

        startWash(neha, m1, new NormalWash());
    }
}
