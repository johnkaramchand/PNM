package in.atria.gov.demo;

public class NotificationModel {

    String id;
    String problem;


    public NotificationModel(String id, String problem) {
        this.id = id;
        this.problem = problem;

    }

    public String getId() {
        return id;
    }

    public String getProblem() {
        return problem;
    }


}