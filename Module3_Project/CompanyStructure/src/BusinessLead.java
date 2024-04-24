import java.util.ArrayList;

public class BusinessLead extends BusinessEmployee{
    private int headCount;
    private ArrayList<Accountant> directReports = new ArrayList<>();

    public BusinessLead(String name){
        super(name);
        setBaseSalary(getBaseSalary()*2);
        headCount = 10;
    }

    public boolean hasHeadCount(){
        return directReports.size() < headCount;
    }


    public boolean addReport(Accountant a, TechnicalLead teamSupported){
        if (hasHeadCount()) {
            directReports.add(a); // add accountant to business lead direct reports
            a.supportTeam(teamSupported); // add technical lead to accountant's team supported
            setBonusBudget(getBonusBudget() + 1.1*a.getBaseSalary()); // increase bonus budget by 1.1 times base salary of a
            a.setManager(this);
            teamSupported.setManager(this);
            return true;
        } else return false;
    }

    public boolean requestBonus(Employee e, double bonus) {
        if (bonus<getBonusBudget()){
            e.setBaseSalary(e.getBaseSalary() + bonus);
            setBonusBudget(getBonusBudget() - bonus);
            return true;
        }
        else return false;
    }

    public boolean approveBonus(Employee e, double bonus){
        TechnicalLead manager = (TechnicalLead) e.getManager();

        for (int i = 0; i < directReports.size(); i++){
            if (directReports.get(i).getTeamSupported().equals(manager))  { // if this business lead manages an accountant that manages the employee's manager
                if (directReports.get(i).approveBonus(bonus)){
                    return true;
                }
                else return false;
            }
        }
        return false;
    }

    public String getTeamStatus(){
        StringBuilder teamStatus = new StringBuilder(this.employeeStatus());

        if (directReports.size() > 0) {
            teamStatus.append(" and is managing: ");
            for (int i = 0; i < directReports.size(); i++ ) {
                teamStatus.append("\n\t");
                teamStatus.append(i+1);
                teamStatus.append(". ");
                teamStatus.append(directReports.get(i).employeeStatus());
            }
        } else {
            teamStatus.append(" and no direct reports yet.");
        }
        return teamStatus.toString();
    }

}
