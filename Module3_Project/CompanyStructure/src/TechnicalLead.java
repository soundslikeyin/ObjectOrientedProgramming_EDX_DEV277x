import java.util.ArrayList;

public class TechnicalLead extends TechnicalEmployee{
    private int headCount;
    private ArrayList<SoftwareEngineer> directReports = new ArrayList<>();
    //private BusinessLead businessLead;

    public TechnicalLead(String name){
        super(name);
        setBaseSalary(getBaseSalary() * 1.3);
        setSuccessfulCheckIns(0);
        headCount = 4;
    }

    public boolean hasHeadCount(){
        return directReports.size() < headCount;
    }

    public boolean addReport(SoftwareEngineer e){
        if (hasHeadCount()) {
            directReports.add(e);
            e.setManager(this);
            return true;
        } else return false;
    }

    public boolean approveCheckIn(SoftwareEngineer e){
        if (e.getCodeAccess() && (this.equals(e.getManager()))) {
            return true;
        } else return false;
    }

    public boolean requestBonus(SoftwareEngineer e, double bonus){

        BusinessLead businessLead = (BusinessLead)e.getManager().getManager(); // this can be made a private variable

        if (businessLead.approveBonus(e, bonus)){
            e.setBaseSalary(e.getBaseSalary() + bonus);
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<SoftwareEngineer> getDirectReports(){
        return directReports;
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
