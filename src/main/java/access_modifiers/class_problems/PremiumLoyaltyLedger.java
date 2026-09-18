import java.util.Arrays;

class GymMember {

    private String memberId;
    protected int monthlyFee;

    private int[] lateFeeHistory = new int[10];
    private int feeCount;

    public GymMember(
            String memberId,
            int monthlyFee) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    protected void chargeLateFee(int amount) {

        lateFeeHistory[feeCount] = amount;
        feeCount++;
    }

    public int[] getLateFeeHistory() {

        return Arrays.copyOf(
                lateFeeHistory, feeCount);
    }

    public int getTotalLateFees() {

        int total = 0;

        for (int i = 0; i < feeCount; i++) {
            total += lateFeeHistory[i];
        }

        return total;
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

    @Override
    protected void chargeLateFee(int amount) {

        super.chargeLateFee(amount / 2);
    }
}

public class PremiumLoyaltyLedger {

    public static void main(String[] args) {

        PremiumMember p =
                new PremiumMember(
                        "MEM5",
                        2000,
                        "Coach Riya");

        p.chargeLateFee(200);

        System.out.println(
                p.getTotalLateFees()
        );

        int[] history =
                p.getLateFeeHistory();

        history[0] = 999;

        System.out.println(
                Arrays.toString(
                        p.getLateFeeHistory()
                )
        );
    }
}