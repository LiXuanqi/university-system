package university;

import university.people.CourseManager;
import university.people.FinanceManager;
import university.people.Student;
import university.people.Teacher;

import java.util.Random;

public class University {
    private static Database db = Database.getInstance();

    public void importMockData() {
        // create students
        String[][] students= {
                {"Jack", "USA"},
                {"Yakki", "CHINA"},
                {"Yammy", "CHINA"},
                {"Steve", "UK"},
                {"Hanzo", "JAPAN"},
                {"Yasuo", "JAPAN"},
                {"LeeSin", "CHINA"}
        };
        for (String[] student : students) {
            createStudent(student[0], student[1]);
        }
        // create teacher
        createTeacher("Ashe");
        createTeacher("Anivia");
        // create course manager
        createCourseManager("Jax");
        CourseManager courseManager = db.findCourseManagerByName("Jax");
        // create finance manager
        createFinanceManager("Yi");
        FinanceManager financeManager = db.findFinanceManagerByName("Yi");

        // create course.
        courseManager.addCourse("Application Engineering and Development", 20, 4, "12:00 PM - 03:00 PM", "ONLINE", "http://www.course.com");
        courseManager.addCourse("Web Development Tools and Methods", 30, 8, "06:00 PM - 09:00 PM", "OFFLINE", "IoT Room");

        // add students to course
        for (String[] student : students) {
            db.findStudentByName(student[0]).signupCourse("Application Engineering and Development");
        }

        // teacher teach course.
        Teacher teacher1 = db.findTeacherByName("Ashe");
        Teacher teacher2 = db.findTeacherByName("Anivia");
        teacher1.addTeachedCourse("Application Engineering and Development");
        teacher2.addTeachedCourse("Web Development Tools and Methods");
        // add assignment
        teacher1.createAssignment("Application Engineering and Development", Database.ASSIGNMENTS_PATHS[0]);
        teacher1.createAssignment("Application Engineering and Development", Database.ASSIGNMENTS_PATHS[1]);
        // upload submission and teacher grade submission
        Random random = new Random();
        for (String[] student : students) {
            db.findStudentByName(student[0]).addSubmissionToCourse("Application Engineering and Development", 0, Database.SUBMISSION_PATHS[0]);
            teacher1.gradeSubmission("Application Engineering and Development", 0, student[0], random.nextInt(99));
            db.findStudentByName(student[0]).addSubmissionToCourse("Application Engineering and Development", 1, Database.SUBMISSION_PATHS[1]);
            teacher1.gradeSubmission("Application Engineering and Development", 1, student[0], random.nextInt(99));
        }
        // set evaluation rule
        teacher1.setCourseEvaluationRule("Application Engineering and Development", "POINT");

        // grade course
        teacher1.gradeCourse("Application Engineering and Development");

        // add scholarship
        financeManager.setRate(2000);
        financeManager.addScholarship("CHINESE SCHOLARSHIP", "FIXED", "COUNTRY");
        financeManager.addTargetConditionToScholarship("CHINESE SCHOLARSHIP", "CHINA");


    }

    private void createCourseManager(String name) {
        CourseManager courseManager = new CourseManager(name);
        db.addCourseManager(courseManager);
    }

    private void createFinanceManager(String name) {
        FinanceManager financeManager = new FinanceManager(name);
        db.addFinanceManager(financeManager);
    }

    private void createTeacher(String name) {
        Teacher teacher = new Teacher(name);
        db.addTeacher(teacher);
    }

    private void createStudent(String name, String country) {
        Student student = new Student(name, country);
        db.addStudent(student);
    }

    private static void printTestTitle(String str) {
        System.out.println();
        System.out.println(" ==== " + str + " ====");
    }

    public static void main(String[] args) {
        University university = new University();
        university.importMockData();


        Student student1 = db.findStudentByName("Jack");
        printTestTitle("Student: browse courses");
        student1.browseCourses();
        printTestTitle("Student: sign up");
        student1.signupCourse("Application Engineering and Development");
        student1.signupCourse("Web Development Tools and Methods");

        printTestTitle("Student: drop");
        student1.dropCourse("Web Development Tools and Methods");
        printTestTitle("Student: view schedule");
        student1.viewAllSchedules();
        printTestTitle("Student: view transcript");
        student1.viewTranscript();

        printTestTitle("Student: create submission");
        student1.addSubmissionToCourse("Application Engineering and Development", 0, Database.SUBMISSION_PATHS[0]);

        printTestTitle("Student: delete submission");
        student1.deleteSubmission("Application Engineering and Development", 1);

        printTestTitle("Student: browser Submission");
        student1.browseSubmission("Application Engineering and Development", 0);

        Teacher teacher = db.findTeacherByName("Ashe");

        printTestTitle("Teacher: browser Assignments");
        teacher.browseMyAllAssignments();

        printTestTitle("Teacher: create Assignments");
        teacher.createAssignment("Application Engineering and Development", Database.ASSIGNMENTS_PATHS[0]);

        printTestTitle("Teacher: delete Assignments");
        teacher.deleteAssignment("Application Engineering and Development", 2);

        printTestTitle("Teacher: grade submission");
        teacher.gradeSubmission("Application Engineering and Development", 0, "Jack", 100);

        printTestTitle("Teacher: set evaluation rule");
        teacher.setCourseEvaluationRule("Application Engineering and Development", "POINT");
        teacher.setCourseEvaluationRule("Application Engineering and Development", "RANK");

        CourseManager courseManager = db.findCourseManagerByName("Jax");

        printTestTitle("Course Manager: add course");
        courseManager.addCourse("TestCourse", 2, 4, "UNDECIDED", "OFFLINE", "MTV");


        printTestTitle("Course Manager: update course");
        courseManager.updateCourseName("TestCourse", "NewCourseName");

        printTestTitle("Course Manager: browse course");
        courseManager.browseCourse("NewCourseName");

        printTestTitle("Course Manager: delete course");
        courseManager.deleteCourse("newCourseName");

        FinanceManager financeManager = db.findFinanceManagerByName("Yi");
        printTestTitle("Finance Manager: set Rate");
        financeManager.setRate(1000);

        printTestTitle("Finance Manager: add Scholarship");
        financeManager.addScholarship("DEAN SCHOLARSHIP", "RATE", "GRADE");
        financeManager.addTargetConditionToScholarship("DEAN SCHOLARSHIP", "B");
        printTestTitle("Finance Manager: update Scholarship");
        financeManager.updateScholarship("DEAN SCHOLARSHIP", "NEW DEAN SCHOLARSHIP");

        printTestTitle("Finance Manager: browse Scholarship");
        financeManager.browseScholarship("NEW DEAN SCHOLARSHIP");

        printTestTitle("Finance Manager: delete Scholarship");
        financeManager.deleteScholarship("NEW DEAN SCHOLARSHIP");

        printTestTitle("Finance Manager: attach Scholarship to Student");
        financeManager.attachScholarshipToStudent("Jack", "CHINESE SCHOLARSHIP");
        financeManager.attachScholarshipToStudent("Jack", "CHINESE SCHOLARSHIP");
        financeManager.attachScholarshipToStudent("Yakki", "CHINESE SCHOLARSHIP");
        financeManager.attachScholarshipToStudent("Yammy", "CHINESE SCHOLARSHIP");

        printTestTitle("Finance Manager: delete Scholarship from Student");
        financeManager.deleteScholarshipFromStudent("Jack", "CHINESE SCHOLARSHIP");

        printTestTitle("Finance Manager: browse Scholarship Assignments");
        financeManager.browseScholarshipAssignments();
    }
}
