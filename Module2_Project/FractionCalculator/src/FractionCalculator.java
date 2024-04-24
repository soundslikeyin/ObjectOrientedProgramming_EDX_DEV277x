import java.util.Scanner;

public class FractionCalculator {

    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);

        introduction();
        String operator = getOperation(input);


        while (!operator.toUpperCase().equals("Q")){

            Fraction firstFraction = getFraction(input);
            System.out.println("you've entered your first fraction: " + firstFraction + ".");
            Fraction secondFraction = getFraction(input);
            System.out.println("you've entered your second fraction: " + secondFraction + ".");


            switch(operator) {
                case "+":
                    System.out.println("You've selected: ADD");
                    Fraction result = firstFraction.add(secondFraction);
                    System.out.println(firstFraction + " ADD " + secondFraction + " = " + result );
                    System.out.println("-".repeat(20));
                    operator = getOperation(input);
                    break;

                case "-":
                    System.out.println("You've selected: MINUS");
                    result = firstFraction.subtract(secondFraction);
                    System.out.println(firstFraction + " MINUS " + secondFraction + " = " + result );
                    System.out.println("-".repeat(20));
                    operator = getOperation(input);
                    break;

                case "/":
                    System.out.println("You've selected: DIVISION");
                    if (secondFraction.getNumerator() == 0) {
                        System.out.println(firstFraction + " DIVIDE BY " + secondFraction + " = Undefined");
                    } else if (firstFraction.getNumerator() == 0){
                        System.out.println(firstFraction + " DIVIDE BY " + secondFraction + " = 0");
                    }
                    else {
                        result = firstFraction.divide(secondFraction);
                        System.out.println(firstFraction + " DIVIDE BY " + secondFraction + " = " + result);
                    }
                    System.out.println("-".repeat(20));
                    operator = getOperation(input);
                    break;

                case "*":
                    System.out.println("You've selected: MULTIPLY");
                    result = firstFraction.multiply(secondFraction);
                    System.out.println(firstFraction + " MULTIPLY " + secondFraction + " = " + result );
                    System.out.println("-".repeat(20));
                    operator = getOperation(input);
                    break;

                case "=":
                    System.out.println("You've selected: EQUAL");
                    System.out.print(firstFraction + " equals " + secondFraction + " is ");
                    boolean equals = firstFraction.equals(secondFraction);
                    System.out.println(equals);
                    System.out.println("-".repeat(20));
                    operator = getOperation(input);
                    break;
            }

        }
        System.out.println("Thank you for using the calculator. Good Bye.");
    }

    public static void introduction() {
        System.out.println("This program is a fraction calculator");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers: ");
        System.out.println("-".repeat(20));
    }

    public static String getOperation(Scanner input) {
        String operation = "";

        while (!operation.equals("+") && !operation.equals("-") && !operation.equals("/") && !operation.equals("*") && !operation.equals("=") && !operation.toUpperCase().equals("Q")) {

            if (!operation.equals("")) {
                System.out.print("Invalid input. ");
            }

            System.out.print("Please enter an operation (+, -, /, *, = or Q to quit) ");
            operation = input.nextLine();
        }

        return operation;
    }

    public static Fraction getFraction(Scanner input) {

        System.out.print("Please enter a fraction (a/b) or an integer (a), where a is any integer and b is any integer > 0: ");
        String testFraction = input.nextLine();

        while (!validFraction(testFraction)) {
            System.out.print("Invalid fraction. ");
            System.out.print("Please enter a fraction (a/b) or an integer (a), where a is any integer and b is any integer > 0: ");
            testFraction = input.nextLine();
        }

        Fraction newFraction;

        if (testFraction.contains("/")) {
            String numerator = testFraction.substring(0, (testFraction.indexOf("/")));
            String denominator = testFraction.substring(testFraction.indexOf("/") + 1);
            newFraction = new Fraction(Integer.parseInt(numerator), Integer.parseInt(denominator));
        }
        else {
            newFraction = new Fraction(Integer.parseInt(testFraction));
        }

        return newFraction;
    }

    public static boolean validFraction(String test){

        if (test == null) {
            return false; // fraction is null
        }

        if (test.startsWith("-")) {
            if (test.substring(1).contains("-")) {
                return false;
            }
        } else {
            if (test.contains("-")){
                return false;
            }
        }

        if (test.contains("/")) {
            String numerator = test.substring(0,(test.indexOf("/")));
            String denominator = test.substring(test.indexOf("/")+1);

            if ((isNumber(numerator)) && (isNumber(denominator)))
                return !denominator.equals("0");  // test for whether denominator is 0
            else{
                return false;
            }
        }
        else {  //if not fraction check if it is number or contains empty strings
            return isNumber(test);
        }
    }

    private static boolean isNumber(String test) {

        if (test.startsWith("-")) {
            test = test.substring(1);
        }

        try {
            Integer.parseInt(test);
        } catch (NumberFormatException e) {
            return false;
        }

        /*
        if (test.matches("[0-9]+")) {
            return true;
        } else {
            return false;
        }*/

        for (int i = 0; i < test.length(); i++) {
            if ((test.charAt(i) != '0')&&
                    (test.charAt(i) != '1') &&
                    (test.charAt(i) != '2') &&
                    (test.charAt(i) != '3') &&
                    (test.charAt(i) != '4') &&
                    (test.charAt(i) != '5') &&
                    (test.charAt(i) != '6') &&
                    (test.charAt(i) != '7') &&
                    (test.charAt(i) != '8') &&
                    (test.charAt(i) != '9') ) {
                    return false;
                }
        }
        return true;
    }
}
