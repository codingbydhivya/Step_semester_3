class GymMember {

    private String memberId;
    protected int monthlyFee;
    private int sessionsAttended;

    public GymMember(
            String memberId,
            int monthlyFee) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println(
                "Standard Member | Sessions: "
                        + sessionsAttended
        );
    }
}

class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public void displayInfo() {
        System.out.println(
                "Premium Member | Trainer: "
                        + trainerName
                        + " | Sessions: "
                        + getSessionsAttended()
        );
    }
}

public class MonthlyAttendanceAnnouncer {

    public static String batchPrint(
            GymMember[] members) {

        StringBuilder result =
                new StringBuilder();

        for (GymMember member : members) {

            // Polymorphic call
            member.displayInfo();

            if (member instanceof PremiumMember) {

                PremiumMember premium =
                        (PremiumMember) member;

                result.append("Premium | Trainer: ")
                        .append(premium.getTrainerName())
                        .append(" | Sessions: ")
                        .append(premium.getSessionsAttended())
                        .append(" [Trainer via downcast: ")
                        .append(premium.getTrainerName())
                        .append("] | ");

            } else {

                result.append("Standard | Sessions: ")
                        .append(member.getSessionsAttended())
                        .append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        GymMember standard =
                new GymMember("MEM6", 1000);

        PremiumMember premium =
                new PremiumMember(
                        "MEM7",
                        2000,
                        "Coach Riya");

        GymMember[] members = {
                standard, premium
        };

        System.out.println(
                batchPrint(members)
        );

        // This causes ClassCastException:
        //
        // PremiumMember bad = (PremiumMember) standard;
    }
}