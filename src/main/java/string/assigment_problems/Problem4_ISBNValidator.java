
public class Problem4_ISBNValidator {

    static String normalizeCode(String raw) {

        String code = raw.trim();

        if (code.length() >= 3) {

            String publisher =
                code.substring(0, 3).toUpperCase();

            String remaining =
                code.substring(3);

            code = publisher + remaining;
        }

        return code;
    }


    static String validateAndFormat(String code) {

        if (code.length() != 13) {

            return "Invalid: wrong length";
        }


        // Check publisher code
        for (int i = 0; i < 3; i++) {

            if (!Character.isLetter(code.charAt(i))) {

                return "Invalid: publisher code must be 3 letters";
            }
        }


        // Check remaining 10 characters
        for (int i = 3; i < 13; i++) {

            if (!Character.isDigit(code.charAt(i))) {

                return "Invalid: body must contain only digits";
            }
        }


        StringBuilder result =
            new StringBuilder();

        result.append("[");
        result.append(code.substring(0, 3));
        result.append("] YEAR: ");
        result.append(code.substring(3, 7));
        result.append(" | CATALOG: ");
        result.append(code.substring(7, 13));

        return result.toString();
    }


    public static void main(String[] args) {

        String code1 =
            normalizeCode(" pen2026004251 ");

        System.out.println(
            validateAndFormat(code1)
        );


        String code2 =
            normalizeCode("12N2026004251");

        System.out.println(
            validateAndFormat(code2)
        );
    }
}
