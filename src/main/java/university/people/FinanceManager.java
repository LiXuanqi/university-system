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
        System.out.println("Rate has been set to " + rate);
    }

    public void addScholarship(String scholarshipName, String type, String condition) {
        Scholarship scholarship = ScholarshipFactory.getInstance(scholarshipName, type, condition);
        db.addScholarship(scholarship);
        System.out.println(scholarshipName + " is added");
    }

    public void addTargetConditionToScholarship(String name, String target) {
        db.findScholarshipByName(name).getRewardCondition().addTarget(target);
    }


    public void deleteScholarship(String name) {
        db.deleteScholarship(name);
        System.out.println(name + " is deleted.");
    }

    public void updateScholarship(String originalName, String newName) {
        Scholarship scholarship = db.findScholarshipByName(originalName);
        if (scholarship == null) {
            return;
        }
        scholarship.setName(newName);
        System.out.println("Scholarship: " + originalName + " has been renamed as " + newName);
    }

    public void browseScholarship(String name) {
        Scholarship scholarship = db.findScholarshipByName(name);
        System.out.println(scholarship);
    }

    public void attachScholarshipToStudent(String studentName, String scholarshipName) {
        Student student = db.findStudentByName(studentName);
        Scholarship scholarship = db.findScholarshipByName(scholarshipName);
        if (student == null || scholarship == null) {
            return;
        }
        db.attachScholarshipToStudent(scholarship, student);
        System.out.println(studentName + " get " + scholarshipName);
    }

    public void deleteScholarshipFromStudent(String studentName, String scholarshipName) {
        Student student = db.findStudentByName(studentName);
        Scholarship scholarship = db.findScholarshipByName(scholarshipName);
        if (student == null || scholarship == null) {
            return;
        }
        db.deleteScholarshipFromStudent(scholarship, student);
        System.out.println(scholarshipName + " is deleted from " + studentName);
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
