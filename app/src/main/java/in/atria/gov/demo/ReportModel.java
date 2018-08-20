package in.atria.gov.demo;

public class ReportModel {

    String id;
    String problem;
    String reportedTo;
    String village;
    String update;

    public ReportModel(String id, String problem, String reportedTo, String village, String update) {
        this.id = id;
        this.problem = problem;
        this.reportedTo = reportedTo;
        this.village = village;
        this.update=update;
    }

    public String getId() {
        return id;
    }
    public String getReportedTo() {
        return reportedTo;
    }
    public String getVillage() {
        return village;
    }
    public String getProblem() {
        return problem;
    }
    public String getUpdate()
    {
        return update;
    }

}