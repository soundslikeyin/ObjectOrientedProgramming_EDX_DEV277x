public class Fraction {
    // fields
    private int numerator;
    private int denominator;

    //constructor
    public Fraction(int numerator, int denominator){
        if ( denominator == 0){
            throw new IllegalArgumentException("denominator cannot be 0");
        }
        else if (denominator < 0) {
            this.numerator = (numerator)*-1;
            this.denominator = Math.abs(denominator);
        }
        else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    public Fraction(int numerator){
        this(numerator, 1);
    }

    public Fraction(){
        this(0);
    }

    //behavior or Methods
    public int getNumerator(){
        return this.numerator;
    }

    public int getDenominator(){
        return this.denominator;
    }

    public String toString(){
        if (this.numerator % this.denominator == 0) {
            return String.valueOf(numerator / denominator);
        }
        return this.numerator + "/" + this.denominator;
    }

    public double toDouble(){
        return (double) (numerator / denominator);
    }

    public Fraction add(Fraction other){
        int newNumerator = (this.denominator * other.numerator) + (this.numerator * other.denominator);
        int newDenominator = this.denominator * other.denominator;
        Fraction result = new Fraction(newNumerator, newDenominator);
        result.toLowestTerms();
        return result;
    }

    public Fraction subtract(Fraction other){
        int newNumerator = (this.numerator * other.denominator) - (this.denominator * other.numerator) ;
        int newDenominator = this.denominator * other.denominator;
        Fraction result = new Fraction(newNumerator, newDenominator);
        result.toLowestTerms();

        return result;
    }

    public Fraction multiply(Fraction other){
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        Fraction result = new Fraction(newNumerator, newDenominator);
        result.toLowestTerms();

        return result;
    }

    public Fraction divide(Fraction other){

        if (other.numerator == 0 ) {
            throw new IllegalArgumentException("Invalid division. Cannot divide by 0");
        }
        else {
            int newNumerator = this.numerator * other.denominator;
            int newDenominator = this.denominator * other.numerator;

            if (newDenominator == 0){
                throw new IllegalArgumentException("Invalid division. Cannot divide by 0");
            } else {
                Fraction result = new Fraction(newNumerator, newDenominator);
                result.toLowestTerms();

                return result;
            }
        }
    }

    public boolean equals(Object other) {
        if (other instanceof Fraction) {
            Fraction otherFraction = (Fraction) other;
            otherFraction.toLowestTerms();
            this.toLowestTerms();

            return (this.numerator == otherFraction.numerator) && (this.denominator == otherFraction.denominator);
        }
        else {
            return false;
        }
    }

    public void toLowestTerms(){
        int gcd = gcd(this.numerator, this.denominator);

        this.numerator = this.numerator / gcd;
        this.denominator = this.denominator / gcd;

    }

    private static int gcd(int numA, int numB){
        while (numA!=0 && numB!=0) {
            int r = numA % numB;
            numA = numB;
            numB = r;
        }
        return numA;
    }

}
