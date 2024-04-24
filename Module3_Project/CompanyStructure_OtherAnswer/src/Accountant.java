public class Accountant extends BusinessEmployee {

    TechnicalLead lead;
    BusinessLead manager;

    public Accountant(String name) {
        super(name);
    }

    public TechnicalLead getTeamSupported() {
        return lead;
    }

    public void supportTeam(TechnicalLead leadA) {
        lead = leadA;
        lead.support = this;
        double total = 0;
        for (int i = 0; i < lead.directReport.size(); i++) {
            total+=lead.directReport.get(i).getBaseSalary();
        }
        budget = total*1.1;
    }

    public boolean approveBonus(double bonus) {
        if(bonus>budget || lead==null) { return false; }
        return true;
    }

    public String employeeStatus() {
        return(super.employeeStatus() + " is supporting " + this.lead + "\n");
    }

    public Employee getManager() {
        return this.manager;
    }

    /*
    public boolean getCodeAccess() {
        return codeAccess;
    }

    public void setCodeAccess(boolean access) {
        codeAccess = access;
    }

    public int getSuccessfulCheckIns() {
        return checkIn;
    }

    //need to double check the 2 next methods
    public boolean checkInCode() {
        if (manager.approveCheckIn(this)) {
            checkIn++;
            return true;
        }
        else {
            return false;
        }
    }

     */
/*
    public int checkIn() {
        return checkIn++;
    }

 */
}