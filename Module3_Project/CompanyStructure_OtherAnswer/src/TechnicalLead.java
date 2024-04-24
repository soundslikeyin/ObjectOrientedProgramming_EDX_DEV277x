import java.util.ArrayList;

public class TechnicalLead extends TechnicalEmployee {

    ArrayList<SoftwareEngineer> directReport = new ArrayList<>();
    int headCount;
    BusinessLead lead;
    Accountant support;


    public TechnicalLead(String name) {
        super(name);
        baseSalary *= 1.3;
        headCount = 4;
    }

    public boolean hasHeadCount() {
        return (headCount >= directReport.size());
    }

    public boolean addReport(SoftwareEngineer e) {
        if (directReport.size() < headCount) {
            directReport.add(e);
            e.manager = this;
            return true;
        }
        return false;
    }

    public String getTeamStatus() {
        String s = employeeStatus();
        if (directReport.size() == 0) {
            s += "and no direct reports yet\n";
        } else {
            s += " and is managing:\n";
            for (int i = 0; i < directReport.size(); i++) {
                int j = i + 1;
                s += "\t" + j + ". " + directReport.get(i).employeeStatus();
            }
        }

        return s;
    }

    public Employee getManager(){
        return lead;
    }
}