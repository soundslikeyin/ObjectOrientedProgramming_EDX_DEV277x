abstract class TechnicalEmployee extends Employee {
    private int successfulCheckIns; //we may need a setter method for this if we want to make this private

    public TechnicalEmployee(String name){
        super(name, 75000);
    }

    public void setSuccessfulCheckIns(int checkIns){
        successfulCheckIns = checkIns;
    }

    public int getSuccessfulCheckIns(){
        return successfulCheckIns;
    }

    public String employeeStatus(){
        return this.toString() + " has " + successfulCheckIns + " successful check ins";
    }

}