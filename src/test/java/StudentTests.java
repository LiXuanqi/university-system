import org.junit.jupiter.api.Test;

public class StudentTests {
    @Test
    void createTest() {
        Student student = new Student("XUANQI LI");
        System.out.println(student);
    }
}
