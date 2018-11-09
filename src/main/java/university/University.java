package university;

import university.people.CourseManager;
import university.people.FinanceManager;
import university.people.Student;
import university.people.Teacher;

import java.util.Random;

public class University {
    private Database db = Database.getInstance();

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
        teacher1.createAssignment("Application Engineering and Development", "/Users/lixuanqi/Github/university-system/src/main/resources/assignment1");
        teacher1.createAssignment("Application Engineering and Development", "/Users/lixuanqi/Github/university-system/src/main/resources/assignment2");
        // upload submission and teacher grade submission
        Random random = new Random();
        for (String[] student : students) {
            db.findStudentByName(student[0]).addSubmissionToCourse("Application Engineering and Development", 0, "/Users/lixuanqi/Github/university-system/src/main/resources/submission1");
            teacher1.gradeSubmission("Application Engineering and Development", 0, student[0], random.nextInt(99));
            db.findStudentByName(student[0]).addSubmissionToCourse("Application Engineering and Development", 1, "/Users/lixuanqi/Github/university-system/src/main/resources/submission2");
            teacher1.gradeSubmission("Application Engineering and Development", 1, student[0], random.nextInt(99));
        }
        // grade course
        teacher1.gradeCourse("Application Engineering and Development");

        // add scholarship
        financeManager.setRate(2000);
        financeManager.addScholarship("CHINESE SCHOLARSHIP", "FIXED", "COUNTRY");
        financeManager.addTargetConditionToScholarship("CHINESE SCHOLARSHIP", "CHINA");


    }

    public void createCourseManager(String name) {
        CourseManager courseManager = new CourseManager(name);
        db.addCourseManager(courseManager);
    }

    public void createFinanceManager(String name) {
        FinanceManager financeManager = new FinanceManager(name);
        db.addFinanceManager(financeManager);
    }

    public void createTeacher(String name) {
        Teacher teacher = new Teacher(name);
        db.addTeacher(teacher);
    }

    public void createStudent(String name, String country) {
        Student student = new Student(name, country);
        db.addStudent(student);
    }
}
