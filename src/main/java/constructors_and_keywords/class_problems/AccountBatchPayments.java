class FeeAccount {
    public void pay(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {
    @Override
    public void pay(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}

public class AccountBatchPayments {

    public void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            account.pay(amount);
        } else {
            account.pay(amount);
        }
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        double amount = 60000;
        int hostelCount = 0;
        int dayScholarCount = 0;

        AccountBatchPayments processor = new AccountBatchPayments();

        for (FeeAccount account : accounts) {
            processor.processPayment(account, amount);

            if (account instanceof HostelFeeAccount) {
                hostelCount++;
            } else {
                dayScholarCount++;
            }
        }

        System.out.println(
            "Hostel accounts processed: " + hostelCount +
            " | Day-scholar accounts processed: " + dayScholarCount
        );
    }
}