package university.scholarship;

public class ScholarshipFactory {
    public static Scholarship getInstance(String name, String type, String condition) {
        Scholarship scholarship = null;
        if (type.equalsIgnoreCase("FIXED")) {
            scholarship = new FixedScholarship(name);
        } else if (type.equalsIgnoreCase("RATE")) {
            scholarship = new RateScholarship(name);
        }
        if (condition.equalsIgnoreCase("COUNTRY")) {
            scholarship.setRewardCondition(new CountryCondition());
        } else if (condition.equalsIgnoreCase("GRADE")) {
            scholarship.setRewardCondition(new GradeCondition());
        }
        return scholarship;
    }
}
