class GymMember {

    private int monthlyFee;
    private int feesPaid;

    private static int nextMembershipNumber = 2001;

    public final String membershipNumber;

    public GymMember(int monthlyFee) {

        this.monthlyFee = monthlyFee;

        membershipNumber =
                "GYM-" + nextMembershipNumber;

        nextMembershipNumber++;
    }

    public void payFee(int amount) {

        feesPaid += amount;
    }

    public void payFee(int amount, String mode) {

        payFee(amount);
    }

    public int getFeesPaid() {

        return feesPaid;
    }

    public static boolean isValidReferralCode(
            String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'G'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {

        return nextMembershipNumber - 2001;
    }
}

class GroupClassMember extends GymMember {

    private String className;

    public GroupClassMember(
            int monthlyFee,
            String className) {

        super(monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}

public class WeeklyCheckInSettlement {

    public static String processWeeklyCheckIn(
            GymMember[] members) {

        StringBuilder result =
                new StringBuilder();

        for (GymMember member : members) {

            if (member == null) {
                continue;
            }

            if (member instanceof GroupClassMember) {

                GroupClassMember group =
                        (GroupClassMember) member;

                result.append("Group Class: ")
                        .append(group.getClassName());

            } else {

                result.append("Regular Member");
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        GymMember m1 =
                new GymMember(1000);

        System.out.println(
                m1.membershipNumber
        );

        System.out.println(
                GymMember.getMembersEnrolled()
        );

        System.out.println(
                GymMember.isValidReferralCode("G45B")
        );

        System.out.println(
                GymMember.isValidReferralCode("G4B")
        );

        System.out.println(
                GymMember.isValidReferralCode("X45B")
        );

        m1.payFee(500);
        m1.payFee(500, "UPI");

        System.out.println(
                m1.getFeesPaid()
        );

        GroupClassMember group =
                new GroupClassMember(
                        1500,
                        "Zumba");

        GymMember[] members = {
                m1, null, group
        };

        System.out.println(
                processWeeklyCheckIn(members)
        );
    }
}