import java.util.ArrayList;

public class Accountant extends BusinessEmployee{
    private TechnicalLead teamSupported;

    public Accountant(String name){
        super(name);
        setBonusBudget(0);
    }

    public TechnicalLead getTeamSupported(){
        return teamSupported;
    }

    public void supportTeam(TechnicalLead lead){
        teamSupported = lead;
        //ArrayList<SoftwareEngineer> directReports = lead.getDirectReports();
        double newBonusBudget = getBonusBudget();

        for (int i = 0; i < lead.getDirectReports().size(); i++){
            newBonusBudget += lead.getDirectReports().get(i).getBaseSalary();
        }
        setBonusBudget(newBonusBudget * 1.1);
    }

    public boolean approveBonus(double bonus){
        if ((teamSupported == null) || (bonus > getBonusBudget())){
            return false;
        }
        else {
            return true;
        }
    }

    public String employeeStatus(){
        return super.employeeStatus() + " is supporting " + teamSupported.getName();
    }

}
