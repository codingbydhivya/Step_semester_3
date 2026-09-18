class GymMember {

    private String memberId;
    protected int monthlyFee;
    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {

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

    public static String signUpBatch(
            String[] memberIds, int monthlyFee) {

        int rejected = 0;

        for (String id : memberIds) {
            try {
                new GymMember(id, monthlyFee);
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Signed Up: " + (memberIds.length - rejected)
                + " | Rejected: " + rejected;
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
}

public class GymMembershipFoundation {

    public static void main(String[] args) {

        try {
            GymMember g =
                    new GymMember("GM1", 1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Construction rejected");
        }

        PremiumMember p =
                new PremiumMember(
                        "MEM01", 2000, "Coach Riya");

        p.attendSession();
        p.attendSession();

        System.out.println(
                p.getSessionsAttended()
        );

        String[] ids = {
                "MEM1", "GM1", "MEM2", " ", "MEM3"
        };

        System.out.println(
                GymMember.signUpBatch(ids, 1000)
        );
    }
}