package university.people;

import university.Database;
import university.course.Course;
import university.scholarship.*;

import java.util.Map;
import java.util.Set;

public class FinanceManager extends User {
    private Database db = Database.getInstance();

    public FinanceManager(String name) {
        super(name);
    }

    public void setRate(int rate) {
        Course.setCreditRate(rate);
    }

    public void addScholarship(String scholarshipName, String type, String condition) {
        Scholarship scholarship = ScholarshipFactory.getInstance(scholarshipName, type, condition);
        db.addScholarship(scholarship);
    }

    public void addTargetConditionToScholarship(String name, String target) {
        db.findScholarshipByName(name).getRewardCondition().addTarget(target);
    }


    public void deleteScholarship(String name) {
        db.deleteScholarship(name);
    }

    public void updateScholarship(String originalName, String newName) {
        Scholarship scholarship = db.findScholarshipByName(originalName);
        scholarship.setName(newName);
    }

    public void browseScholarship(String name) {
        Scholarship scholarship = db.findScholarshipByName(name);
        System.out.println(scholarship);
    }

    public void attachScholarshipToStudent(String studentName, String scholarshipName) {
        Student student = db.findStudentByName(studentName);
        Scholarship scholarship = db.findScholarshipByName(scholarshipName);
        db.attachScholarshipToStudent(scholarship, student);
    }

    public void deleteScholarshipFromStudent(String studentName, String scholarshipName) {
        Student student = db.findStudentByName(studentName);
        Scholarship scholarship = db.findScholarshipByName(scholarshipName);
        db.deleteScholarshipFromStudent(scholarship, student);
    }

    public void browseScholarshipAssignments() {
        Map<Scholarship, Set<Student>> map = db.getScholarshipToStudents();

        for (Map.Entry<Scholarship, Set<Student>> entry : map.entrySet()) {
            System.out.println(entry.getKey().getName());
            for (Student student : entry.getValue()) {
                System.out.println("  - " + student.getName());
            }
        }
    }
}
