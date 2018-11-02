package university.assignment;

import java.util.HashSet;
import java.util.Set;

public class MultipleChoice implements Answer {
    @Override
    public String getContent() {
        StringBuilder sb = new StringBuilder();
        for (Choice choice : ans) {
            sb.append(choice);
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    enum Choice {
        A, B, C, D
    }
    private Set<Choice> ans;

    public MultipleChoice() {
        this.ans = new HashSet<>();
    }

    public void addChoice(String choice) {
        ans.add(Choice.valueOf(choice));
    }

    @Override
    public String toString() {
        return "MultipleChoice{" +
                "ans=" + ans +
                '}';
    }
}
