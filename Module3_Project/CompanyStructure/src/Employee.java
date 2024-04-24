abstract class Employee {
    private String name;
    private double baseSalary;
    private int employeeID;
    private static int countID;
    private Employee manager;

    public Employee(String name, double baseSalary){
        this.name = name;
        this.baseSalary = baseSalary;
        countID += 1;
        this.employeeID = countID;
    }

     public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double newSalary){
        this.baseSalary = newSalary;
    }

    public String getName() {
        return name;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public Employee getManager(){
        return manager; // should manage through an interface
    }

    public void setManager(Employee manager){
        this.manager = manager;
    }

    public boolean equals(Employee other){
        return this.employeeID == other.employeeID;
    }

    public String toString(){
        return employeeID + " " + name;
    }

    abstract String employeeStatus();

}
