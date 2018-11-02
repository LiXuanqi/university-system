package university.assignment;

public class Essay implements Answer {
    private String text;

    public Essay(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getContent() {
        return text;
    }

    @Override
    public String toString() {
        return "Essay{" +
                "text='" + text + '\'' +
                '}';
    }
}
