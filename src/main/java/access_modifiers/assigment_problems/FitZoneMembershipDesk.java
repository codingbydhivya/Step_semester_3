abstract class MembershipPlan {

    protected double baseRate = 1000;

    public abstract double calculateFee();
}

class MonthlyPlan extends MembershipPlan {

    public double calculateFee() {
        return baseRate;
    }
}

class QuarterlyPlan extends MembershipPlan {

    public double calculateFee() {
        return baseRate * 3 * 0.90;
    }
}

class AnnualPlan extends MembershipPlan {

    public double calculateFee() {
        return baseRate * 12 * 0.75;
    }
}

class Member {

    private String name;

    Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Membership {

    enum Status {
        ACTIVE,
        FROZEN,
        EXPIRED
    }

    private Member member;
    private MembershipPlan plan;
    private Status status;

    Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.status = Status.ACTIVE;
    }

    public void checkIn() {

        if (status == Status.ACTIVE) {
            System.out.println(
                member.getName() +
                " checked in successfully."
            );
        } else {
            System.out.println(
                "Check-in denied: " +
                member.getName() +
                "'s membership is " +
                status + "."
            );
        }
    }

    public void freeze() {

        if (status == Status.EXPIRED) {
            System.out.println(
                "Cannot freeze an Expired membership."
            );
            return;
        }

        if (status == Status.FROZEN) {
            System.out.println(
                "Membership is already Frozen."
            );
            return;
        }

        status = Status.FROZEN;

        System.out.println(
            member.getName() +
            "'s membership frozen. Status: Frozen."
        );
    }

    public void unfreeze() {

        if (status == Status.EXPIRED) {
            System.out.println(
                "Cannot unfreeze an Expired membership."
            );
            return;
        }

        if (status == Status.FROZEN) {
            status = Status.ACTIVE;

            System.out.println(
                member.getName() +
                "'s membership unfrozen. Status: Active."
            );
        }
    }

    public void expire() {

        status = Status.EXPIRED;

        System.out.println(
            member.getName() +
            "'s membership expired. Status: Expired."
        );
    }
}

public class FitZoneMembershipDesk {

    public static void main(String[] args) {

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership =
            new Membership(
                asha,
                new QuarterlyPlan()
            );

        Membership raviMembership =
            new Membership(
                ravi,
                new MonthlyPlan()
            );

        System.out.printf(
            "Quarterly membership created for Asha. Fee: ₹%.2f. Status: Active.%n",
            new QuarterlyPlan().calculateFee()
        );

        System.out.printf(
            "Monthly membership created for Ravi. Fee: ₹%.2f. Status: Active.%n",
            new MonthlyPlan().calculateFee()
        );

        ashaMembership.checkIn();

        ashaMembership.freeze();

        ashaMembership.checkIn();

        raviMembership.expire();

        raviMembership.freeze();
    }
}
