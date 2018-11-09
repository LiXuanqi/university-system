package people;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import university.Database;
import university.people.FinanceManager;
import university.people.Student;

public class FinanceManagerTests {

    FinanceManager manager;

    private Database db = Database.getInstance();

    @BeforeEach
    void setUpEach() {
        manager = new FinanceManager("Bill");
        Student student = new Student("Jack", "CHINA");
        db.addStudent(student);
    }

    @Test
    void attachScholarshipToStudentTest() {
        manager.addScholarship("ChineseScholarship", "FIXED", "COUNTRY");
        manager.addTargetConditionToScholarship("ChineseScholarship", "CHINA");
        manager.attachScholarshipToStudent("Jack", "ChineseScholarship");
    }

    @Test
    void deleteScholarshipFromStudentTest() {
        manager.addScholarship("ChineseScholarship", "FIXED", "COUNTRY");
        manager.addTargetConditionToScholarship("ChineseScholarship", "CHINA");
        manager.attachScholarshipToStudent("Jack", "ChineseScholarship");
        manager.browseScholarshipAssignments();
        manager.deleteScholarshipFromStudent("Jack", "ChineseScholarship");
        manager.browseScholarshipAssignments();
    }

    @Test
    void browseScholarshipAssignmentsTest() {
        manager.addScholarship("ChineseScholarship", "FIXED", "COUNTRY");
        manager.addTargetConditionToScholarship("ChineseScholarship", "CHINA");
        manager.attachScholarshipToStudent("Jack", "ChineseScholarship");
        manager.browseScholarshipAssignments();
    }

    @Test
    void addScholarshipTest() {
        manager.addScholarship("ChineseScholarship", "FIXED", "COUNTRY");
        manager.addTargetConditionToScholarship("ChineseScholarship", "CHINA");
        System.out.println(db.findScholarshipByName("ChineseScholarship"));
    }

    @Test
    void deleteScholarshipTest() {
        manager.addScholarship("ChineseScholarship", "FIXED", "COUNTRY");
        manager.addTargetConditionToScholarship("ChineseScholarship", "CHINA");
        System.out.println(db.findScholarshipByName("ChineseScholarship"));
        manager.deleteScholarship("ChineseScholarship");
        System.out.println(db.findScholarshipByName("ChineseScholarship"));

    }

    @Test
    void updateScholarshipTest() {
        manager.addScholarship("ChineseScholarship", "FIXED", "COUNTRY");
        manager.addTargetConditionToScholarship("ChineseScholarship", "CHINA");
        System.out.println(db.findScholarshipByName("ChineseScholarship"));
        manager.updateScholarship("ChineseScholarship", "NewScholarship");
        System.out.println(db.findScholarshipByName("ChineseScholarship"));
        System.out.println(db.findScholarshipByName("NewScholarship"));
    }

    @Test
    void browseScholarshipTest() {
        manager.addScholarship("ChineseScholarship", "FIXED", "COUNTRY");
        manager.addTargetConditionToScholarship("ChineseScholarship", "CHINA");
        manager.browseScholarship("ChineseScholarship");
    }

    @Test
    void addRateTest() {
        manager.setRate(800);
    }

    @Test
    void updateRateTest() {
        manager.setRate(1000);
    }
}
