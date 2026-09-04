class BankTransactionReference {

    String normalizeReference(String raw) {
        String reference = raw.trim();

        if (reference.length() < 3) {
            return reference.toUpperCase();
        }

        String bankCode = reference.substring(0, 3).toUpperCase();
        String remaining = reference.substring(3);

        return bankCode + remaining;
    }

    String validateAndFormat(String reference) {

        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        for (int i = 3; i < reference.length(); i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        String bankCode = reference.substring(0, 3);
        String date = reference.substring(3, 9);
        String sequence = reference.substring(9);

        String formattedDate =
            date.substring(0, 2) + "/" +
            date.substring(2, 4) + "/" +
            date.substring(4, 6);

        StringBuilder result = new StringBuilder();

        result.append("[")
              .append(bankCode)
              .append("] DATE: ")
              .append(formattedDate)
              .append(" | SEQ: ")
              .append(sequence);

        return result.toString();
    }
}

public class Problem5_BankTransactionReference {

    public static void main(String[] args) {

        BankTransactionReference generator =
            new BankTransactionReference();

        String normalized =
            generator.normalizeReference(" hdf03022600042 ");

        System.out.println(
            generator.validateAndFormat(normalized)
        );

        normalized =
            generator.normalizeReference("12F03022600042");

        System.out.println(
            generator.validateAndFormat(normalized)
        );
    }
}