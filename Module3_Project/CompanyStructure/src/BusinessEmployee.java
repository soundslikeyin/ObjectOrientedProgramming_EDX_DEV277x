abstract class BusinessEmployee extends Employee{
    private double bonusBudget; // we may need a setter for this to keep it private

    public BusinessEmployee(String name){
        super(name, 50000);
    }

    public double getBonusBudget(){
        return bonusBudget;
    }

    public void setBonusBudget(double newBudget){
        bonusBudget = newBudget;
    }

    public String employeeStatus(){
        return this.toString() + " with a budget of " + bonusBudget;
    }

}
