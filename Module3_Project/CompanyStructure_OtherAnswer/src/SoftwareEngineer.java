public class SoftwareEngineer extends TechnicalEmployee {

    boolean codeAccess;
    TechnicalLead manager;

    public SoftwareEngineer(String name) {
        super(name);
    }

    public Employee getManager(){
        return manager;
    }
}