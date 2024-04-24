public class CompanyStructure {
    public static void main(String[] args) {
        TechnicalLead CTO = new TechnicalLead("Satya Nadella");
        SoftwareEngineer seA = new SoftwareEngineer("Kasey");
        SoftwareEngineer seB = new SoftwareEngineer("Breana");
        SoftwareEngineer seC = new SoftwareEngineer("Eric");
        CTO.addReport(seA);
        CTO.addReport(seB);
        CTO.addReport(seC);
        System.out.println(CTO.getTeamStatus());
        System.out.println();

        TechnicalLead VPofENG = new TechnicalLead("Bill Gates");
        SoftwareEngineer seD = new SoftwareEngineer("Winter");
        SoftwareEngineer seE = new SoftwareEngineer("Libby");
        SoftwareEngineer seF = new SoftwareEngineer("Gizan");
        SoftwareEngineer seG = new SoftwareEngineer("Zaynah");
        System.out.println(VPofENG.getName() + " added report " + seD.getName() +  "is successful = " + VPofENG.addReport(seD));
        //VPofENG.addReport(seD);
        VPofENG.addReport(seE);
        VPofENG.addReport(seF);
        VPofENG.addReport(seG);
        seE.setCodeAccess(true);
        System.out.println(seE.getName() + " check in is successful = " + seE.checkInCode());
        System.out.println();
        System.out.println(VPofENG.getTeamStatus());
        System.out.println();

        BusinessLead CFO = new BusinessLead("Amy Hood");
        Accountant actA = new Accountant("Niky");
        Accountant actB = new Accountant("Andrew");
        //CFO.addReport(actA, CTO);
        System.out.println(CFO.getName() + " add report " + actA.getName() + " is successful: " + CFO.addReport(actA, CTO));
        CFO.addReport(actB, VPofENG);
        System.out.println();
        System.out.println(CFO.getTeamStatus());

        System.out.println();
        System.out.println(seE.getName()+" salary was" + seE.getBaseSalary());
        System.out.println(VPofENG.getName() + " requested bonus for " + seE.getName() + " is successful =" + VPofENG.requestBonus(seE, 500));
        System.out.println(seE.getName()+" salary is" + seE.getBaseSalary());

        System.out.println();
        System.out.println(actA.getName()+" salary was" + actA.getBaseSalary());
        System.out.println(CFO.getName() + " request bonus for " + actA.getName() + " is successful " + CFO.requestBonus(actA, 500));
        System.out.println(actA.getName()+" salary is" + actA.getBaseSalary());







    }
}
