class CustomerName {

    String reverseCustomerName(String customerName) {

        char[] characters = customerName.toCharArray();

        StringBuilder reversed = new StringBuilder();

        for (int i = characters.length - 1; i >= 0; i--) {
            reversed.append(characters[i]);
        }

        return reversed.toString();
    }
}

public class Problem5_ReverseCustomerName {

    public static void main(String[] args) {

        String customerName = "Sunil";

        CustomerName customer = new CustomerName();

        String reversed =
                customer.reverseCustomerName(customerName);

        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reversed);
    }
}