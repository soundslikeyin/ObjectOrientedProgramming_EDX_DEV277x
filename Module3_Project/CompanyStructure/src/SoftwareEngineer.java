public class SoftwareEngineer extends TechnicalEmployee{
    private boolean codeAccess;

    public SoftwareEngineer(String name){
        super(name);
        setSuccessfulCheckIns(0);
        codeAccess = false;
    }

    public boolean getCodeAccess(){
        return codeAccess;
    }

    public void setCodeAccess(boolean access){
        codeAccess = access;
    }

    public boolean checkInCode(){
        TechnicalLead manager = (TechnicalLead) this.getManager();

        if (manager.approveCheckIn(this)) {
            setSuccessfulCheckIns(getSuccessfulCheckIns() + 1);
            return true;
        }
        else {
            return false;
        }
    }
}
