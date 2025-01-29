import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    // Function to validate credit card number using Luhn algorithm
    public static boolean validateCreditCardNumber(String cardNumber) {
        if (!cardNumber.matches("^[0-9]{13,19}$")) return false;

        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    // Function to determine the card issuer (bandeira)
    public static String getCardIssuer(String cardNumber) {
        Pattern visa = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
        Pattern visa16 = Pattern.compile("^4[0-9]{15}$");
        Pattern mastercard = Pattern.compile("^5[1-5][0-9]{14}$");
        Pattern amex = Pattern.compile("^3[47][0-9]{13}$");
        Pattern discover = Pattern.compile("^6(?:011|5[0-9]{2})[0-9]{12}$");
        Pattern diners = Pattern.compile("^3(?:0[0-5]|[68][0-9])[0-9]{11}$");
        Pattern jcb = Pattern.compile("^(?:2131|1800|35\\d{3})\\d{11}$");
        Pattern enroute = Pattern.compile("^(2014|2149)\\d{11}$");
        Pattern voyager = Pattern.compile("^8699[0-9]{11}$");
        Pattern hipercard = Pattern.compile("^(606282|3841)[0-9]{10,12}$");
        Pattern aura = Pattern.compile("^(5078|5029|6362|6370)[0-9]{12,15}$");

        if (visa.matcher(cardNumber).matches()) return "visa";
        if (visa16.matcher(cardNumber).matches()) return "visa 16 digits";
        if (mastercard.matcher(cardNumber).matches()) return "mastercard";
        if (amex.matcher(cardNumber).matches()) return "amex";
        if (discover.matcher(cardNumber).matches()) return "discover";
        if (diners.matcher(cardNumber).matches()) return "diners";
        if (jcb.matcher(cardNumber).matches()) return "jcb";
        if (enroute.matcher(cardNumber).matches()) return "enroute";
        if (voyager.matcher(cardNumber).matches()) return "voyager";
        if (hipercard.matcher(cardNumber).matches()) return "hipercard";
        if (aura.matcher(cardNumber).matches()) return "aura";

        return "unknown";
    }

    // Example usage
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your credit card number: ");
        String cardNumber = scanner.nextLine();
        
        if (validateCreditCardNumber(cardNumber)) {
            System.out.println("Valid card number");
            System.out.println("Card issuer: " + getCardIssuer(cardNumber));
        } else {
            System.out.println("Invalid card number");
        }
        
        scanner.close();
    }
}