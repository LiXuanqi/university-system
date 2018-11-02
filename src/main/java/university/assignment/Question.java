package university.assignment;

public class Question {

    private String text;
    private int point;

    public Question(String text, int point) {
        this.text = text;
        this.point = point;
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", point=" + point +
                '}';
    }
}
